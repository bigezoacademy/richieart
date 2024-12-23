// SearchController.java
package com.richieartco.controllers;

import com.richieartco.model.Project;
import com.richieartco.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {

    private final ProjectService projectService;

    @Autowired
    public SearchController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/search")
    public ResponseEntity<List<Project>> search(@RequestParam String searchTerm) {
        List<Project> searchResults = projectService.searchByCategoryOrProjectCode(searchTerm);
        System.out.println("------------SEARCH RESULTS ----" + searchResults.size() + "" + searchResults);
        return ResponseEntity.ok(searchResults);
    }



}
