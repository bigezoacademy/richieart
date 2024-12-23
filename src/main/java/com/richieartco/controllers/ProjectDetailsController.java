package com.richieartco.controllers;

import com.richieartco.model.Project;
import com.richieartco.services.ProjectService;
import com.richieartco.security.EndToEndUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class ProjectDetailsController {

    private final ProjectService projectService;

    @Autowired
    public ProjectDetailsController(ProjectService projectService) {
        this.projectService = projectService;
    }



    @GetMapping("/projects/{category}/{bedroom}")
    public String subcategoryDetails(@PathVariable String category, @PathVariable String bedroom, Model model) {
        List<Project> projects = projectService.getProjectsByCategoryAndBedroom(category, bedroom);
        model.addAttribute("projects", projects);
        model.addAttribute("category", category);
        model.addAttribute("bedroom", bedroom);
        return "projects";
    }


    @GetMapping("/project/{projectId}")
    public String projectDetails(@PathVariable Long projectId, Model model) {
        Project project = projectService.getProjectById(projectId);

        if (project != null) {
            model.addAttribute("project", project);
            return "project";
        } else {

            return "error";
        }
    }

    @GetMapping("/editProject/{projectId}")
    public String editProject(@PathVariable Long projectId, Model model) {
        Project project = projectService.getProjectById(projectId);

        if (project != null) {
            model.addAttribute("project", project);
            return "editProject";
        } else {
            // Handle project not found, you can redirect to an error page
            return "error";
        }
    }

    @PostMapping("/editOneProject")
    public String updateProject(@ModelAttribute Project updatedProject,
                                String submissionDate,
                                @RequestParam("thumbnailFile") MultipartFile thumbnailFile,
                                @RequestParam("imageFile1") MultipartFile imageFile1,
                                @RequestParam("imageFile2") MultipartFile imageFile2,
                                @RequestParam("imageFile3") MultipartFile imageFile3,
                                @RequestParam("imageFile4") MultipartFile imageFile4,
                                @RequestParam("imageFile5") MultipartFile imageFile5) throws IOException {

        LocalDateTime submissionDateTime = LocalDateTime.now();

        // Define the desired format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a");

        // Format the LocalDateTime
        submissionDate = submissionDateTime.format(formatter);
        System.out.println("----------------------------------------------"+submissionDate);
        projectService.updateProject(updatedProject,submissionDate,thumbnailFile,imageFile1,imageFile2,imageFile3,imageFile4,imageFile5);
        updatedProject.setSubmissionDate(submissionDate);
            return "redirect:/project/" + updatedProject.getId();

    }

    @PostMapping("/deleteProject/{id}")
    public String deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        // Redirect to a page after deletion (e.g., project list)
        return "redirect:/";
    }


}
