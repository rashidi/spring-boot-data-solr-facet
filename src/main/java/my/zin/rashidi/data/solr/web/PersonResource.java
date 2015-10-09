package my.zin.rashidi.data.solr.web;

import my.zin.rashidi.data.solr.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Rashidi Zin
 */
@RestController
@RequestMapping(value = "person", produces = MediaType.APPLICATION_JSON_VALUE)
public class PersonResource {

    @Autowired
    private PersonService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity find(@RequestParam("name") String name) {
        return new ResponseEntity(service.findByName(name), HttpStatus.OK);
    }
}
