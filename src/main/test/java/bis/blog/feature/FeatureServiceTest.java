package bis.blog.feature;

import com.bis.blog.FeatureToggleApplication;
import com.bis.blog.feature.Feature;
import com.bis.blog.feature.FeatureService;
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
public class FeatureServiceTest {

    @Autowired
    private FeatureService featureService;

    @Test
    public void testFindFeatureByName() {

        featureService.saveFeature(new Feature("F1", true));
        featureService.saveFeature(new Feature("F2", false));

        Optional<Feature> features = featureService.findByName("F1");
        assertEquals(true, features.isPresent());
        assertTrue(features.get().getName().contentEquals("F1"));

    }

    @Test
    public void testFindAllFeatures() {

        featureService.saveFeature(new Feature("F1", true));
        featureService.saveFeature(new Feature("F2", false));

        List<Feature> all = featureService.findAll();
        assertEquals(2, all.size());
        assertTrue(all.get(0).getName().contentEquals("F1"));

    }

    @Test
    public void testDeleteFeature() {

        featureService.saveFeature(new Feature("F1", true));
        featureService.saveFeature(new Feature("F2", false));

        featureService.deleteFeature(new Feature("F2", false));
        List<Feature> all = featureService.findAll();
        assertEquals(1, all.size());
        assertTrue(all.get(0).getName().contentEquals("F1"));

    }

    @Test
    public void testSaveFeature() {

        featureService.saveFeature(new Feature("F1", true));
        featureService.saveFeature(new Feature("F2", false));

        List<Feature> all = featureService.findAll();
        assertEquals(2, all.size());
        assertTrue(all.get(0).getName().contentEquals("F1"));

    }
}
