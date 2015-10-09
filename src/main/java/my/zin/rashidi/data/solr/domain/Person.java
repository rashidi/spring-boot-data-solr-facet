package my.zin.rashidi.data.solr.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

/**
 * @author Rashidi Zin
 */
@SolrDocument(solrCoreName = "person")
public class Person {

    public final static String FIELD_LOCATION = "location";

    @Id
    @Field("_id")
    @JsonIgnore
    private String id;

    @Field
    private String name;

    @Field
    private String location;

    public Person() {
    }

    public Person(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
