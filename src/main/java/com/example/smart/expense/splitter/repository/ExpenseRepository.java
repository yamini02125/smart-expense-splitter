package com.example.smart.expense.splitter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.smart.expense.splitter.model.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}