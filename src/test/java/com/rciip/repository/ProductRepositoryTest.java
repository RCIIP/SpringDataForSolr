package com.rciip.repository;

import com.rciip.model.Product;
import com.rciip.repository.ProductRepository;
import com.rciip.util.InputPreprocessor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

/**
 * Created by ZJM on 2018/4/5.
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void findById() {
        Optional<Product> T = productRepository.findById("MA147LL/A");
        System.out.println(T);
    }

    @Test
    public void search() {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(0, 10, sort);
        HighlightPage<Product> T = productRepository.findByNameIn(InputPreprocessor.splitSearchTermAndRemoveIgnoredCharacters("id:MA147LL/A"), pageable);
        System.out.println(T.getContent());
    }

}