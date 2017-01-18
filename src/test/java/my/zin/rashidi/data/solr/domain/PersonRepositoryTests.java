package my.zin.rashidi.data.solr.domain;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * @author Rashidi Zin
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonRepositoryTests {

    @Autowired
    private PersonRepository repository;

    @Test
    public void findAllAndFacetByLocation() {
        Collection<String> locations = new ArrayList<>();

        Pageable pageable = new PageRequest(0, 10);
        FacetPage<Person> results = repository.findAllAndFacetByLocation(pageable);

        results.getFacetResultPage(Person.FIELD_LOCATION).getContent().forEach(
            content -> locations.add(content.getValue())
        );

        Assert.assertEquals(3, locations.size());
        Assert.assertTrue(
            locations.containsAll(
                Arrays.asList("Gotham City", "Metropolis", "Central City")
            )
        );
    }

    @Test
    public void findOneByName() {
        Assert.assertTrue(
            repository.findOneByName("Bruce Wayne").isPresent()
        );

        Assert.assertFalse(
            repository.findOneByName("Alfred Pennyworth").isPresent()
        );
    }

}
