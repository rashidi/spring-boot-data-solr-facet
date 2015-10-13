package my.zin.rashidi.data.solr.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.*;
import org.springframework.data.solr.core.query.result.GroupPage;

/**
 * @author Rashidi Zin
 */
public class PersonRepositoryImpl implements PersonRepositoryCustom {

    @Autowired
    private SolrTemplate solrTemplate;

    @Override
    public GroupPage<Person> findAllGroupByLocation() {
        Field field = new SimpleField(Person.FIELD_LOCATION);

        SimpleQuery query = new SimpleQuery(new SimpleStringCriteria("*:*"));
        GroupOptions options = new GroupOptions()
                .addGroupByField(field);

        query.setGroupOptions(options);

        GroupPage<Person> page = solrTemplate.queryForGroupPage(query, Person.class);
        return page;
    }
}
