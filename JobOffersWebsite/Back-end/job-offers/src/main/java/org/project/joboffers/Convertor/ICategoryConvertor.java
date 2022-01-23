package org.project.joboffers.Convertor;

import org.project.joboffers.DTO.CategoryDTO;
import org.project.joboffers.Model.Category;

import java.util.List;

public interface ICategoryConvertor {
    CategoryDTO convertToDTO(Category category);
    Category convertToModel(CategoryDTO categoryDTO);
    List<CategoryDTO> convertListToDto(List<Category> categories);
}
