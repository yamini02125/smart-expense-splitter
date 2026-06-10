package com.example.smart.expense.splitter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.smart.expense.splitter.model.LoginUser;

public interface LoginUserRepository extends JpaRepository<LoginUser, Long> {

    LoginUser findByEmail(String email);

}