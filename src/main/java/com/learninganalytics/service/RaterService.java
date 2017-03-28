package com.learninganalytics.service;

import com.learninganalytics.model.Rater;

/**
 * Created by Luthfi on 17/03/2017.
 */
public interface RaterService {
    public Rater findRater(String id);
    public void saveRater(Rater rater);
}
