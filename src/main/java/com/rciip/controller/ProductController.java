package com.rciip.controller;

import com.rciip.model.Product;
import com.rciip.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.result.FacetFieldEntry;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by ZJM on 2018/4/7.
 */
@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/")
    public String toSearch() {
        return "search";
    }

    @RequestMapping("/showOne")
    public String showOne(Model model, String id) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "product";
    }

    @RequestMapping("/search")
    public String search(Model model, @RequestParam(value = "q", required = false) String query,
                         @PageableDefault(page = 0, size = ProductService.DEFAULT_PAGE_SIZE) Pageable pageable) {
        model.addAttribute("page", productService.findByName(query, pageable));
        model.addAttribute("pageable", pageable);
        model.addAttribute("query", query);
        return "list";
    }

    @ResponseBody
    @GetMapping(value = "/autocomplete", produces = "application/json")
    public Set<String> autoComplete(Model model, String term, @PageableDefault(page = 0, size = 1)Pageable pageable) {
        if (!StringUtils.hasText(term)) {
            return Collections.emptySet();
        }
        return productService.autocompleteNameFragment(term, pageable);
    }

}
























