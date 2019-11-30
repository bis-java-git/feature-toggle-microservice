package bis.blog.feature;

import com.bis.blog.FeatureToggleApplication;
import com.bis.blog.feature.Feature;
import com.bis.blog.feature.FeatureRepository;
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
public class FeatureRepositoryTest {

    @Autowired
    private FeatureRepository featureRepository;

    @Test
    public void testFindFeatureByName() {

        featureRepository.save(new Feature("F1", true));
        featureRepository.save(new Feature("F2", false));

        Optional<Feature> features = featureRepository.findByName("F1");
        assertEquals(true, features.isPresent());
        assertTrue(features.get().getName().contentEquals("F1"));

    }

    @Test
    public void testFindAllFeatures() {

        featureRepository.save(new Feature("F1", true));
        featureRepository.save(new Feature("F2", false));

        List<Feature> all = featureRepository.findAll();
        assertEquals(2, all.size());
        assertTrue(all.get(0).getName().contentEquals("F1"));

    }

}
