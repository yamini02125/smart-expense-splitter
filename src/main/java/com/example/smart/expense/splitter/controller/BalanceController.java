package com.example.smart.expense.splitter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.smart.expense.splitter.model.Expense;
import com.example.smart.expense.splitter.repository.ExpenseRepository;
import com.example.smart.expense.splitter.repository.UserRepository;

import java.util.*;

@RestController
@RequestMapping("/balance")
public class BalanceController {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserRepository userRepository;

    // Shows balances (+ve means should receive, -ve means should pay)
    @GetMapping("/{groupId}")
    public Map<String, Double> calculateBalance(@PathVariable Long groupId) {

        List<Expense> expenses = expenseRepository.findAll();

        Map<String, Double> balance = new HashMap<>();

        for (Expense e : expenses) {

            if (!e.getGroupId().equals(groupId))
                continue;

            if (e.getPaidBy() == null)
                continue;

            String payer = userRepository
                    .findById(e.getPaidBy().getId())
                    .get()
                    .getName();

            // Add full amount to payer
            balance.put(payer,
                    balance.getOrDefault(payer, 0.0) + e.getAmount());

            // Divide equally among participants
            double share = e.getAmount() / e.getSplitBetween().size();

            // Subtract share from everyone involved
            for (Long userId : e.getSplitBetween()) {

                String userName = userRepository
                        .findById(userId)
                        .get()
                        .getName();

                balance.put(userName,
                        balance.getOrDefault(userName, 0.0) - share);
            }
        }

        return balance;
    }

    // Shows "who pays whom"
    @GetMapping("/settle/{groupId}")
    public List<String> settleBalances(@PathVariable Long groupId) {

        Map<String, Double> balance = calculateBalance(groupId);

        List<String> result = new ArrayList<>();

        List<String> positiveUsers = new ArrayList<>();
        List<String> negativeUsers = new ArrayList<>();

        for (Map.Entry<String, Double> entry : balance.entrySet()) {

            if (entry.getValue() > 0.0) {
                positiveUsers.add(entry.getKey());
            }

            if (entry.getValue() < 0.0) {
                negativeUsers.add(entry.getKey());
            }
        }

        for (String debtor : negativeUsers) {

            for (String creditor : positiveUsers) {

                double debt = Math.abs(balance.get(debtor));
                double credit = balance.get(creditor);

                double amount = Math.min(debt, credit);

                if (amount > 0) {

                    result.add(debtor + " pays " + creditor + " ₹" + amount);

                    balance.put(debtor,
                            balance.get(debtor) + amount);

                    balance.put(creditor,
                            balance.get(creditor) - amount);
                }
            }
        }

        return result;
    }
}