package my.zin.rashidi.data.solr.web;

import my.zin.rashidi.data.solr.Application;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentation;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Rashidi Zin
 */
@WebIntegrationTest(randomPort = true)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class PersonResourceTests {

    @Rule
    public final RestDocumentation restDocumentation = new RestDocumentation("target/generated-snippets");

    @Autowired
    private WebApplicationContext context;

    @Value("${local.server.port}")
    private int port;

    private MockMvc mockMvc;

    @Autowired
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(
                documentationConfiguration(restDocumentation)
        ).build();
    }

    @Test
    public void findByName() throws Exception {
        mockMvc.perform(
                get("http://localhost:" + port + "/person")
                        .param("name", "Bruce Wayne")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", is("Bruce Wayne")))
                .andExpect(jsonPath("location", is("Gotham City")))
                .andDo(document("person-example",
                        requestParameters(
                                parameterWithName("name").description("Name of the person you are looking for")
                        ),
                        responseFields(
                                fieldWithPath("name").description("Name of the person you are looking for"),
                                fieldWithPath("location").description("Current location of the person")
                        )
                ));
    }
}
