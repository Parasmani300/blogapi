package com.paras.mai.web.blogapi.dao;


import com.paras.mai.web.blogapi.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Integer> {
}
