package com.learninganalytics.service;

import com.learninganalytics.model.LabelSentence;
import com.learninganalytics.model.LabelSentencePK;
import com.learninganalytics.repository.LabelSentenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Luthfi on 26/03/2017.
 */
@Service("labelSentenceService")
public class LabelSentenceServiceImpl implements LabelSentenceService {
    @Autowired
    private LabelSentenceRepository labelSentenceRepository;

    @Override
    public List<LabelSentence> findAllLabelSentences() {
        return labelSentenceRepository.findAll();
    }

    @Override
    public LabelSentence findLabelSentence(LabelSentencePK id) {
        return labelSentenceRepository.findOne(id);
    }

    @Override
    public List<LabelSentence> findLabelSentencebyIdRater(String id_rater) {
        return labelSentenceRepository.findById_rater(id_rater);
    }

    @Override
    public void saveLabelSentence(LabelSentence labelSentence) {
        labelSentenceRepository.save(labelSentence);
    }

    @Override
    public void deleteLabelSentence(LabelSentence labelSentence) {
        labelSentenceRepository.delete(new LabelSentencePK(labelSentence.getId_rater(), labelSentence.getId_post(), labelSentence.getId_sentence()));
    }
}
