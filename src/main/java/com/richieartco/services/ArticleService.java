package com.richieartco.services;

import com.richieartco.model.Article;
import com.richieartco.model.Article;
import com.richieartco.model.Article;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ArticleService {
    List<Article> getAllArticles();

    Article getArticleById(Long id);

    void deleteArticleById(Long id);

    String uploadArticle(@ModelAttribute Article article,
                         String submissionDate,
                         @RequestParam("storyPhoto") MultipartFile storyPhoto1,
                          Model model
    ) throws IOException;

    void updateArticle(@ModelAttribute Article updatedArticle,
                       String submissionDate,
                       @RequestParam("storyPhoto") MultipartFile storyPhoto1)throws IOException;


}
