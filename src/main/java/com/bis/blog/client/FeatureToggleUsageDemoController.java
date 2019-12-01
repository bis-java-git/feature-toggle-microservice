package com.bis.blog.client;

import com.bis.blog.featuretoggle.service.FeatureToggleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feature-demo")
class FeatureToggleUsageDemoController {

    @Autowired
    private FeatureToggleService featureToggleService;

    @GetMapping
    private String showCode() {
        if (featureToggleService.findByName("hemant").isPresent() && featureToggleService.findByName("hemant").get().getEnabled()) {
            return "Feature toggle is on, following code will be executed";
        }
        return "Default code is executed";
    }


}
