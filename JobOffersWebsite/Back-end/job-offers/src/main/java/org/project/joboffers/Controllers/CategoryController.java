package org.project.joboffers.Controllers;

import org.project.joboffers.DTO.CategoryDTO;
import org.project.joboffers.DTO.Response.MessageResponse;
import org.project.joboffers.ServiceInterfaces.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    private final ICategoryService categoryService;

    @Autowired
    public CategoryController(ICategoryService categoryService){this.categoryService = categoryService;}

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> categories = categoryService.getAllCategories();
        if(categories != null) {
            return ResponseEntity.ok().body(categories);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable(value = "id") String id) {
        CategoryDTO category = categoryService.getCategoryById(id);
        if(category != null) {
            return ResponseEntity.ok().body(category);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/category")
    public ResponseEntity<MessageResponse> addCategory(@RequestBody CategoryDTO categoryDTO) {
        if (!categoryService.addCategory(categoryDTO)){
            return ResponseEntity.badRequest().body(new MessageResponse("This category with the id " + categoryDTO.getCategoryName() + " already exists."));
        } else {
            return ResponseEntity.ok().body(new MessageResponse("Category is added successfully!!"));
        }
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<MessageResponse> deleteCategory(@PathVariable String id) {
        CategoryDTO category = categoryService.getCategoryById(id);
        if(category == null){
            return ResponseEntity.badRequest().body(new MessageResponse("Please provide a valid category id."));
        }
        else{
            categoryService.deleteCategory(id);
            return ResponseEntity.ok().body(new MessageResponse("Category is deleted successfully!!"));
        }
    }

    @PutMapping("/category")
    public ResponseEntity<MessageResponse> updateCategory(@RequestBody CategoryDTO categoryDTO) {
        if (categoryService.editCategory(categoryDTO)) {
            return ResponseEntity.ok().body(new MessageResponse("Category is edited successfully!!"));
        } else {
            return ResponseEntity.badRequest().body(new MessageResponse("Please provide a valid category id."));
        }
    }

}
