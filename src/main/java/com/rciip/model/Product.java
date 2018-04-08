package com.rciip.model;

import com.rciip.util.SearchableProductDefinition;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.util.List;

/**
 * Created by ZJM on 2018/4/5.
 */
@SolrDocument(solrCoreName = "collection1")
public class Product implements SearchableProductDefinition {

    private @Id @Indexed String id;

    private @Indexed(NAME_FIELD_NAME) String name;

    private @Indexed(AVAILABLE_FIELD_NAME) boolean available;

    private @Indexed(PRICE_FIELD_NAME) float price;

    private @Indexed List<String> features;

    private @Indexed("title") String title;

    // private @Indexed(PRICE_FIELD_NAME) GeoLocation location;

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

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public List<String> getFeatures() {
        return features;
    }

    public void setFeatures(List<String> features) {
        this.features = features;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    //    public GeoLocation getLocation() {
//        return location;
//    }
//
//    public void setLocation(GeoLocation location) {
//        this.location = location;
//    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", available=" + available +
                ", price=" + price +
                ", features=" + features +
                ", title='" + title + '\'' +
                '}';
    }
}
