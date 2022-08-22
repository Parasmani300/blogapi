package com.paras.mai.web.blogapi.controller;

import com.paras.mai.web.blogapi.entities.Category;
import com.paras.mai.web.blogapi.payloads.ApiRsponse;
import com.paras.mai.web.blogapi.payloads.CategoryDto;
import com.paras.mai.web.blogapi.services.CategoryService;
import com.paras.mai.web.blogapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

//    create
    @PostMapping(value = "/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto)
    {
        CategoryDto createdCategory =  this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

//    update
    @PutMapping("/{catId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable Integer catId)
    {
        CategoryDto updatedCategory = this.categoryService.updateCategory(categoryDto,catId);

        return new ResponseEntity<>(updatedCategory,HttpStatus.OK);
    }

//    delete
    @DeleteMapping("/{catId}")
    public  ResponseEntity<?> deleteCategory(@PathVariable Integer catId)
    {
        this.categoryService.deleteCategory(catId);
        return new ResponseEntity<>(new ApiRsponse("Category Deleted Sucessdully",false),HttpStatus.OK);
    }

//    read
    @GetMapping("/{catId}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer catId)
    {
        CategoryDto catDto = this.categoryService.getCategory(catId);

        return new ResponseEntity<>(catDto,HttpStatus.OK);
    }

    @GetMapping("/")
    public  ResponseEntity<List<CategoryDto>> getAllCategories()
    {
        List<CategoryDto> categoryList = this.categoryService.getCategories();
        return new ResponseEntity<>(categoryList,HttpStatus.OK);
    }

}
