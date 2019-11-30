package com.bis.blog.feature;

import com.bis.blog.FeatureToggleApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/features")
class FeatureController {

    private static final Logger log = LoggerFactory.getLogger(FeatureController.class);


    @Autowired
    private FeatureService featureService;


    @GetMapping
    private Map<String, Boolean> listFeatures() {
        log.info("list features");
        return featureService.findAll().stream().collect(Collectors.toMap(Feature::getName, Feature::getEnabled));
    }

    @PostMapping
    private String insertFeature(@RequestBody Feature feature) {
        featureService.saveFeature(new Feature(feature.getName(), feature.getEnabled()));
        log.info("name: {} enabled: {}" ,feature.getName(),feature.getEnabled());
        return "Success....";
    }

    @DeleteMapping
    private void deleteFeature (@RequestBody Feature feature) {
        featureService.deleteFeature(feature);
    }
}
