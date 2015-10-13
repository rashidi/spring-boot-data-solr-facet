package my.zin.rashidi.data.solr.configuration;

import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;
import org.springframework.data.solr.server.SolrServerFactory;
import org.springframework.data.solr.server.support.MulticoreSolrServerFactory;

/**
 * @author Rashidi Zin
 */
@Configuration
@EnableSolrRepositories(value = "my.zin.rashidi.data.solr.domain")
public class CustomSolrConfiguration {

    @Value("${spring.data.solr.host}")
    private String solrHost;

    @Bean
    public SolrServerFactory solrServerFactory() {
        return new MulticoreSolrServerFactory(new HttpSolrServer(solrHost));
    }

    @Bean
    public SolrTemplate solrTemplate() throws Exception {
        SolrTemplate solrTemplate = new SolrTemplate(solrServerFactory());
        solrTemplate.setSolrCore("person");
        return solrTemplate;
    }

}
