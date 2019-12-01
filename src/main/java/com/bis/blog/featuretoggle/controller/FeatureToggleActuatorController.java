package com.bis.blog.featuretoggle.controller;

import com.bis.blog.featuretoggle.domain.FeatureToggle;
import com.bis.blog.featuretoggle.repository.FeatureToggleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Endpoint(id = "feature-toggles")
class FeatureToggleActuatorController {

    @Autowired
    private FeatureToggleRepository featureToggleRepository;

    @ReadOperation(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FeatureToggle> featureToggles() {

        return featureToggleRepository.findAll();
    }

}
