package org.project.joboffers.Convertor;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.project.joboffers.DTO.CategoryDTO;
import org.project.joboffers.Model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryConvertor implements ICategoryConvertor {
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryConvertor(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryDTO convertToDTO(Category category) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(category, CategoryDTO.class);
    }

    @Override
    public Category convertToModel(CategoryDTO categoryDTO) {
        return new Category(categoryDTO.getCategoryId(), categoryDTO.getCategoryName());
    }

    @Override
    public List<CategoryDTO> convertListToDto(List<Category> categories) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return categories.stream().map(category -> modelMapper.map(category, CategoryDTO.class))
                .collect(Collectors.toList());
    }
}
