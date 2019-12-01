package com.bis.blog.featuretoggle.service;

import com.bis.blog.featuretoggle.domain.FeatureToggle;
import com.bis.blog.featuretoggle.repository.FeatureToggleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeatureToggleService {

    @Autowired
    private FeatureToggleRepository featureToggleRepository;

    public FeatureToggle saveFeature(FeatureToggle f) {
        Optional<FeatureToggle> retrievedFeature = featureToggleRepository.findByName(f.getName());
        if (retrievedFeature.isPresent()) {
            retrievedFeature.get().setEnabled(f.getEnabled());
            return featureToggleRepository.save(retrievedFeature.get());
        } else {
            return featureToggleRepository.save(f);
        }
    }

    public List<FeatureToggle> findAll() {
        return featureToggleRepository.findAll();
    }

    public Optional<FeatureToggle> findByName(String name) {
        return featureToggleRepository.findByName(name);
    }

    public void deleteFeature(final Long id) {
        Optional<FeatureToggle> featureToBeDeleted = featureToggleRepository.findById(id);
        if (featureToBeDeleted.isPresent()) {
            featureToggleRepository.delete(featureToBeDeleted.get());
        }
    }

    public void deleteAll() {
        featureToggleRepository.deleteAll();
    }
}
