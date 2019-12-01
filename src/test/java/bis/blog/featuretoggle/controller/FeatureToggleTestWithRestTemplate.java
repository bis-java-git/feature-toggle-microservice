package bis.blog.featuretoggle.controller;

import com.bis.blog.FeatureToggleApplication;
import com.bis.blog.featuretoggle.domain.FeatureToggle;
import com.bis.blog.featuretoggle.service.FeatureToggleService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DirtiesContext
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = FeatureToggleApplication.class)
public class FeatureToggleTestWithRestTemplate {

    @LocalServerPort
    private int randomServerPort;

    @Autowired
    private FeatureToggleService featureToggleService;

    private RestTemplate restTemplate = new RestTemplate();

    private String baseUrl;

    @Before
    public void initializeFeatures() {
        featureToggleService.saveFeature(new FeatureToggle("F1", true));
        featureToggleService.saveFeature(new FeatureToggle("F2", false));
        baseUrl =  "http://localhost:" + randomServerPort + "/feature-toggle";
    }

    @After
    public void destroyFeatures() {
        featureToggleService.deleteAll();
    }

    @Test
    public void getAllFeatureTogglesTest() throws Exception {

        ResponseEntity<List<FeatureToggle>> response = restTemplate.exchange(
                baseUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<FeatureToggle>>() {
                });
        assertTrue(response.getStatusCode().is2xxSuccessful());
        List<FeatureToggle> featureToggles = response.getBody();

        assertEquals(2, featureToggles.size());
        assertEquals("F1", featureToggles.get(0).getName());
    }

    @Test
    public void deleteFeatureTogglesTest() throws Exception {

        List<FeatureToggle> featureToggleList = featureToggleService.findAll();
        assertEquals(2, featureToggleList.size());

        FeatureToggle featureToggle = featureToggleList.get(0);
        assertNotNull(featureToggle);

        String deleterUrl = baseUrl + "/" + featureToggle.getId() + "/";
        restTemplate.delete(deleterUrl);

        featureToggleList = featureToggleService.findAll();
        assertEquals(1, featureToggleList.size());

    }

    @Test
    public void updateFeatureTogglesTest() throws Exception {

        List<FeatureToggle> featureToggleList = featureToggleService.findAll();
        assertEquals(2, featureToggleList.size());

        FeatureToggle featureToggle = featureToggleList.get(0);
        assertNotNull(featureToggle);
        featureToggle.setEnabled(Boolean.FALSE);

        restTemplate.put(baseUrl, featureToggle);

        featureToggleList = featureToggleService.findAll();
        assertEquals(2, featureToggleList.size());

        featureToggle = featureToggleList.get(0);
        assertNotNull(featureToggle);
        assertFalse(featureToggle.getEnabled());

    }

    @Test
    public void createNewFeatureTogglesTest() throws Exception {

        List<FeatureToggle> featureToggleList = featureToggleService.findAll();
        assertEquals(2, featureToggleList.size());

        restTemplate.postForObject(baseUrl, new FeatureToggle("hemant", true), FeatureToggle.class);

        featureToggleList = featureToggleService.findAll();
        assertEquals(3, featureToggleList.size());

        FeatureToggle featureToggle = featureToggleList.get(2);
        assertNotNull(featureToggle);
        assertTrue(featureToggle.getEnabled());
        assertEquals("hemant", featureToggle.getName());

    }
}
