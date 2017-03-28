package com.learninganalytics.model;

import java.io.Serializable;

/**
 * Created by Luthfi on 26/03/2017.
 */
public class SentencePK implements Serializable{
    private int post_id;
    private int sentence_id;

    public SentencePK() {
    }

    public SentencePK(int post_id, int sentence_id) {
        this.post_id = post_id;
        this.sentence_id = sentence_id;
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
}
