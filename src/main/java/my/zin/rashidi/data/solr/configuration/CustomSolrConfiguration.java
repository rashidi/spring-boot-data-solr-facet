package my.zin.rashidi.data.solr.configuration;

import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;
import org.springframework.data.solr.server.SolrClientFactory;
import org.springframework.data.solr.server.support.MulticoreSolrClientFactory;

/**
 * @author Rashidi Zin
 */
@Configuration
@EnableSolrRepositories(value = "my.zin.rashidi.data.solr.domain", schemaCreationSupport = true, multicoreSupport = true)
public class CustomSolrConfiguration {

    @Value("${spring.data.solr.host}")
    private String solrHost;

    @Bean
    public SolrClientFactory solrServerFactory() {
        return new MulticoreSolrClientFactory(new HttpSolrClient(solrHost));
    }

    @Bean
    public SolrTemplate solrTemplate() throws Exception {
        SolrTemplate solrTemplate = new SolrTemplate(solrServerFactory());
        solrTemplate.setSolrCore("person");
        return solrTemplate;
    }

}
