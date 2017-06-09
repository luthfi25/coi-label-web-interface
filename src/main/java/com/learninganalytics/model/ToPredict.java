package com.learninganalytics.model;

import meka.classifiers.multilabel.BPNN;
import meka.core.MLUtils;
import org.springframework.beans.factory.annotation.Value;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;

/**
 * Created by Luthfi on 09/06/2017.
 */
public class ToPredict {
    private String document;

    public ToPredict(String document) throws Exception {
        this.document = document;
    }

    public ToPredict() throws Exception {
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    @Override
    public String toString() {
        return "ToPredict{" +
                "document='" + document + '\'' +
                '}';
    }
}
