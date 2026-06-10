package com.example.smart.expense.splitter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.smart.expense.splitter.dto.DashboardResponse;
import com.example.smart.expense.splitter.model.Expense;
import com.example.smart.expense.splitter.repository.ExpenseRepository;
import com.example.smart.expense.splitter.repository.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{groupId}")
    public DashboardResponse getDashboard(@PathVariable Long groupId) {

        List<Expense> expenses = expenseRepository.findAll();

        double totalExpenses = 0;

        Set<Long> users = new HashSet<>();

        for (Expense e : expenses) {

            if (e.getGroupId().equals(groupId)) {

                totalExpenses += e.getAmount();

                users.add(e.getPaidBy().getId());

                users.addAll(e.getSplitBetween());
            }
        }

        DashboardResponse response = new DashboardResponse();

        response.setTotalExpenses(totalExpenses);

        response.setNumberOfUsers(users.size());

        if (users.size() > 0) {
            response.setAverageExpense(totalExpenses / users.size());
        }

        return response;
    }
}