package com.bis.blog.demo;

import com.bis.blog.feature.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feature-demo")
class DemoController {

    @Autowired
    private FeatureService featureService;

    @GetMapping
    private String showCode() {
        if (featureService.findByName("hemant").isPresent() && featureService.findByName("hemant").get().getEnabled()) {
            return "Feature toggle is on, following code will be executed";
        }
        return "Default code is executed";
    }


}
