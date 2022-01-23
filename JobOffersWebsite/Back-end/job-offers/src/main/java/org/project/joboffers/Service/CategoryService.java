package org.project.joboffers.Service;

import org.project.joboffers.Convertor.ICategoryConvertor;
import org.project.joboffers.DALInterfaces.ICategoryDAL;
import org.project.joboffers.DTO.CategoryDTO;
import org.project.joboffers.Model.Category;
import org.project.joboffers.ServiceInterfaces.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {
    private final ICategoryDAL categoryDAL;
    private final ICategoryConvertor categoryConvertor;


    @Autowired
    public CategoryService(ICategoryDAL categoryDAL, ICategoryConvertor categoryConvertor){
        this.categoryDAL = categoryDAL;
        this.categoryConvertor = categoryConvertor;
    }
    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryDAL.getAllCategories();
        return categoryConvertor.convertListToDto(categories);
    }

    @Override
    public CategoryDTO getCategoryById(String id){
        Category category = categoryDAL.getCategoryById(id);
        if(category != null){
            return categoryConvertor.convertToDTO(category);
        }
        return null;
    }

    @Override
    public boolean addCategory(CategoryDTO categoryDTO){
        if(categoryDAL.getCategoryById(categoryDTO.getCategoryId()) == null){
            Category category = categoryConvertor.convertToModel(categoryDTO);
            categoryDAL.addCategory(category);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCategory(String id){
        Category category = categoryDAL.getCategoryById(id);
        if(category == null){
            return false;
        }
        categoryDAL.deleteCategory(category);
        return true;
    }

    @Override
    public boolean editCategory(CategoryDTO categoryDTO){
        Category oldCategory = categoryDAL.getCategoryById(categoryDTO.getCategoryId());
        if(oldCategory == null){
            return false;
        }
        Category category = categoryConvertor.convertToModel(categoryDTO);
        categoryDAL.editCategory(category);
        return true;
    }
}
