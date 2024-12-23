package com.richieartco.controllers;

import com.richieartco.model.Article;
import com.richieartco.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/articles")
    public String listArticles(Model model) {
        List<Article> articles = articleService.getAllArticles();
        model.addAttribute("articles", articles);
        return "blogs"; // Thymeleaf template name
    }

    @GetMapping("/article/{id}")
    public String viewArticle(@PathVariable Long id, Model model) {
        Article article = articleService.getArticleById(id);

        if (article == null) {
            // Handle the case where the article is not found, e.g., redirect to an error page
            return "redirect:/error";
        }

        model.addAttribute("article", article);
        return "blog"; // Thymeleaf template name for displaying a single article
    }




}
