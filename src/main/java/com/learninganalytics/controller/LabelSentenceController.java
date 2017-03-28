package com.learninganalytics.controller;

import com.learninganalytics.model.LabelSentence;
import com.learninganalytics.model.Rater;
import com.learninganalytics.model.Sentence;
import com.learninganalytics.model.SentencePK;
import com.learninganalytics.service.LabelSentenceService;
import com.learninganalytics.service.RaterService;
import com.learninganalytics.service.SentenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Luthfi on 27/03/2017.
 */

@Controller
public class LabelSentenceController {
    @Autowired
    private LabelSentenceService labelSentenceService;

    @Autowired
    private SentenceService sentenceService;

    @Autowired
    private RaterService raterService;

    @RequestMapping(value = "/label-sentence", method = RequestMethod.POST)
    public String createNewLabelSentence(@RequestBody LabelSentence labelSentence, Model model){
        labelSentenceService.saveLabelSentence(labelSentence);
        Sentence s = sentenceService.findSentence(new SentencePK(labelSentence.getId_post(), labelSentence.getId_sentence()));
        HashMap<Sentence, LabelSentence> sentenceToLabel = new HashMap<>();
        sentenceToLabel.put(s, labelSentence);

        Rater r = raterService.findRater(labelSentence.getId_rater());
        model.addAttribute("label", sentenceToLabel);
        model.addAttribute("sentence", s);
        model.addAttribute("rater", r);
        return "fragments/labelInfo :: info";
    }
}
