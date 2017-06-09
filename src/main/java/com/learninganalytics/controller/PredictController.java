package com.learninganalytics.controller;

import com.learninganalytics.model.Rater;
import com.learninganalytics.model.ToPredict;
import com.learninganalytics.service.RaterService;
import com.learninganalytics.utils.CSVUtils;
import meka.classifiers.multilabel.BPNN;
import meka.core.MLUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import weka.core.Instances;
import weka.core.Utils;
import weka.core.converters.ConverterUtils.DataSource;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Luthfi on 09/06/2017.
 */
@Controller
public class PredictController {
    @Autowired
    RaterService raterService;

    @Value("${spring.coi.directory}")
    private String coiDir;

    @RequestMapping(value = "/home/predict", method = RequestMethod.GET)
    public ModelAndView predict(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Rater rater = raterService.findRater(auth.getName());

        modelAndView.addObject("rater", rater);
        modelAndView.setViewName("predict");
        return modelAndView;
    }

    @RequestMapping(value="/predict-api", method = RequestMethod.POST)
    public String predictAPI(@RequestBody ToPredict toPredict, Model model) throws Exception {
        String document = toPredict.getDocument();

        //clean html tag
        Pattern p = Pattern.compile("<.*?>");
        Matcher m = p.matcher(document);
        document = m.replaceAll(" ");

        //split sentence
        String command =  "python "+ coiDir +"/split_sentence.py " + document;
        Process proc = Runtime.getRuntime().exec(command);
        BufferedReader stdInput = new BufferedReader(new
                InputStreamReader(proc.getInputStream()));

        String output = "";
        // read the output from the command
        String s;
        while ((s = stdInput.readLine()) != null) {
            output = output + s;
        }

        BufferedReader stdError = new BufferedReader(new
                InputStreamReader(proc.getErrorStream()));

        String error = "";
        // read any errors from the attempted command
        while ((s = stdError.readLine()) != null) {
            error = error + s;
        }

        System.out.println("error split sentence: " + error);

        String[] sentences = output.split(";");

        ArrayList<String> cleanSentence = new ArrayList<>();
        //remove punctuation & case folding
        for(String sentence: sentences){
            p = Pattern.compile("[^\\w\\s]");
            m = p.matcher(sentence);
            sentence = m.replaceAll(" ");
            sentence = sentence.toLowerCase();
            cleanSentence.add(sentence);
        }

        //build csv
        String csvFile = coiDir + "/predict.csv";
        FileWriter writer = new FileWriter(csvFile);

        CSVUtils.writeLine(writer, Arrays.asList("sentence"));
        for (String sentence : cleanSentence){
            CSVUtils.writeLine(writer, Arrays.asList(sentence));
        }
        writer.flush();
        writer.close();

        //extract feature
        command =  "python auto-extract-feature.py " + coiDir;
        proc = Runtime.getRuntime().exec(command, null, new File(coiDir));
        stdInput = new BufferedReader(new
                InputStreamReader(proc.getInputStream()));

        stdError = new BufferedReader(new
                InputStreamReader(proc.getErrorStream()));

        // read any errors from the attempted command
        while ((s = stdError.readLine()) != null) {
            error = error + s;
        }

        System.out.println("error extract feature: " + error);

        //predict
        Instances train = DataSource.read(coiDir + "/model/train.arff");
        MLUtils.prepareData(train);

        Instances predict = DataSource.read( coiDir + "/predict.arff");
        MLUtils.prepareData(predict);

        String msg = train.equalHeadersMsg(predict);
        if (msg != null)
            throw new IllegalStateException(msg);

        BPNN classifier = new BPNN();
        classifier.setLearningRate(0.01);
        classifier.setH(20);
        classifier.setE(1000);
        classifier.setMomentum(0.2);
        classifier.setSeed(0);
        classifier.buildClassifier(train);

        int length = cleanSentence.size();
        int [][] predicted = new int[length][3];
        for (int i = 0; i < predict.numInstances(); i++){
            double[] dist = classifier.distributionForInstance(predict.instance(i));
            for (int j = 0; j < dist.length; j++){
                predicted[i][j] = (int) Math.rint(dist[j]);
            }
        }

        System.out.println(Utils.arrayToString(predicted));
        int[] finalPredict = new int[length];

        for (int i = 0; i < predicted.length; i++){
            int val = 0;
            if (predicted[i][0] == 1){
                val += 4;
            }
            if (predicted[i][1] == 1){
                val += 2;
            }
            if (predicted[i][2] == 1){
                val += 1;
            }
            finalPredict[i] = val;
        }
        System.out.println(Utils.arrayToString(finalPredict));
        System.out.println(Arrays.toString(cleanSentence.toArray()));

        model.addAttribute("sentences", cleanSentence);
        model.addAttribute("prediction", finalPredict);
        return "fragments/complement :: predict";
    }
}
