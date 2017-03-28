package com.learninganalytics.model;

import java.io.Serializable;

/**
 * Created by Luthfi on 26/03/2017.
 */
public class LabelSentencePK implements Serializable {
    private String id_rater;
    private int id_post;
    private int id_sentence;

    public LabelSentencePK() {
    }

    public LabelSentencePK(String id_rater, int id_post, int id_sentence) {
        this.id_rater = id_rater;
        this.id_post = id_post;
        this.id_sentence = id_sentence;
    }

    public String getId_rater() {
        return id_rater;
    }

    public void setId_rater(String id_rater) {
        this.id_rater = id_rater;
    }

    public int getId_post() {
        return id_post;
    }

    public void setId_post(int id_post) {
        this.id_post = id_post;
    }

    public int getId_sentence() {
        return id_sentence;
    }

    public void setId_sentence(int id_sentence) {
        this.id_sentence = id_sentence;
    }
}
