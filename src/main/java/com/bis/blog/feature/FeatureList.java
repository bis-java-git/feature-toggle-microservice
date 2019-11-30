package com.bis.blog.feature;

import java.io.Serializable;
import java.util.List;

public class FeatureList implements Serializable {

    private List<Feature> features;

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    @Override
    public String toString() {
        return "FeatureList{" +
                "features=" + features +
                '}';
    }
}
