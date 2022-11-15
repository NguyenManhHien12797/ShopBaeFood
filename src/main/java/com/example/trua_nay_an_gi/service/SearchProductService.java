package com.example.trua_nay_an_gi.service;

import com.example.trua_nay_an_gi.model.product.Category;
import com.example.trua_nay_an_gi.model.product.Product;
import com.example.trua_nay_an_gi.model.search_form.SearchForm;
import com.example.trua_nay_an_gi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchProductService {
    @Autowired
    private ProductRepository productRepository;

    public Iterable<Product> findAllProduct(int limit) {
        return productRepository.findAllProduct(limit);
    }
    public Iterable<Product> searchByForm(SearchForm searchForm) {
        if (searchForm.getCategories().size() == 0) {
            return searchByNameOnly(searchForm.getQ(), searchForm.getLimit());
        }
        if (searchForm.getQ().isEmpty()){
            return searchByCategoriesOnly(searchForm.getCategories(), searchForm.getLimit());
        }
        return searchByNameAndCategories(searchForm.getQ(), searchForm.getCategories(), searchForm.getLimit());
    }

    private Iterable<Product> searchByNameOnly(String name, int limit) {
        if (name.isEmpty()){
            return findAllProduct(limit);
        }
        String namePattern = "%" + name +"%";
        return productRepository.findAllProductWithName(namePattern, limit);
    }

    public Iterable<Product> searchByCategoriesOnly(List<Category> categories, int limit) {
        String categoryIdList = generateCategoryIdListString (categories);
        Iterable<Product> result = productRepository.findProductByCategoryIdList(categoryIdList, limit);
        return result;
    }

    public Iterable<Product> searchByNameAndCategories(String name, List<Category> categories, int limit) {
        String namePattern = "%" + name + "%";
        String categoryIdList = generateCategoryIdListString(categories);
        return productRepository.findProductByNameAndCategoryIdList(namePattern, categoryIdList, limit);
    }

    public String generateCategoryIdListString(List<Category> categories) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < categories.size(); i++) {
            result.append(categories.get(i).getId().toString());
            if (i < categories.size() - 1) {
                result.append(",");
            }
        }
        return result.toString();
    }
}
