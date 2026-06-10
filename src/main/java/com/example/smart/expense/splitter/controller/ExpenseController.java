
package com.example.smart.expense.splitter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.smart.expense.splitter.model.Expense;
import com.example.smart.expense.splitter.repository.ExpenseRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseRepository expenseRepository;

    // Add Expense
    @PostMapping
    public Expense addExpense(@RequestBody Expense expense) {

        expense.setCreatedAt(LocalDateTime.now());

        return expenseRepository.save(expense);
    }

    // Get All Expenses
    @GetMapping
    public List<Expense> getAllExpenses() {

        return expenseRepository.findAll();
    }

    // Update Expense
    @PutMapping("/{id}")
    public Expense updateExpense(@PathVariable Long id,
                                 @RequestBody Expense updatedExpense) {

        Optional<Expense> optionalExpense = expenseRepository.findById(id);

        if (optionalExpense.isPresent()) {

            Expense expense = optionalExpense.get();

            expense.setAmount(updatedExpense.getAmount());
            expense.setGroupId(updatedExpense.getGroupId());
            expense.setPaidBy(updatedExpense.getPaidBy());
            expense.setSplitBetween(updatedExpense.getSplitBetween());

            return expenseRepository.save(expense);
        }

        return null;
    }

    // Delete Expense
    @DeleteMapping("/{id}")
    public String deleteExpense(@PathVariable Long id) {

        if (expenseRepository.existsById(id)) {

            expenseRepository.deleteById(id);

            return "Expense deleted successfully";
        }

        return "Expense not found";
    }
}

