package com.rciip.repository;

import com.rciip.model.Product;
import com.rciip.util.SearchableProductDefinition;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.data.solr.repository.Facet;
import org.springframework.data.solr.repository.Highlight;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.data.solr.core.query.Query.Operator;

import java.util.Collection;

/**
 * Created by ZJM on 2018/4/5.
 */
public interface ProductRepository extends SolrCrudRepository<Product, String> {

    @Highlight(prefix = "<b class='text-danger'>", postfix = "</b>")
    @Query(fields = {SearchableProductDefinition.ID_FIELD_NAME, SearchableProductDefinition.AVAILABLE_FIELD_NAME,
    SearchableProductDefinition.CATEGORIES_FIELD_NAME, SearchableProductDefinition.FEATURES_FIELD_NAME, SearchableProductDefinition.LOCATION_FIELD_NAME,
    SearchableProductDefinition.NAME_FIELD_NAME, SearchableProductDefinition.PRICE_FIELD_NAME, "title"}, defaultOperator = Operator.AND)
    HighlightPage<Product> findByNameIn(Collection<String> names, Pageable page);

    @Facet(fields = { SearchableProductDefinition.NAME_FIELD_NAME })
    FacetPage<Product> findByNameStartsWith(Collection<String> nameFragements, Pageable pageable);

}
