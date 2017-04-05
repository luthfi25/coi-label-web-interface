package com.learninganalytics.service;

import com.learninganalytics.model.Sentence;
import com.learninganalytics.model.SentencePK;
import com.learninganalytics.repository.SentenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Luthfi on 26/03/2017.
 */

@Service("sentenceService")
public class SentenceServiceImpl implements SentenceService{
    @Autowired
    private SentenceRepository sentenceRepository;

    @Override
    public List<Sentence> findAllSentence() {
        return sentenceRepository.findAll();
    }

    @Override
    public Sentence findSentence(SentencePK id) {
        return sentenceRepository.findOne(id);
    }

    @Override
    public Page<Sentence> findPaginateSentence(Pageable pageable, boolean view) {
        if(view)
            return sentenceRepository.findAll(pageable);
        else
            return sentenceRepository.findAllSentenceSorted(pageable);
    }
}
