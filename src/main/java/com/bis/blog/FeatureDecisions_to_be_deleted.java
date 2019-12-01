package com.bis.blog;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@ConfigurationProperties("feature")
public class FeatureDecisions_to_be_deleted {

    private Map<String, Boolean> toggles = new HashMap<>();

    public Map<String, Boolean> getToggles() {
        return toggles;
    }

    public boolean isFeatureEnabled(String name) {
        return toggles.getOrDefault(name, false);
    }

    public void setNewFeature(String name, Boolean enabled) {
        toggles.put(name,enabled);
    }

}