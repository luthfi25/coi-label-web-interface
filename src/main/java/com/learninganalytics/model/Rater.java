package com.learninganalytics.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

/**
 * Created by Luthfi on 17/03/2017.
 */

@Entity
@Table(name = "`coi-rater`")
public class Rater {
    @Id
    @NotEmpty(message = "*Please provide a nickname")
    @Column(name = "id")
    private String id;
    @NotEmpty(message = "*Please provide a name")
    @Column(name = "name")
    private String name;
    @Length(min = 5, message = "*Your password must have at least 5 characters")
    @NotEmpty(message = "*Please provide a password")
    @Transient
    @Column(name = "password")
    private String password;
    @Column(name = "type_id")
    private int type_id;
    @ManyToOne
    @JoinColumn(insertable=false, updatable=false, name = "type_id", referencedColumnName = "id")
    private RaterType raterType;
    @OneToMany(mappedBy = "rater")
    private List<LabelSentence> labelSentence;

    public Rater() {
    }

    public Rater(String id, String name, String password, int type_id, RaterType raterType, List<LabelSentence> labelSentence) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.type_id = type_id;
        this.raterType = raterType;
        this.labelSentence = labelSentence;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public RaterType getRaterType() {
        return raterType;
    }

    public void setRaterType(RaterType raterType) {
        this.raterType = raterType;
    }

    public List<LabelSentence> getLabelSentence() {
        return labelSentence;
    }

    public void setLabelSentence(List<LabelSentence> labelSentence) {
        this.labelSentence = labelSentence;
    }

    @Override
    public String toString() {
        return "Rater{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", type_id=" + type_id +
                ", raterType=" + raterType +
                '}';
    }
}
