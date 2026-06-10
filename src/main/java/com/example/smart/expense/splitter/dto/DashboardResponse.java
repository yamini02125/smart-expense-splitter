package com.example.smart.expense.splitter.dto;

import lombok.Data;

@Data
public class DashboardResponse {

    private double totalExpenses;
    private int numberOfUsers;
    private double averageExpense;
}