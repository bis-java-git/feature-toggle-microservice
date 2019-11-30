package com.bis.blog.feature;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FeatureRepository extends JpaRepository<Feature, Long> {

    Optional<Feature> findByName(String name);

    List<Feature> findAll();

}
