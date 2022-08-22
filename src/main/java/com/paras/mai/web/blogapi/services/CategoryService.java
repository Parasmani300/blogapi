package com.paras.mai.web.blogapi.services;

import com.paras.mai.web.blogapi.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {

//    create
    public CategoryDto createCategory(CategoryDto categoryDto);

//    update
    public CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);

//    delete
    public void deleteCategory(Integer categoryId);


//    read
    CategoryDto getCategory(Integer categoryId);

//   read all
    List<CategoryDto> getCategories();
}
