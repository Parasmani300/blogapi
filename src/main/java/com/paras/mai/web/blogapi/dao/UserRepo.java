package com.paras.mai.web.blogapi.dao;

import com.paras.mai.web.blogapi.entities.User;
import com.paras.mai.web.blogapi.payloads.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Integer> {

    @Query(value = "Select * from Users order by email",nativeQuery = true)
    public Optional<List<User>> findUserOrderByNameAndEmail();

}
