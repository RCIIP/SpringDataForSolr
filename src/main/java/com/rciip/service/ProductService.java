package com.rciip.service;

import com.rciip.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

/**
 * Created by ZJM on 2018/4/7.
 */
public interface ProductService {

    int DEFAULT_PAGE_SIZE = 10;

    Page<Product> findByName(String searchTerm, Pageable pageable);

    Product findById(String id);

    Set<String> autocompleteNameFragment(String fragment, Pageable pageable);

}










