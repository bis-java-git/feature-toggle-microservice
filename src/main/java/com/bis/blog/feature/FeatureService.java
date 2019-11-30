package com.bis.blog.feature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeatureService {

    @Autowired
    private FeatureRepository featureRepository;

    public void saveFeature(Feature f) {
        Optional<Feature> retrievedFeature = featureRepository.findByName(f.getName());
        if (retrievedFeature.isPresent()) {
            retrievedFeature.get().setEnabled(f.getEnabled());
            featureRepository.save(retrievedFeature.get());
        } else {
            featureRepository.save(f);
        }
    }


    public List<Feature> findAll() {
        return featureRepository.findAll();
    }

    public Optional<Feature> findByName(String name) {
        return featureRepository.findByName(name);
    }

    public void deleteFeature(Feature feature) {
        Optional<Feature> featureToBeDeleted = featureRepository.findByName(feature.getName());
        if (featureToBeDeleted.isPresent()) {
            featureRepository.delete(featureToBeDeleted.get());
        }
    }
}
