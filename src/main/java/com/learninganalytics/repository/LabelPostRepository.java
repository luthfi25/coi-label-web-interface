package com.learninganalytics.repository;

import com.learninganalytics.model.LabelPost;
import com.learninganalytics.model.LabelPostPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Luthfi on 05/04/2017.
 */

@Repository("labelPostRepository")
public interface LabelPostRepository extends JpaRepository<LabelPost, LabelPostPK>{
}
