package com.rciip.service;

import com.rciip.model.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

/**
 * Created by ZJM on 2018/4/8.
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    public void findByIdTest() {
        Product product = productService.findById("MA147LL/A");
        System.out.println(product);
    }

}
