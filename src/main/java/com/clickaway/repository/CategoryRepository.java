package com.clickaway.repository;

import com.clickaway.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    /*
    @Query(value = "select c from Category c")
    List<Category> retrieveAll();
     */
    List<CategoryIDAndTitle> findAllBy();
}
