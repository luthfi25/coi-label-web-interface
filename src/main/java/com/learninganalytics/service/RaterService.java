package com.learninganalytics.service;

import com.learninganalytics.model.Rater;

import java.util.List;

/**
 * Created by Luthfi on 17/03/2017.
 */
public interface RaterService {
    public Rater findRater(String id);
    public void saveRater(Rater rater);
    public List<Rater> findAllRater();
    public void deleteRater(Rater rater);
}
