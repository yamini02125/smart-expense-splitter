package com.example.smart.expense.splitter.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class GroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String groupName;
}