package com.example.smart.expense.splitter.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class TransactionResponse {

    private String payer;
    private double amount;
    private List<String> participants;
    private LocalDateTime createdAt;
}