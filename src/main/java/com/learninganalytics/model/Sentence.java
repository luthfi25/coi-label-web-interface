package com.learninganalytics.model;

import javax.persistence.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by Luthfi on 26/03/2017.
 */

@Table(name = "`scele-forum-sentence`")
@IdClass(SentencePK.class)
@Entity
public class Sentence {
    @Id
    private int post_id;
    @Id
    private int sentence_id;
    private String sentence;
    @OneToMany(mappedBy = "sentence" )
    private List<LabelSentence> labelSentence;

    public Sentence() {
    }

    public Sentence(int post_id, int sentence_id, String sentence) {
        this.post_id = post_id;
        this.sentence_id = sentence_id;
        this.sentence = sentence;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public int getSentence_id() {
        return sentence_id;
    }

    public void setSentence_id(int sentence_id) {
        this.sentence_id = sentence_id;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public void setLabelSentence(List<LabelSentence> labelSentence) {
        this.labelSentence = labelSentence;
    }

    public List<LabelSentence> getLabelSentence() {
        return labelSentence;
    }
}
