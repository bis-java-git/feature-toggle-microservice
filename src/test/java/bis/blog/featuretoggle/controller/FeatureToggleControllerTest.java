package bis.blog.featuretoggle.controller;

import com.bis.blog.FeatureToggleApplication;
import com.bis.blog.featuretoggle.domain.FeatureToggle;
import com.bis.blog.featuretoggle.service.FeatureToggleService;
import com.fasterxml.jackson.core.type.TypeReference;
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
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FeatureToggleApplication.class)
@AutoConfigureMockMvc
@Transactional
@DirtiesContext
public class FeatureToggleControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private FeatureToggleService featureToggleService;

    @Before
    public void initializeFeatures() {
        featureToggleService.saveFeature(new FeatureToggle("F1", true));
        featureToggleService.saveFeature(new FeatureToggle("F2", false));
    }

    @After
    public void destroyFeatures() {
        featureToggleService.deleteAll();
    }

    public <T> List<T> jsonArrayToObjectList(String json, Class<T> tClass) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        CollectionType listType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, tClass);
        List<T> ts = mapper.readValue(json, listType);
        return ts;
    }


    @Test
    public void getAllFeatureTogglessWithActuatorTest() throws Exception {
        ResultActions perform = mvc.perform(MockMvcRequestBuilders.get("/actuator/feature-toggles").accept(MediaType.APPLICATION_JSON));
        MvcResult mvcResult = perform.andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(200, response.getStatus());

        ObjectMapper objectMapper = new ObjectMapper();
        List<FeatureToggle> featureToggleList = objectMapper.readValue(response.getContentAsString(), new TypeReference<List<FeatureToggle>>() { });
        assertEquals(2, featureToggleList.size());
        assertEquals("F1", featureToggleList.get(0).getName());
    }

    @Test
    public void getAllFeatureTogglesTest() throws Exception {
        ResultActions perform = mvc.perform(MockMvcRequestBuilders.get("/feature-toggle").accept(MediaType.APPLICATION_JSON));
        MvcResult mvcResult = perform.andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(200, response.getStatus());

        ObjectMapper objectMapper = new ObjectMapper();
        List<FeatureToggle> featureToggleList = objectMapper.readValue(response.getContentAsString(), new TypeReference<List<FeatureToggle>>() { });
        assertEquals(2, featureToggleList.size());
        assertEquals("F1", featureToggleList.get(0).getName());
    }

    @Test
    public void deleteFeatureToggleTest() throws Exception {
        ResultActions perform = mvc.perform(MockMvcRequestBuilders.get("/feature-toggle").accept(MediaType.APPLICATION_JSON));

        MvcResult mvcResult = perform.andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(200, response.getStatus());

        ObjectMapper objectMapper = new ObjectMapper();
        List<FeatureToggle> featureToggleList = objectMapper.readValue(response.getContentAsString(), new TypeReference<List<FeatureToggle>>() { });
        assertEquals(2, featureToggleList.size());
        assertEquals("F1", featureToggleList.get(0).getName());
    }
}
