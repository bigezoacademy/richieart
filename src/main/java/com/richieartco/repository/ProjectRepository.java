// ProjectRepository.java
package com.richieartco.repository;

import com.richieartco.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query("SELECT DISTINCT p.category FROM Project p ORDER BY p.category ASC")
    List<String> findDistinctCategories();

    @Query(value = "SELECT DISTINCT p.number_of_bedrooms FROM project p WHERE p.category = :category ORDER BY CAST(p.number_of_bedrooms AS SIGNED) ASC", nativeQuery = true)
    List<String> findDistinctNumberOfBedroomsByCategory(@Param("category") String category);

    @Query("SELECT p FROM Project p WHERE p.category = :category AND p.numberOfBedrooms = :bedroom")
    List<Project> findByCategoryAndNumberOfBedrooms(@Param("category") String category, @Param("bedroom") String bedroom);

    List<Project> findAllByCategory(String category);
    @Query("SELECT DISTINCT p.numberOfBedrooms FROM Project p")
    List<String> findDistinctBedrooms();


    @Query("SELECT p FROM Project p " +
            "WHERE p.category LIKE %:searchTerm% OR p.projectCode LIKE %:searchTerm%")
    List<Project> searchByCategoryOrProjectCode(@Param("searchTerm") String searchTerm);


    @Query("SELECT p FROM Project p ORDER BY p.category ASC")
    List<Project> findAllProjectsOrderedByCategory();
}
