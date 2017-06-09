package parser;

import java.io.*;
import java.util.*;

import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.parser.shiftreduce.ShiftReduceParser;
import edu.stanford.nlp.process.DocumentPreprocessor;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import edu.stanford.nlp.trees.Tree;


public class Parser {
	
	private ShiftReduceParser model;
	private MaxentTagger tagger;
	
	public Parser()
	{
		String modelPath = "Indonesian_Model.ser.gz";
		String taggerPath = "tagger.model";
		tagger = new MaxentTagger(taggerPath);
		model = ShiftReduceParser.loadModel(modelPath);
	}
	 
	public String parse(String str)
	{
	    DocumentPreprocessor tokenizer = new DocumentPreprocessor(new StringReader(str));
		List<HasWord> all = new ArrayList<HasWord>();
		for (List<HasWord> sentence : tokenizer) {
			for(HasWord h: sentence){
				all.add(h);
			}
		}
		List<TaggedWord> tagged = tagger.tagSentence(all);
		Tree tree = model.apply(tagged);
		return tree.toString();
	}
	
	public static void main(String[] args) throws IOException {    
		Parser p = new Parser();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		try {
			String line = null;
			while ((line = in.readLine()) != null) {
				System.out.println(p.parse(line));
			}
		} finally {
			in.close();
		}
	}
}
