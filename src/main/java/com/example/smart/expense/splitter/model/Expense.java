package com.example.smart.expense.splitter.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long groupId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User paidBy;

    private double amount;

    // Stores date and time when expense was created
    private LocalDateTime createdAt;

    @ElementCollection
    private List<Long> splitBetween;

    private String category;

    public String getCategory() {
    return category;
}

public void setCategory(String category) {
    this.category = category;
}
}