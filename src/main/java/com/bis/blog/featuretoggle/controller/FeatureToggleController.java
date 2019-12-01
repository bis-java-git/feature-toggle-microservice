package com.bis.blog.featuretoggle.controller;

import com.bis.blog.featuretoggle.domain.FeatureToggle;
import com.bis.blog.featuretoggle.service.FeatureToggleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feature-toggle")
class FeatureToggleController {

    private static final Logger log = LoggerFactory.getLogger(FeatureToggleController.class);


    @Autowired
    private FeatureToggleService featureToggleService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    private @ResponseBody List<FeatureToggle> listFeatures() {
        log.info("list feature toggles");
        // return featureToggleService.findAll().stream().collect(Collectors.toMap(FeatureToggle::getName, FeatureToggle::getEnabled));
        return featureToggleService.findAll();
    }

    @PostMapping
    private @ResponseBody FeatureToggle insertFeature(@RequestBody FeatureToggle featureToggle) {
        FeatureToggle savedFeatureToggle = featureToggleService.saveFeature(new FeatureToggle(featureToggle.getName(), featureToggle.getEnabled()));
        log.info("name: {} enabled: {}", featureToggle.getName(), featureToggle.getEnabled());
        return savedFeatureToggle;
    }

    @DeleteMapping(value="{id}")
    private void deleteFeature(@PathVariable Long id) {
        //TODO Error Handling
        featureToggleService.deleteFeature(id);
    }

    @PutMapping
    @ResponseBody private void updateFeature(@RequestBody FeatureToggle featureToggle) {
        //TODO Error Handling
        featureToggleService.saveFeature(new FeatureToggle(featureToggle.getName(), featureToggle.getEnabled()));
        log.info("name: {} enabled: {}", featureToggle.getName(), featureToggle.getEnabled());
    }
}
