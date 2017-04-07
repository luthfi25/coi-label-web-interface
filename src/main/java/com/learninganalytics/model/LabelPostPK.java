package com.learninganalytics.model;

import java.io.Serializable;

/**
 * Created by Luthfi on 05/04/2017.
 */
public class LabelPostPK implements Serializable {
    private String id_rater;
    private int id_post;

    public LabelPostPK() {
    }

    public LabelPostPK(String id_rater, int id_post) {
        this.id_rater = id_rater;
        this.id_post = id_post;
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
}
