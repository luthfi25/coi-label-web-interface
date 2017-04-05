package com.learninganalytics.service;

import com.learninganalytics.model.Sentence;
import com.learninganalytics.model.SentencePK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by Luthfi on 26/03/2017.
 */
public interface SentenceService {
    public List<Sentence> findAllSentence();
    public Sentence findSentence(SentencePK id);
    public Page<Sentence> findPaginateSentence(Pageable pageable, boolean view);
}
