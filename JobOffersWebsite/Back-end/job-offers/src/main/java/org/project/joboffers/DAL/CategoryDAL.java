package org.project.joboffers.DAL;

import org.project.joboffers.DALInterfaces.ICategoryDAL;
import org.project.joboffers.JPARepository.CategoryRepo;
import org.project.joboffers.Model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDAL implements ICategoryDAL {

    private final CategoryRepo categoryRepo;

    @Autowired
    public CategoryDAL(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    @Override
    public Category getCategoryById(String id) {
        return categoryRepo.findByCategoryId(id);
    }

    @Override
    public void addCategory(Category category) {
         categoryRepo.save(category);
    }

    @Override
    public void deleteCategory(Category category) {
        categoryRepo.delete(category);
    }

    @Override
    public void editCategory(Category category) {
        categoryRepo.save(category);
    }

    @Override
    public Category getByCategoryName(String categoryName) {
        return categoryRepo.findByCategoryName(categoryName);
    }
}
