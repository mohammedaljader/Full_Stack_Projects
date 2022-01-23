package org.project.joboffers.ServiceInterfaces;

import org.project.joboffers.DTO.CategoryDTO;
import org.project.joboffers.Model.Category;

import java.util.List;

public interface ICategoryService {
    List<CategoryDTO> getAllCategories();

    CategoryDTO getCategoryById(String id);

    boolean addCategory(CategoryDTO categoryDTO);

    boolean deleteCategory(String id);

    boolean editCategory(CategoryDTO categoryDTO);
}
