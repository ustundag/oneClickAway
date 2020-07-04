package com.clickaway.repository;

import com.clickaway.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    /*
    @Query("select c.title from Category c where c.id = :id")
    String getTitleById(Long id);
    */

    //Category getCategoryById(Long id);

    Category getCategoryByTitle(String title);
}
