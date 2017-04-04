package com.learninganalytics.repository;

import com.learninganalytics.model.Sentence;
import com.learninganalytics.model.SentencePK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by Luthfi on 26/03/2017.
 */
@Repository("sentenceRepository")
public interface SentenceRepository extends JpaRepository<Sentence, SentencePK>{
    @Query("select sentence from Sentence sentence ORDER BY (CASE WHEN EXISTS (SELECT ls from LabelSentence ls where ls.id_post = sentence.post_id AND ls.id_sentence = sentence.sentence_id) THEN sentence.post_id END) ASC, (CASE WHEN NOT EXISTS (SELECT ls from LabelSentence ls where ls.id_post = sentence.post_id AND ls.id_sentence = sentence.sentence_id) THEN sentence.post_id END) ASC")
    Page<Sentence> findAllSentenceSorted(Pageable pageable);
}
