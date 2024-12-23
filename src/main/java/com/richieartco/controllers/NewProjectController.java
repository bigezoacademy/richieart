package com.richieartco.controllers;

import com.amazonaws.services.s3.AmazonS3;
import com.richieartco.model.Project;
import com.richieartco.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class NewProjectController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private AmazonS3 amazonS3;


    public NewProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }



    @PostMapping("/uploadProject")
    public String uploadProject(@ModelAttribute Project project,
                                String submissionDate,
                                @RequestParam("thumbnailFile") MultipartFile thumbnailFile,
                                @RequestParam("imageFile1") MultipartFile imageFile1,
                                @RequestParam("imageFile2") MultipartFile imageFile2,
                                @RequestParam("imageFile3") MultipartFile imageFile3,
                                @RequestParam("imageFile4") MultipartFile imageFile4,
                                @RequestParam("imageFile5") MultipartFile imageFile5,Model model
                               ) throws IOException {



        LocalDateTime submissionDateTime = LocalDateTime.now();

        // Define the desired format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a");

        // Format the LocalDateTime
        submissionDate = submissionDateTime.format(formatter);
        System.out.println("----------------------------------------------"+submissionDate);
        projectService.uploadProject(project,submissionDate,thumbnailFile,imageFile1,imageFile2,imageFile3,imageFile4,imageFile5,model);
        project.setSubmissionDate(submissionDate);
        model.addAttribute("uploadSuccess", "Successfully uploaded new project");


        return "admin";
    }




}
