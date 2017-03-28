package com.learninganalytics.repository;

import com.learninganalytics.model.LabelSentence;
import com.learninganalytics.model.LabelSentencePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Luthfi on 26/03/2017.
 */

@Repository("labelSentenceRepository")
public interface LabelSentenceRepository extends JpaRepository<LabelSentence, LabelSentencePK> {
    @Query("SELECT ls FROM LabelSentence ls WHERE ls.id_rater = ?1")
    List<LabelSentence> findById_rater(String id_rater);
}
