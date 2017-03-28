package com.learninganalytics.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Luthfi on 17/03/2017.
 */

@Entity
@Table(name = "`coi-rater-type`")
public class RaterType {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "role")
    private String role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "RaterType{" +
            "id=" + id +
            ", role='" + role + '\'' +
            '}';
    }
}
