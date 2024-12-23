package com.richieartco.serviceImplementation;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.richieartco.model.Article;
import com.richieartco.model.Article;
import com.richieartco.repository.ArticleRepository;
import com.richieartco.services.ArticleService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;
    private final AmazonS3 amazonS3Client;

    public ArticleServiceImpl(AmazonS3 amazonS3Client) {
        this.amazonS3Client = amazonS3Client;
    }

    @Override
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    @Override
    public Article getArticleById(Long id) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        return optionalArticle.orElse(null);
    }

    @Override
    public void deleteArticleById(Long id) {
        articleRepository.deleteById(id);
    }

    @Override
    public String uploadArticle(@ModelAttribute Article article,
                                String submissionDate,
                                @RequestParam("storyPhoto") MultipartFile storyPhoto1,
                                Model model) throws IOException {

        try {
            System.out.println("----------------------------------------------" + storyPhoto1);

            // Upload image to Amazon S3 and get the URL
            String imageUrl = uploadImage(storyPhoto1);
            System.out.println("----------------------------------------------" + imageUrl);

            // Set the URL in the article
            article.setStoryPhotoUrl(imageUrl);

            LocalDateTime submissionDateTime = LocalDateTime.now();

            // Define the desired format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a");

            // Format the LocalDateTime
            submissionDate = submissionDateTime.format(formatter);
            System.out.println("UPLOADED----------------------------------------------" + submissionDate);

            article.setUploadedDate(submissionDate);

            articleRepository.save(article);
            model.addAttribute("uploadSuccess", "Successfully uploaded new article");

            return "redirect:/blogs";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("uploadError", "Failed to upload new article");
            return "redirect:/blogs";
        }
    }

    @Override
    @Transactional
    public void updateArticle(@ModelAttribute Article updatedArticle,
                              String submissionDate,
                              @RequestParam("storyPhoto") MultipartFile storyPhoto1) throws IOException {
        // Retrieve the existing article from the database
        Article existingArticle = articleRepository.findById(updatedArticle.getId()).orElse(null);

        LocalDateTime submissionDateTime = LocalDateTime.now();

        // Define the desired format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a");

        // Format the LocalDateTime
        submissionDate = submissionDateTime.format(formatter);
        if (existingArticle != null) {
            if (updatedArticle.getTitle() != null && !updatedArticle.getTitle().isEmpty()) {
                existingArticle.setTitle(updatedArticle.getTitle());
            }
            if (updatedArticle.getPromptText() != null && !updatedArticle.getPromptText().isEmpty()) {
                existingArticle.setPromptText(updatedArticle.getPromptText());
            }
            if (!storyPhoto1.isEmpty()) {
                existingArticle.setStoryPhotoUrl(uploadImage(storyPhoto1));
            }
            if (updatedArticle.getSubTitle() != null && !updatedArticle.getSubTitle().isEmpty()) {
                existingArticle.setSubTitle(updatedArticle.getSubTitle());
            }
            if (updatedArticle.getParagraph() != null) {
                existingArticle.setParagraph(updatedArticle.getParagraph());
            }
            if (updatedArticle.getAuthor() != null) {
                existingArticle.setAuthor(updatedArticle.getAuthor());
            }
            if (updatedArticle.getUploadedDate() != null) {
                existingArticle.setUploadedDate(submissionDate);
            }
            if (updatedArticle.getSubTitle1() != null && !updatedArticle.getSubTitle1().isEmpty()) {
                existingArticle.setSubTitle1(updatedArticle.getSubTitle1());
            }
            if (updatedArticle.getParagraph1() != null) {
                existingArticle.setParagraph1(updatedArticle.getParagraph1());
            }
            if (updatedArticle.getSubTitle2() != null && !updatedArticle.getSubTitle2().isEmpty()) {
                existingArticle.setSubTitle2(updatedArticle.getSubTitle2());
            }
            if (updatedArticle.getParagraph2() != null) {
                existingArticle.setParagraph2(updatedArticle.getParagraph2());
            }
            if (updatedArticle.getSubTitle3() != null && !updatedArticle.getSubTitle3().isEmpty()) {
                existingArticle.setSubTitle3(updatedArticle.getSubTitle3());
            }
            if (updatedArticle.getParagraph3() != null) {
                existingArticle.setParagraph3(updatedArticle.getParagraph3());
            }
            if (updatedArticle.getSubTitle4() != null && !updatedArticle.getSubTitle4().isEmpty()) {
                existingArticle.setSubTitle4(updatedArticle.getSubTitle4());
            }
            if (updatedArticle.getParagraph4() != null) {
                existingArticle.setParagraph4(updatedArticle.getParagraph4());
            }
            if (updatedArticle.getSubTitle5() != null && !updatedArticle.getSubTitle5().isEmpty()) {
                existingArticle.setSubTitle5(updatedArticle.getSubTitle5());
            }
            if (updatedArticle.getParagraph5() != null) {
                existingArticle.setParagraph5(updatedArticle.getParagraph5());
            }
            if (updatedArticle.getSubTitle6() != null && !updatedArticle.getSubTitle6().isEmpty()) {
                existingArticle.setSubTitle6(updatedArticle.getSubTitle6());
            }
            if (updatedArticle.getParagraph6() != null) {
                existingArticle.setParagraph6(updatedArticle.getParagraph6());
            }
            if (updatedArticle.getSubTitle7() != null && !updatedArticle.getSubTitle7().isEmpty()) {
                existingArticle.setSubTitle7(updatedArticle.getSubTitle7());
            }
            if (updatedArticle.getParagraph7() != null) {
                existingArticle.setParagraph7(updatedArticle.getParagraph7());
            }
            if (updatedArticle.getSubTitle8() != null && !updatedArticle.getSubTitle8().isEmpty()) {
                existingArticle.setSubTitle8(updatedArticle.getSubTitle8());
            }
            if (updatedArticle.getParagraph8() != null) {
                existingArticle.setParagraph8(updatedArticle.getParagraph8());
            }
            if (updatedArticle.getSubTitle9() != null && !updatedArticle.getSubTitle9().isEmpty()) {
                existingArticle.setSubTitle9(updatedArticle.getSubTitle9());
            }
            if (updatedArticle.getParagraph9() != null) {
                existingArticle.setParagraph9(updatedArticle.getParagraph9());
            }
            if (updatedArticle.getSubTitle10() != null && !updatedArticle.getSubTitle10().isEmpty()) {
                existingArticle.setSubTitle10(updatedArticle.getSubTitle10());
            }
            if (updatedArticle.getParagraph10() != null) {
                existingArticle.setParagraph10(updatedArticle.getParagraph10());
            }

            articleRepository.save(existingArticle);
        }
    }

    private String uploadImage(MultipartFile image) throws IOException {
        String bucketName = "richieart"; // Replace with your bucket name
        String originalFilename = image.getOriginalFilename();
        String fileName = "images/" + generateRandomFilename(originalFilename);
        byte[] bytes = image.getBytes();

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(bytes.length);

        amazonS3Client.putObject(new PutObjectRequest(bucketName, fileName, image.getInputStream(), metadata));

        // Return the URL to the uploaded image
        return amazonS3Client.getUrl(bucketName, fileName).toString();
    }

    private String generateRandomFilename(String originalFilename) {
        String extension = FilenameUtils.getExtension(originalFilename);
        String randomName = UUID.randomUUID().toString().replaceAll("-", "");
        String newFilename = randomName + "." + extension;
        return newFilename;
    }
}
