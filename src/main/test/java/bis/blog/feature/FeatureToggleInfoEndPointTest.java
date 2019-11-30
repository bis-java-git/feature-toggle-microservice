package bis.blog.feature;

import com.bis.blog.FeatureToggleApplication;
import com.bis.blog.feature.Feature;
import com.bis.blog.feature.FeatureList;
import com.bis.blog.feature.FeatureService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FeatureToggleApplication.class)
@AutoConfigureMockMvc
@Transactional
@DirtiesContext
public class FeatureToggleInfoEndPointTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private FeatureService featureService;

    @Before
    public void initializeFeatures() {
        featureService.saveFeature(new Feature("F1", true));
        featureService.saveFeature(new Feature("F2", false));
    }

    @After
    public void destroyFeatures() {
        featureService.deleteFeature(new Feature("F1", true));
        featureService.deleteFeature(new Feature("F2", false));
    }

    public <T> List<T> jsonArrayToObjectList(String json, Class<T> tClass) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        CollectionType listType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, tClass);
        List<T> ts = mapper.readValue(json, listType);
        return ts;
    }


    @Test
    public void getAllFeatures() throws Exception {
        ResultActions perform = mvc.perform(MockMvcRequestBuilders.get("/actuator/feature-toggles").accept(MediaType.APPLICATION_JSON));
        MvcResult mvcResult = perform.andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(200, response.getStatus());

        ObjectMapper objectMapper = new ObjectMapper();
        //objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<Feature> featureList = objectMapper.readValue(response.getContentAsString(), new TypeReference<List<Feature>>() { });
        assertEquals(2, featureList.size());
        assertEquals("F1", featureList.get(0).getName());
    }
}
