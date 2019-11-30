package com.bis.blog.demo;

import com.bis.blog.feature.Feature;
import com.bis.blog.feature.FeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class DataLoader {

    @Autowired
    private FeatureRepository featureRepository;

    @PostConstruct
    public void loadFeatures() {
//        featureRepository.save(new Feature("F1", true));
//        featureRepository.save(new Feature("F2", false));
//        featureRepository.save(new Feature("F3", true));
    }

    @PreDestroy
    public void removeFeatures() {
//        featureRepository.delete(new Feature("F1", true));
//        featureRepository.delete(new Feature("F2", false));
//        featureRepository.delete(new Feature("F3", true));
    }
}
