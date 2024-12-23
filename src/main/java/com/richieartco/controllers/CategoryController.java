package com.richieartco.controllers;

import com.richieartco.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class CategoryController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/categories")
    public List<String> getAllCategories() {
               return projectService.getDistinctCategories();
    }
}
