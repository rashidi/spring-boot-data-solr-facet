package my.zin.rashidi.data.solr.domain;

import my.zin.rashidi.data.solr.Application;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.solr.core.query.result.GroupEntry;
import org.springframework.data.solr.core.query.result.GroupPage;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author Rashidi Zin
 */
@IntegrationTest
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class PersonRepositoryImplTests {

    @Autowired
    private PersonRepositoryCustom repository;

    private final Collection<String> locations = Arrays.asList("Gotham City", "Metropolis", "Central City");

    @Test
    public void findAllGroupByLocation() {
        GroupPage<Person> page = repository.findAllGroupByLocation();
        Page<GroupEntry<Person>> entries = page.getGroupResult(Person.FIELD_LOCATION).getGroupEntries();

        Assert.assertEquals(3, entries.getTotalElements());

        entries.forEach(location -> Assert.assertTrue(
                locations.contains(location.getGroupValue())
        ));
    }
}
