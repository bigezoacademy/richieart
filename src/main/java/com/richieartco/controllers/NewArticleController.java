package com.richieartco.controllers;

import com.amazonaws.services.s3.AmazonS3Client;
import com.richieartco.model.Article;
import com.richieartco.repository.ArticleRepository;
import com.richieartco.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class NewArticleController {
    @Autowired
    private ArticleService articleService;
    private final AmazonS3Client amazonS3Client;


    public NewArticleController(ArticleRepository articleRepository, AmazonS3Client amazonS3Client) {
        this.articleService = articleService;
        this.amazonS3Client = amazonS3Client;
    }


    @PostMapping("/uploadArticle")
    public String uploadArticle(@ModelAttribute Article article,
                                String submissionDate,
                                @RequestParam("storyPhoto") MultipartFile storyPhoto1,
                                Model model
                               ) throws IOException {

        LocalDateTime submissionDateTime = LocalDateTime.now();

        // Define the desired format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a");

        // Format the LocalDateTime
        submissionDate = submissionDateTime.format(formatter);
        System.out.println("----------------------------------------------"+storyPhoto1);
        articleService.uploadArticle(article,submissionDate,storyPhoto1,model);
        article.setUploadedDate(submissionDate);
        model.addAttribute("uploadSuccess", "Successfully uploaded new article");


        return "blogs";
    }




}
