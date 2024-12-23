// ProjectServiceImpl.java
package com.richieartco.serviceImplementation;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.richieartco.model.Project;
import com.richieartco.repository.ProjectRepository;
import com.richieartco.services.ProjectService;
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
import java.util.*;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    private final AmazonS3 amazonS3Client;

    public ProjectServiceImpl(AmazonS3 amazonS3Client) {
        this.amazonS3Client = amazonS3Client;
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAllProjectsOrderedByCategory();
    }

    @Override
    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project getProjectById(Long id) {
        return projectRepository.findById(id).orElse(null);
    }

    @Override
    public Project updateProjectDetails(Long id, Project editedProject) {
        // Implement your update logic here
        return null;
    }

    @Override
    public Map<String, List<String>> getDistinctBedroomsByCategory() {
        Map<String, List<String>> bedroomsByCategory = new HashMap<>();
        List<String> distinctCategories = getDistinctCategories();

        for (String category : distinctCategories) {
            List<String> bedrooms = projectRepository.findDistinctNumberOfBedroomsByCategory(category);
            bedroomsByCategory.put(category, bedrooms);
            System.out.println("--------------" + bedrooms.get(0));
        }

        return bedroomsByCategory;
    }



    @Override
    public List<String> getDistinctCategories() {
        return projectRepository.findDistinctCategories();
    }

    @Override
    public List<Project> getProjectsByCategoryAndBedroom(String category, String bedroom) {
        return projectRepository.findByCategoryAndNumberOfBedrooms(category, bedroom);
    }
    @Override
    public List<String> getDistinctBedrooms() {
        return projectRepository.findDistinctBedrooms();
    }

    @Override
    public Project createProject(Project project){
        return projectRepository.save(project);
    }



    @Override
    public String uploadProject(@ModelAttribute Project project,
                                String submissionDate,
                                @RequestParam("thumbnailFile") MultipartFile thumbnailFile,
                                @RequestParam("imageFile1") MultipartFile imageFile1,
                                @RequestParam("imageFile2") MultipartFile imageFile2,
                                @RequestParam("imageFile3") MultipartFile imageFile3,
                                @RequestParam("imageFile4") MultipartFile imageFile4,
                                @RequestParam("imageFile5") MultipartFile imageFile5, Model model
    ) throws IOException {

       try{
           project.setThumbnailImage(uploadImage(thumbnailFile));
           project.setImageUrl1(uploadImage(imageFile1));
           project.setImageUrl2(uploadImage(imageFile2));
           project.setImageUrl3(uploadImage(imageFile3));
           project.setImageUrl4(uploadImage(imageFile4));
           project.setImageUrl5(uploadImage(imageFile5));

           LocalDateTime submissionDateTimeDate = LocalDateTime.now();
           submissionDate = submissionDateTimeDate.toString();
           System.out.println("UPLOADED----------------------------------------------" + submissionDate);

           project.setSubmissionDate(submissionDate);

           projectRepository.save(project);
           model.addAttribute("uploadSuccess", "Successfully uploaded new project");


           return "redirect:/admin";
       }catch(Exception e){
           e.printStackTrace();
           model.addAttribute("uploadError", "Failed to upload new project");


           return "redirect:/admin";
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

        String imageUrl = amazonS3Client.getUrl(bucketName, fileName).toString();
        System.out.println("Image url-----" + imageUrl);

        return imageUrl;
    }

    private String generateRandomFilename(String originalFilename) {
        String extension = FilenameUtils.getExtension(originalFilename);
        String randomName = UUID.randomUUID().toString().replaceAll("-", "");
        String newFilename = randomName + "." + extension;

        return newFilename;
    }

    @Override
    public List<Project> searchByCategoryOrProjectCode(String searchTerm) {
        return projectRepository.searchByCategoryOrProjectCode(searchTerm);
    }

    @Override
    @Transactional
    public void updateProject(@ModelAttribute Project updatedProject,
                              String submissionDate,
                              @RequestParam("thumbnailFile") MultipartFile thumbnailFile,
                              @RequestParam("imageFile1") MultipartFile imageFile1,
                              @RequestParam("imageFile2") MultipartFile imageFile2,
                              @RequestParam("imageFile3") MultipartFile imageFile3,
                              @RequestParam("imageFile4") MultipartFile imageFile4,
                              @RequestParam("imageFile5") MultipartFile imageFile5) throws IOException {
        // Retrieve the existing project from the database
        Project existingProject = projectRepository.findById(updatedProject.getId()).orElse(null);

        // Update non-empty fields of the existing project with the values from the updated project
        if (existingProject != null) {
            if (updatedProject.getProjectCode() != null && !updatedProject.getProjectCode().isEmpty()) {
                existingProject.setProjectCode(updatedProject.getProjectCode());
            }
            if (updatedProject.getProjectVideoUrl() != null && !updatedProject.getProjectVideoUrl().isEmpty()) {
                existingProject.setProjectVideoUrl(updatedProject.getProjectVideoUrl());
            }
            if (updatedProject.getCategory() != null && !updatedProject.getCategory().isEmpty()) {
                existingProject.setCategory(updatedProject.getCategory());
            }
            if (updatedProject.getDescription() != null && !updatedProject.getDescription().isEmpty()) {
                existingProject.setDescription(updatedProject.getDescription());
            }
            if (updatedProject.getMaterialSchedule() != null && !updatedProject.getMaterialSchedule().isEmpty()) {
                existingProject.setMaterialSchedule(updatedProject.getMaterialSchedule());
            }
            if (updatedProject.getNumberOfFloors() != null) {
                existingProject.setNumberOfFloors(updatedProject.getNumberOfFloors());
            }
            if (updatedProject.getNumberOfBedrooms() != null) {
                existingProject.setNumberOfBedrooms(updatedProject.getNumberOfBedrooms());
            }
            if (updatedProject.getNumberOfBathrooms() != null) {
                existingProject.setNumberOfBathrooms(updatedProject.getNumberOfBathrooms());
            }
            if (updatedProject.getAreaOfHouse() != null && !updatedProject.getAreaOfHouse().isEmpty()) {
                existingProject.setAreaOfHouse(updatedProject.getAreaOfHouse());
            }
            if (updatedProject.getWidthOfHouse() != null && !updatedProject.getWidthOfHouse().isEmpty()) {
                existingProject.setWidthOfHouse(updatedProject.getWidthOfHouse());
            }
            if (updatedProject.getDepthOfHouse() != null && !updatedProject.getDepthOfHouse().isEmpty()) {
                existingProject.setDepthOfHouse(updatedProject.getDepthOfHouse());
            }

            if (!thumbnailFile.isEmpty()) {
                existingProject.setThumbnailImage(uploadImage(thumbnailFile));
            }

            // Handle Image 1
            if (!imageFile1.isEmpty()) {
                existingProject.setImageUrl1(uploadImage(imageFile1));
            }

            // Handle Image 2
            if (!imageFile2.isEmpty()) {
                existingProject.setImageUrl2(uploadImage(imageFile2));
            }

            // Handle Image 3
            if (!imageFile3.isEmpty()) {
                existingProject.setImageUrl3(uploadImage(imageFile3));
            }

            // Handle Image 4
            if (!imageFile4.isEmpty()) {
                existingProject.setImageUrl4(uploadImage(imageFile4));
            }

            // Handle Image 5
            if (!imageFile5.isEmpty()) {
                existingProject.setImageUrl5(uploadImage(imageFile5));
            }

            projectRepository.save(existingProject);
        }
    }

    @Override
    public void deleteProject(Long id) {

        projectRepository.deleteById(id);
    }
}
