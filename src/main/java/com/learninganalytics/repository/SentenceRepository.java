package com.learninganalytics.repository;

import com.learninganalytics.model.Sentence;
import com.learninganalytics.model.SentencePK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Luthfi on 26/03/2017.
 */
@Repository("sentenceRepository")
public interface SentenceRepository extends JpaRepository<Sentence, SentencePK>{
}
