package com.learninganalytics.service;

import com.learninganalytics.model.LabelPost;
import com.learninganalytics.model.LabelPostPK;

import java.util.List;

/**
 * Created by Luthfi on 05/04/2017.
 */
public interface LabelPostService {
    public List<LabelPost> findAllLabelPosts();
    public LabelPost findLabelPost(LabelPostPK id);
}
