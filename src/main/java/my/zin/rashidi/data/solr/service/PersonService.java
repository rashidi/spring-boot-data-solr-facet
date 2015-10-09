package my.zin.rashidi.data.solr.service;

import my.zin.rashidi.data.solr.domain.Person;
import my.zin.rashidi.data.solr.domain.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author Rashidi Zin
 */
@Service
public class PersonService {

    @Autowired
    PersonRepository repository;

    public Person findByName(String name) {

        Assert.hasText(name, "can't find a person without a name!");

        return repository.findOneByName(name)
                .orElse(null);
    }
}
