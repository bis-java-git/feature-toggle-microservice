package com.bis.blog.featuretoggle.domain;

import java.io.Serializable;
import java.util.List;

public class FeatureList_to_be_deleted implements Serializable {

    private List<FeatureToggle> featureToggles;

    public List<FeatureToggle> getFeatureToggles() {
        return featureToggles;
    }

    public void setFeatureToggles(List<FeatureToggle> featureToggles) {
        this.featureToggles = featureToggles;
    }

    @Override
    public String toString() {
        return "FeatureList{" +
                "features=" + featureToggles +
                '}';
    }
}
