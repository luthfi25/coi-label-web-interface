package com.learninganalytics.repository;

import com.learninganalytics.model.RaterType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Luthfi on 17/03/2017.
 */
@Repository("raterTypeRepository")
public interface RaterTypeRepository extends JpaRepository<RaterType, Integer>{
    RaterType findByRole(String role);
}
