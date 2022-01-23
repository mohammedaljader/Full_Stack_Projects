package org.project.joboffers.JPARepository;

import org.project.joboffers.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, String> {
    Category findByCategoryId(String id);
    Category findByCategoryName(String categoryId);
}
