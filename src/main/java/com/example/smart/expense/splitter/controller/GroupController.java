package com.example.smart.expense.splitter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.smart.expense.splitter.model.GroupEntity;
import com.example.smart.expense.splitter.repository.GroupRepository;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private GroupRepository groupRepository;

    // Create group
    @PostMapping
    public GroupEntity createGroup(@RequestBody GroupEntity group) {
        return groupRepository.save(group);
    }

    // Get all groups
    @GetMapping
    public List<GroupEntity> getAllGroups() {
        return groupRepository.findAll();
    }
}