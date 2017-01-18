package my.zin.rashidi.data.solr.service;

import my.zin.rashidi.data.solr.domain.Person;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Rashidi Zin
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonServiceTests {

    @Rule
    public ExpectedException expect = ExpectedException.none();

    @Autowired
    private PersonService service;

    @Test
    public void findByName() {
        Person batman = service.findByName("Bruce Wayne");

        Assert.assertEquals(
            "Gotham City", batman.getLocation()
        );

        Assert.assertNull(
            service.findByName("Selina Kyle")
        );
    }

    @Test
    public void findByNameWithoutInput() {
        expect.expect(IllegalArgumentException.class);
        expect.expectMessage("can't find a person without a name!");

        service.findByName(null);
        service.findByName(StringUtils.EMPTY);
    }
}
