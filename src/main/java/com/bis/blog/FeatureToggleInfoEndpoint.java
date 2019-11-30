package com.bis.blog;

import com.bis.blog.feature.Feature;
import com.bis.blog.feature.FeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Endpoint(id = "feature-toggles")
class FeatureToggleInfoEndpoint {

    @Autowired
    private FeatureRepository featureRepository;

    @ReadOperation(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Feature> featureToggles() {

        return featureRepository.findAll();
    }

}
