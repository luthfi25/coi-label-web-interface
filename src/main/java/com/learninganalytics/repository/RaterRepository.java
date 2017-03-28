package com.learninganalytics.repository;

import com.learninganalytics.model.Rater;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Luthfi on 17/03/2017.
 */

@Repository("raterRepository")
public interface RaterRepository extends JpaRepository<Rater, String>{
}
