package com.learninganalytics.service;

import com.learninganalytics.model.LabelSentence;
import com.learninganalytics.model.LabelSentencePK;

import java.awt.*;
import java.util.List;

/**
 * Created by Luthfi on 26/03/2017.
 */
public interface LabelSentenceService {
    public List<LabelSentence> findAllLabelSentences();
    public LabelSentence findLabelSentence(LabelSentencePK id);
    public List<LabelSentence> findLabelSentencebyIdRater(String id_rater);
    public void saveLabelSentence(LabelSentence labelSentence);
}
