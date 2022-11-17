//package com.example.trua_nay_an_gi.service;
//
//import com.example.trua_nay_an_gi.model.app_users.Merchant;
//import com.example.trua_nay_an_gi.model.product.Category;
//import com.example.trua_nay_an_gi.model.product.Product;
//import com.example.trua_nay_an_gi.model.search_form.SearchForm;
//import com.example.trua_nay_an_gi.repository.ProductRepository;
//import com.example.trua_nay_an_gi.repository.merchant.IMerchantRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class SearchMerchantService {
//    @Autowired
//    private IMerchantRepository merchantRepository;
//
//    public Iterable<Merchant> findAllMerchant(int limit) {
//        return merchantRepository.findAllMerchant(limit);
//    }
//    public Iterable<Merchant> searchByForm(SearchForm searchForm) {
//        if (searchForm.getCategories().size() == 0) {
//            return searchByNameOnly(searchForm.getQ(), searchForm.getLimit());
//        }
//        if (searchForm.getQ().isEmpty()){
//            return searchByCategoriesOnly(searchForm.getCategories(), searchForm.getLimit());
//        }
//        return searchByNameAndCategories(searchForm.getQ(), searchForm.getCategories(), searchForm.getLimit());
//    }
//
//    private Iterable<Merchant> searchByNameOnly(String name, int limit) {
//        if (name.isEmpty()){
//            return findAllMerchant(limit);
//        }
//        String namePattern = "%" + name +"%";
//        return merchantRepository.findAllMerchantWithName(namePattern, limit);
//    }
//
//    public Iterable<Merchant> searchByCategoriesOnly(List<Category> categories, int limit) {
//        String categoryIdList = generateCategoryIdListString (categories);
//        Iterable<Merchant> result = merchantRepository.findMerchantByCategoryIdList(categoryIdList, limit);
//        return result;
//    }
//
//    public Iterable<Merchant> searchByNameAndCategories(String name, List<Category> categories, int limit) {
//        String namePattern = "%" + name + "%";
//        String categoryIdList = generateCategoryIdListString(categories);
//        return merchantRepository.findMerchantByNameAndCategoryIdList(namePattern, categoryIdList, limit);
//    }
//
//    public String generateCategoryIdListString(List<Category> categories) {
//        StringBuilder result = new StringBuilder();
//        for (int i = 0; i < categories.size(); i++) {
//            result.append(categories.get(i).getId().toString());
//            if (i < categories.size() - 1) {
//                result.append(",");
//            }
//        }
//        return result.toString();
//    }
//}
