package com.richieartco.controllers;

import com.richieartco.model.Article;
import com.richieartco.model.Article;
import com.richieartco.model.Article;
import com.richieartco.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class EditArticleController {
    @Autowired
    private ArticleService articleService;

    @PostMapping("/deleteArticle/{id}")
    public String deleteArticle(@PathVariable Long id) {
        articleService.deleteArticleById(id);
        return "redirect:/articles";
    }

    @GetMapping("/editArticle/{articleId}")
    public String editArticle(@PathVariable Long articleId, Model model) {
        Article article = articleService.getArticleById(articleId);

        if (article != null) {
            model.addAttribute("article", article);
            return "editArticle";
        } else {
            // Handle article not found, you can redirect to an error page
            return "error";
        }
    }

    @PostMapping("/editOneArticle")
    public String updateArticle(@ModelAttribute Article updatedArticle,
                                String submissionDate,
                                @RequestParam("storyPhoto") MultipartFile storyPhoto1
                                ) throws IOException {

        LocalDateTime submissionDateTime = LocalDateTime.now();

        // Define the desired format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a");

        // Format the LocalDateTime
        submissionDate = submissionDateTime.format(formatter);
        System.out.println("----------------------------------------------"+submissionDate);
        articleService.updateArticle(updatedArticle,submissionDate,storyPhoto1);
        updatedArticle.setUploadedDate(submissionDate);
        return "redirect:/article/" + updatedArticle.getId();

    }

}

