package com.learninganalytics.service;

import com.learninganalytics.model.Sentence;
import com.learninganalytics.model.SentencePK;

import java.util.List;

/**
 * Created by Luthfi on 26/03/2017.
 */
public interface SentenceService {
    public List<Sentence> findAllSentence();
    public Sentence findSentence(SentencePK id);
}
