package com.learninganalytics.model;

import javax.persistence.*;

/**
 * Created by Luthfi on 26/03/2017.
 */

@Table(name = "`coi-label-sentence`")
@Entity
@IdClass(LabelSentencePK.class)
public class LabelSentence {
    @Id
    @Column(name = "id_post")
    private int id_post;
    @Id
    @Column(name = "id_sentence")
    private int id_sentence;
    @Id
    @Column(name = "id_rater")
    private String id_rater;
    @ManyToOne
    @JoinColumns(
        {
                @JoinColumn(updatable=false,insertable=false, name = "id_post", referencedColumnName = "post_id"),
                @JoinColumn(updatable=false,insertable=false, name = "id_sentence", referencedColumnName = "sentence_id")
        }
    )
    private Sentence sentence;
    @ManyToOne
    @JoinColumn(insertable=false, updatable=false, name = "id_rater")
    private Rater rater;
    @Column(name = "SP")
    private boolean SP;
    @Column(name = "TP")
    private boolean TP;
    @Column(name = "CP")
    private boolean CP;


    public LabelSentence() {
    }

    public LabelSentence(int id_post, int id_sentence, String id_rater, Sentence sentence, Rater rater, boolean SP, boolean TP, boolean CP) {
        this.id_post = id_post;
        this.id_sentence = id_sentence;
        this.id_rater = id_rater;
        this.sentence = sentence;
        this.rater = rater;
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

    public int getId_sentence() {
        return id_sentence;
    }

    public void setId_sentence(int id_sentence) {
        this.id_sentence = id_sentence;
    }

    public Sentence getSentence() {
        return sentence;
    }

    public void setSentence(Sentence sentence) {
        this.sentence = sentence;
    }

    public Rater getRater() {
        return rater;
    }

    public void setRater(Rater rater) {
        this.rater = rater;
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
        return "LabelSentence{" +
                "id_rater=" + id_rater +
                ", id_post=" + id_post +
                ", id_sentence=" + id_sentence +
                ", sentence=" + sentence +
                ", rater=" + rater +
                ", SP=" + SP +
                ", TP=" + TP +
                ", CP=" + CP +
                '}';
    }
}
