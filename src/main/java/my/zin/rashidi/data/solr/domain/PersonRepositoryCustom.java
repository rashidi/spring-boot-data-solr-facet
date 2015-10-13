package my.zin.rashidi.data.solr.domain;

import org.springframework.data.solr.core.query.result.GroupPage;

/**
 * @author Rashidi Zin
 */
public interface PersonRepositoryCustom {

    GroupPage<Person> findAllGroupByLocation();

}
