package com.example.smart.expense.splitter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.smart.expense.splitter.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}