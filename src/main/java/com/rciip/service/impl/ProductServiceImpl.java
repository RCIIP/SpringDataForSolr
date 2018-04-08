package com.rciip.service.impl;

import com.rciip.model.Product;
import com.rciip.repository.ProductRepository;
import com.rciip.service.ProductService;
import com.rciip.util.InputPreprocessor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.result.FacetFieldEntry;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.data.solr.core.query.result.SolrResultPage;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by ZJM on 2018/4/7.
 */
@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Product> findByName(String searchTerm, Pageable pageable) {
        if (StringUtils.isBlank(searchTerm)) {
            return productRepository.findAll(pageable);
        }
        return productRepository.findByNameIn(preprocessor(searchTerm), pageable);
    }

    @Override
    public Product findById(String id) {
        Optional<Product> optional = productRepository.findById(id);
        // 检查 id 的合法性
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public Set<String> autocompleteNameFragment(String fragment, Pageable pageable) {
        Set<String> titles = new LinkedHashSet<String>();
        if (StringUtils.isBlank(fragment)) {
            return titles;
        }
        FacetPage<Product> result = productRepository.findByNameStartsWith(InputPreprocessor.splitSearchTermAndRemoveIgnoredCharacters(fragment), pageable);
        for (Page<FacetFieldEntry> page : result.getFacetResultPages()) {
            for (FacetFieldEntry entry : page) {
                if (entry.getValue().contains(fragment)) {
                    titles.add(entry.getValue());
                }
            }
        }
        return titles;
    }

    private Collection<String> preprocessor(String input) {
        return InputPreprocessor.splitSearchTermAndRemoveIgnoredCharacters(input);
    }
}

















