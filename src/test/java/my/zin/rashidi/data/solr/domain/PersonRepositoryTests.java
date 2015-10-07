package my.zin.rashidi.data.solr.domain;

import my.zin.rashidi.data.solr.Application;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * @author Rashidi Zin
 */
@IntegrationTest
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class PersonRepositoryTests {

    @Autowired
    private PersonRepository repository;

    @Before
    public void init() {
        repository.save(new Person("Bruce Wayne", "Gotham City"));
        repository.save(new Person("Dick Grayson", "Gotham City"));
        repository.save(new Person("Clark Kent", "Metropolis"));
        repository.save(new Person("Conner Kent", "Metropolis"));
        repository.save(new Person("Barry Allen", "Central City"));
    }

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

    @After
    public void after() {
        repository.deleteAll();
    }
}
