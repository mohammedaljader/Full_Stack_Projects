package org.project.joboffers.DALInterfaces;

import org.project.joboffers.Model.Category;

import java.util.List;

public interface ICategoryDAL {
    List<Category> getAllCategories();
    Category getCategoryById(String id);
    void addCategory(Category category);
    void deleteCategory(Category category);
    void editCategory(Category category);
    Category getByCategoryName(String categoryName);
}
