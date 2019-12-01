package com.bis.blog.featuretoggle.repository;

import com.bis.blog.featuretoggle.domain.FeatureToggle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FeatureToggleRepository extends JpaRepository<FeatureToggle, Long> {

    Optional<FeatureToggle> findByName(String name);

    Optional<FeatureToggle> findById(Long id);

    List<FeatureToggle> findAll();

}
