package my.zin.rashidi.data.solr.domain;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.solr.core.query.result.GroupEntry;
import org.springframework.data.solr.core.query.result.GroupPage;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author Rashidi Zin
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonRepositoryImplTests {

    private final Collection<String> locations = Arrays.asList("Gotham City", "Metropolis", "Central City");
    @Autowired
    private PersonRepositoryCustom repository;

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
