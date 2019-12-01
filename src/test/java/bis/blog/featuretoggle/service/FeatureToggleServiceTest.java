package bis.blog.featuretoggle.service;

import com.bis.blog.FeatureToggleApplication;
import com.bis.blog.featuretoggle.domain.FeatureToggle;
import com.bis.blog.featuretoggle.service.FeatureToggleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FeatureToggleApplication.class)
@Transactional
public class FeatureToggleServiceTest {

    @Autowired
    private FeatureToggleService featureToggleService;

    @Test
    public void testFindFeatureByName() {

        featureToggleService.saveFeature(new FeatureToggle("F1", true));
        featureToggleService.saveFeature(new FeatureToggle("F2", false));

        Optional<FeatureToggle> features = featureToggleService.findByName("F1");
        assertEquals(true, features.isPresent());
        assertTrue(features.get().getName().contentEquals("F1"));

    }

    @Test
    public void testFindAllFeatures() {

        featureToggleService.saveFeature(new FeatureToggle("F1", true));
        featureToggleService.saveFeature(new FeatureToggle("F2", false));

        List<FeatureToggle> all = featureToggleService.findAll();
        assertEquals(2, all.size());
        assertTrue(all.get(0).getName().contentEquals("F1"));

    }

    @Test
    public void testDeleteFeature() {

        FeatureToggle featureToggle1 = featureToggleService.saveFeature(new FeatureToggle("F1", true));
        featureToggleService.saveFeature(new FeatureToggle("F2", false));

        featureToggleService.deleteFeature(featureToggle1.getId());
        List<FeatureToggle> all = featureToggleService.findAll();
        assertEquals(1, all.size());
        assertTrue(all.get(0).getName().contentEquals("F1"));

    }

    @Test
    public void testSaveFeature() {

        featureToggleService.saveFeature(new FeatureToggle("F1", true));
        featureToggleService.saveFeature(new FeatureToggle("F2", false));

        List<FeatureToggle> all = featureToggleService.findAll();
        assertEquals(2, all.size());
        assertTrue(all.get(0).getName().contentEquals("F1"));

    }
}
