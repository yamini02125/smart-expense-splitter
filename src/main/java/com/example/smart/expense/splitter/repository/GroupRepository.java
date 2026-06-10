package com.example.smart.expense.splitter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.smart.expense.splitter.model.GroupEntity;

public interface GroupRepository extends JpaRepository<GroupEntity, Long> {
}