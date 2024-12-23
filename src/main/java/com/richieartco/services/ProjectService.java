// ProjectService.java
package com.richieartco.services;

import com.richieartco.model.Project;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ProjectService {
    List<Project> getAllProjects();

    Project saveProject(Project project);

    Project getProjectById(Long id);

    Project updateProjectDetails(Long id, Project editedProject);
    Map<String, List<String>> getDistinctBedroomsByCategory();
    List<Project> getProjectsByCategoryAndBedroom(String category, String bedroom);


    List<String> getDistinctCategories();

    List<String> getDistinctBedrooms();

   Project createProject(Project project);



    String uploadProject(@ModelAttribute Project project,
                         String submissionDate,
                         @RequestParam("thumbnailFile") MultipartFile thumbnailFile,
                         @RequestParam("imageFile1") MultipartFile imageFile1,
                         @RequestParam("imageFile2") MultipartFile imageFile2,
                         @RequestParam("imageFile3") MultipartFile imageFile3,
                         @RequestParam("imageFile4") MultipartFile imageFile4,
                         @RequestParam("imageFile5") MultipartFile imageFile5, Model model
    ) throws IOException;


    List<Project> searchByCategoryOrProjectCode(String searchTerm);

    void updateProject(@ModelAttribute Project updatedProject,
                       String submissionDate,
                       @RequestParam("thumbnailFile") MultipartFile thumbnailFile,
                       @RequestParam("imageFile1") MultipartFile imageFile1,
                       @RequestParam("imageFile2") MultipartFile imageFile2,
                       @RequestParam("imageFile3") MultipartFile imageFile3,
                       @RequestParam("imageFile4") MultipartFile imageFile4,
                       @RequestParam("imageFile5") MultipartFile imageFile5)throws IOException;

    void deleteProject(Long id);
}
