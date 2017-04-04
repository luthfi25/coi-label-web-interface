package com.learninganalytics.service;

import com.learninganalytics.model.Rater;
import com.learninganalytics.model.RaterType;
import com.learninganalytics.repository.RaterRepository;
import com.learninganalytics.repository.RaterTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by Luthfi on 17/03/2017.
 */

@Service("raterService")
public class RaterServiceImpl implements RaterService{
    @Autowired
    private RaterRepository raterRepository;
    @Autowired
    private RaterTypeRepository raterTypeRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Rater findRater(String id) {
        return raterRepository.findOne(id);
    }

    @Override
    public void saveRater(Rater rater) {
        rater.setPassword(bCryptPasswordEncoder.encode(rater.getPassword()));
        RaterType raterType = raterTypeRepository.findByRole("Human");
        rater.setType(raterType);
        raterRepository.save(rater);
    }
}
