package com.learninganalytics.service;

import com.learninganalytics.model.LabelPost;
import com.learninganalytics.model.LabelPostPK;
import com.learninganalytics.repository.LabelPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Luthfi on 05/04/2017.
 */

@Service("labelPostService")
public class LabelPostServiceImpl implements LabelPostService{
    @Autowired
    private LabelPostRepository labelPostRepository;

    @Override
    public List<LabelPost> findAllLabelPosts() {
        return labelPostRepository.findAll();
    }

    @Override
    public LabelPost findLabelPost(LabelPostPK id) {
        return labelPostRepository.findOne(id);
    }
}
