package com.example.trua_nay_an_gi.model.search_form;

import com.example.trua_nay_an_gi.model.product.Category;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class SearchForm {
    private String q;
    private List<Category> categories = new ArrayList<>();
    private int limit;
}
