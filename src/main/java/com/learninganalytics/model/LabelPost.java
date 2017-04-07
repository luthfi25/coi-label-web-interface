package com.learninganalytics.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * Created by Luthfi on 05/04/2017.
 */

@Table(name = "`coi-label-post`")
@Entity
@IdClass(LabelPostPK.class)
public class LabelPost {
    @Id
    private String id_rater;
    @Id
    private int id_post;
    private boolean SP;
    private boolean TP;
    private boolean CP;

    public LabelPost() {
    }

    public LabelPost(String id_rater, int id_post, boolean SP, boolean TP, boolean CP) {
        this.id_rater = id_rater;
        this.id_post = id_post;
        this.SP = SP;
        this.TP = TP;
        this.CP = CP;
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

    public boolean isSP() {
        return SP;
    }

    public void setSP(boolean SP) {
        this.SP = SP;
    }

    public boolean isTP() {
        return TP;
    }

    public void setTP(boolean TP) {
        this.TP = TP;
    }

    public boolean isCP() {
        return CP;
    }

    public void setCP(boolean CP) {
        this.CP = CP;
    }

    @Override
    public String toString() {
        return "LabelPost{" +
                "id_rater='" + id_rater + '\'' +
                ", id_post=" + id_post +
                ", SP=" + SP +
                ", TP=" + TP +
                ", CP=" + CP +
                '}';
    }
}
