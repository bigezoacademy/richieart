package com.richieartco.model;

import jakarta.persistence.*;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String projectCode;

    private String category;

    @Column(length = 1000)
    private String description;

    @Column(length = 1000)
    private String materialSchedule;

    private String submissionDate;

    @Column(length = 1000)
    private String thumbnailImage;

    @Column(length = 1000)
    private String imageUrl1;

    @Column(length = 1000)
    private String imageUrl2;

    @Column(length = 1000)
    private String imageUrl3;

    @Column(length = 1000)
    private String imageUrl4;

    @Column(length = 1000)
    private String imageUrl5;

    @Column(length = 1000)
    private String projectVideoUrl;

    private String numberOfFloors;
    private String numberOfBedrooms;
    private String numberOfBathrooms;
    private String areaOfHouse;
    private String widthOfHouse;
    private String depthOfHouse;
    private String verified;

    // Default constructor
    public Project() {
    }

    // All-args constructor
    public Project(Long id, String projectCode, String category, String description, String materialSchedule,
                   String submissionDate, String thumbnailImage, String imageUrl1, String imageUrl2,
                   String imageUrl3, String imageUrl4, String imageUrl5, String projectVideoUrl,
                   String numberOfFloors, String numberOfBedrooms, String numberOfBathrooms,
                   String areaOfHouse, String widthOfHouse, String depthOfHouse, String verified) {
        this.id = id;
        this.projectCode = projectCode;
        this.category = category;
        this.description = description;
        this.materialSchedule = materialSchedule;
        this.submissionDate = submissionDate;
        this.thumbnailImage = thumbnailImage;
        this.imageUrl1 = imageUrl1;
        this.imageUrl2 = imageUrl2;
        this.imageUrl3 = imageUrl3;
        this.imageUrl4 = imageUrl4;
        this.imageUrl5 = imageUrl5;
        this.projectVideoUrl = projectVideoUrl;
        this.numberOfFloors = numberOfFloors;
        this.numberOfBedrooms = numberOfBedrooms;
        this.numberOfBathrooms = numberOfBathrooms;
        this.areaOfHouse = areaOfHouse;
        this.widthOfHouse = widthOfHouse;
        this.depthOfHouse = depthOfHouse;
        this.verified = verified;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMaterialSchedule() {
        return materialSchedule;
    }

    public void setMaterialSchedule(String materialSchedule) {
        this.materialSchedule = materialSchedule;
    }

    public String getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(String submissionDate) {
        this.submissionDate = submissionDate;
    }

    public String getThumbnailImage() {
        return thumbnailImage;
    }

    public void setThumbnailImage(String thumbnailImage) {
        this.thumbnailImage = thumbnailImage;
    }

    public String getImageUrl1() {
        return imageUrl1;
    }

    public void setImageUrl1(String imageUrl1) {
        this.imageUrl1 = imageUrl1;
    }

    public String getImageUrl2() {
        return imageUrl2;
    }

    public void setImageUrl2(String imageUrl2) {
        this.imageUrl2 = imageUrl2;
    }

    public String getImageUrl3() {
        return imageUrl3;
    }

    public void setImageUrl3(String imageUrl3) {
        this.imageUrl3 = imageUrl3;
    }

    public String getImageUrl4() {
        return imageUrl4;
    }

    public void setImageUrl4(String imageUrl4) {
        this.imageUrl4 = imageUrl4;
    }

    public String getImageUrl5() {
        return imageUrl5;
    }

    public void setImageUrl5(String imageUrl5) {
        this.imageUrl5 = imageUrl5;
    }

    public String getProjectVideoUrl() {
        return projectVideoUrl;
    }

    public void setProjectVideoUrl(String projectVideoUrl) {
        this.projectVideoUrl = projectVideoUrl;
    }

    public String getNumberOfFloors() {
        return numberOfFloors;
    }

    public void setNumberOfFloors(String numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
    }

    public String getNumberOfBedrooms() {
        return numberOfBedrooms;
    }

    public void setNumberOfBedrooms(String numberOfBedrooms) {
        this.numberOfBedrooms = numberOfBedrooms;
    }

    public String getNumberOfBathrooms() {
        return numberOfBathrooms;
    }

    public void setNumberOfBathrooms(String numberOfBathrooms) {
        this.numberOfBathrooms = numberOfBathrooms;
    }

    public String getAreaOfHouse() {
        return areaOfHouse;
    }

    public void setAreaOfHouse(String areaOfHouse) {
        this.areaOfHouse = areaOfHouse;
    }

    public String getWidthOfHouse() {
        return widthOfHouse;
    }

    public void setWidthOfHouse(String widthOfHouse) {
        this.widthOfHouse = widthOfHouse;
    }

    public String getDepthOfHouse() {
        return depthOfHouse;
    }

    public void setDepthOfHouse(String depthOfHouse) {
        this.depthOfHouse = depthOfHouse;
    }

    public String getVerified() {
        return verified;
    }

    public void setVerified(String verified) {
        this.verified = verified;
    }
}
