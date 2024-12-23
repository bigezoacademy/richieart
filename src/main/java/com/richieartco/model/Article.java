package com.richieartco.model;

import jakarta.persistence.*;
import org.springframework.web.multipart.MultipartFile;

@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000)
    private String title;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String promptText;

    @Column(length = 1000)
    private String subTitle;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String paragraph;

    @Column(length = 1000)
    private String subTitle1;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String paragraph1;

    @Column(length = 1000)
    private String subTitle2;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String paragraph2;

    @Column(length = 1000)
    private String subTitle3;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String paragraph3;

    @Column(length = 1000)
    private String subTitle4;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String paragraph4;

    @Column(length = 1000)
    private String subTitle5;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String paragraph5;

    @Column(length = 1000)
    private String subTitle6;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String paragraph6;

    @Column(length = 1000)
    private String subTitle7;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String paragraph7;

    @Column(length = 1000)
    private String subTitle8;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String paragraph8;

    @Column(length = 1000)
    private String subTitle9;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String paragraph9;

    @Column(length = 1000)
    private String subTitle10;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String paragraph10;

    private String uploadedDate;
    private String author;

    @Transient
    private transient MultipartFile storyPhoto;  // Marked as transient to exclude it from serialization

    @Column(length = 1000)
    private String storyPhotoUrl;  // URL to store in the database

    // Default constructor
    public Article() {
    }

    // All-args constructor
    public Article(Long id, String title, String promptText, String subTitle, String paragraph, String subTitle1,
                   String paragraph1, String subTitle2, String paragraph2, String subTitle3, String paragraph3,
                   String subTitle4, String paragraph4, String subTitle5, String paragraph5, String subTitle6,
                   String paragraph6, String subTitle7, String paragraph7, String subTitle8, String paragraph8,
                   String subTitle9, String paragraph9, String subTitle10, String paragraph10, String uploadedDate,
                   String author, MultipartFile storyPhoto, String storyPhotoUrl) {
        this.id = id;
        this.title = title;
        this.promptText = promptText;
        this.subTitle = subTitle;
        this.paragraph = paragraph;
        this.subTitle1 = subTitle1;
        this.paragraph1 = paragraph1;
        this.subTitle2 = subTitle2;
        this.paragraph2 = paragraph2;
        this.subTitle3 = subTitle3;
        this.paragraph3 = paragraph3;
        this.subTitle4 = subTitle4;
        this.paragraph4 = paragraph4;
        this.subTitle5 = subTitle5;
        this.paragraph5 = paragraph5;
        this.subTitle6 = subTitle6;
        this.paragraph6 = paragraph6;
        this.subTitle7 = subTitle7;
        this.paragraph7 = paragraph7;
        this.subTitle8 = subTitle8;
        this.paragraph8 = paragraph8;
        this.subTitle9 = subTitle9;
        this.paragraph9 = paragraph9;
        this.subTitle10 = subTitle10;
        this.paragraph10 = paragraph10;
        this.uploadedDate = uploadedDate;
        this.author = author;
        this.storyPhoto = storyPhoto;
        this.storyPhotoUrl = storyPhotoUrl;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPromptText() {
        return promptText;
    }

    public void setPromptText(String promptText) {
        this.promptText = promptText;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getParagraph() {
        return paragraph;
    }

    public void setParagraph(String paragraph) {
        this.paragraph = paragraph;
    }

    public String getSubTitle1() {
        return subTitle1;
    }

    public void setSubTitle1(String subTitle1) {
        this.subTitle1 = subTitle1;
    }

    public String getParagraph1() {
        return paragraph1;
    }

    public void setParagraph1(String paragraph1) {
        this.paragraph1 = paragraph1;
    }

    public String getSubTitle2() {
        return subTitle2;
    }

    public void setSubTitle2(String subTitle2) {
        this.subTitle2 = subTitle2;
    }

    public String getParagraph2() {
        return paragraph2;
    }

    public void setParagraph2(String paragraph2) {
        this.paragraph2 = paragraph2;
    }

    public String getSubTitle3() {
        return subTitle3;
    }

    public void setSubTitle3(String subTitle3) {
        this.subTitle3 = subTitle3;
    }

    public String getParagraph3() {
        return paragraph3;
    }

    public void setParagraph3(String paragraph3) {
        this.paragraph3 = paragraph3;
    }

    public String getSubTitle4() {
        return subTitle4;
    }

    public void setSubTitle4(String subTitle4) {
        this.subTitle4 = subTitle4;
    }

    public String getParagraph4() {
        return paragraph4;
    }

    public void setParagraph4(String paragraph4) {
        this.paragraph4 = paragraph4;
    }

    public String getSubTitle5() {
        return subTitle5;
    }

    public void setSubTitle5(String subTitle5) {
        this.subTitle5 = subTitle5;
    }

    public String getParagraph5() {
        return paragraph5;
    }

    public void setParagraph5(String paragraph5) {
        this.paragraph5 = paragraph5;
    }

    public String getSubTitle6() {
        return subTitle6;
    }

    public void setSubTitle6(String subTitle6) {
        this.subTitle6 = subTitle6;
    }

    public String getParagraph6() {
        return paragraph6;
    }

    public void setParagraph6(String paragraph6) {
        this.paragraph6 = paragraph6;
    }

    public String getSubTitle7() {
        return subTitle7;
    }

    public void setSubTitle7(String subTitle7) {
        this.subTitle7 = subTitle7;
    }

    public String getParagraph7() {
        return paragraph7;
    }

    public void setParagraph7(String paragraph7) {
        this.paragraph7 = paragraph7;
    }

    public String getSubTitle8() {
        return subTitle8;
    }

    public void setSubTitle8(String subTitle8) {
        this.subTitle8 = subTitle8;
    }

    public String getParagraph8() {
        return paragraph8;
    }

    public void setParagraph8(String paragraph8) {
        this.paragraph8 = paragraph8;
    }

    public String getSubTitle9() {
        return subTitle9;
    }

    public void setSubTitle9(String subTitle9) {
        this.subTitle9 = subTitle9;
    }

    public String getParagraph9() {
        return paragraph9;
    }

    public void setParagraph9(String paragraph9) {
        this.paragraph9 = paragraph9;
    }

    public String getSubTitle10() {
        return subTitle10;
    }

    public void setSubTitle10(String subTitle10) {
        this.subTitle10 = subTitle10;
    }

    public String getParagraph10() {
        return paragraph10;
    }

    public void setParagraph10(String paragraph10) {
        this.paragraph10 = paragraph10;
    }

    public String getUploadedDate() {
        return uploadedDate;
    }

    public void setUploadedDate(String uploadedDate) {
        this.uploadedDate = uploadedDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public MultipartFile getStoryPhoto() {
        return storyPhoto;
    }

    public void setStoryPhoto(MultipartFile storyPhoto) {
        this.storyPhoto = storyPhoto;
    }

    public String getStoryPhotoUrl() {
        return storyPhotoUrl;
    }

    public void setStoryPhotoUrl(String storyPhotoUrl) {
        this.storyPhotoUrl = storyPhotoUrl;
    }
}
