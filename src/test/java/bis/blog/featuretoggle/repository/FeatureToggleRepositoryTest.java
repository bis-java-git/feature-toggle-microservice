package bis.blog.featuretoggle.repository;

import com.bis.blog.FeatureToggleApplication;
import com.bis.blog.featuretoggle.domain.FeatureToggle;
import com.bis.blog.featuretoggle.repository.FeatureToggleRepository;
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
public class FeatureToggleRepositoryTest {

    @Autowired
    private FeatureToggleRepository featureToggleRepository;

    @Test
    public void testFindFeatureByName() {

        featureToggleRepository.save(new FeatureToggle("F1", true));
        featureToggleRepository.save(new FeatureToggle("F2", false));

        Optional<FeatureToggle> features = featureToggleRepository.findByName("F1");
        assertEquals(true, features.isPresent());
        assertTrue(features.get().getName().contentEquals("F1"));

    }

    @Test
    public void testFindAllFeatures() {

        featureToggleRepository.save(new FeatureToggle("F1", true));
        featureToggleRepository.save(new FeatureToggle("F2", false));

        List<FeatureToggle> all = featureToggleRepository.findAll();
        assertEquals(2, all.size());
        assertTrue(all.get(0).getName().contentEquals("F1"));

    }

}
