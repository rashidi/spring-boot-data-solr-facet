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
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Rashidi Zin
 */
@WebIntegrationTest("server.port:0")
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
                MockMvcRestDocumentation.documentationConfiguration(restDocumentation)
        ).build();
    }

    @Test
    public void findByName() throws Exception {
        mockMvc.perform(
                RestDocumentationRequestBuilders.get("http://localhost:" + port + "/person?name=Bruce Wayne")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", is("Bruce Wayne")))
                .andExpect(jsonPath("location", is("Gotham City")));
    }
}
