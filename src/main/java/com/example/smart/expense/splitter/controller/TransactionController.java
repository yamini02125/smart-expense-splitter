package com.example.smart.expense.splitter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.smart.expense.splitter.dto.TransactionResponse;
import com.example.smart.expense.splitter.model.Expense;
import com.example.smart.expense.splitter.repository.ExpenseRepository;
import com.example.smart.expense.splitter.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{groupId}")
    public List<TransactionResponse> getTransactions(@PathVariable Long groupId) {

        List<Expense> expenses = expenseRepository.findAll();
        List<TransactionResponse> response = new ArrayList<>();

        for (Expense e : expenses) {

            if (!e.getGroupId().equals(groupId))
                continue;

            TransactionResponse transaction = new TransactionResponse();

            transaction.setAmount(e.getAmount());

            transaction.setPayer(
                    userRepository.findById(e.getPaidBy().getId())
                            .get()
                            .getName());

            List<String> participants = new ArrayList<>();

            for (Long userId : e.getSplitBetween()) {

                participants.add(
                        userRepository.findById(userId)
                                .get()
                                .getName());
            }

            transaction.setParticipants(participants);
            transaction.setCreatedAt(e.getCreatedAt());

            response.add(transaction);
        }

        return response;
    }
}