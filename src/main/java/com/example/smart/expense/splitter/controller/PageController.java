package com.example.smart.expense.splitter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/userspage")
    public String usersPage() {
        return "users";
    }

    @GetMapping("/expensespage")
    public String expensesPage() {
        return "expenses";
    }

    @GetMapping("/dashboardpage")
    public String dashboardPage() {
        return "dashboard";
    }

    @GetMapping("/transactionspage")
    public String transactionsPage() {
        return "transactions";
    }

    @GetMapping("/balancepage")
    public String balancePage() {
        return "balance";
    }

    

}