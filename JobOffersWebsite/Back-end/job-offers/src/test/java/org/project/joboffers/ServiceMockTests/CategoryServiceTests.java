package org.project.joboffers.ServiceMockTests;

import org.modelmapper.ModelMapper;
import org.project.joboffers.Convertor.CategoryConvertor;
import org.project.joboffers.DALInterfaces.ICategoryDAL;
import org.project.joboffers.DTO.CategoryDTO;
import org.project.joboffers.Model.Category;
import org.project.joboffers.Service.CategoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTests {

    @Mock
    private ICategoryDAL categoryDAL;
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        categoryService = new CategoryService(categoryDAL, new CategoryConvertor(new ModelMapper()));
    }

    @Test
    void getCategoryList() {
        List<Category> categories = List.of(
                new Category("Test"),
                new Category("Test1"),
                new Category("Test2")
        );
        when(categoryDAL.getAllCategories()).thenReturn(categories);

        List<CategoryDTO> categories1 = categoryService.getAllCategories();
        Assertions.assertEquals(categories1.get(0).getCategoryName(), "Test");
        Assertions.assertEquals(categories1.get(1).getCategoryName(), "Test1");
        Assertions.assertEquals(categories1.get(2).getCategoryName(), "Test2");
        Assertions.assertEquals(categories1.size(), 3);
    }

    @Test
    void getCategory() {
        Category category = new Category("1", "Test");
        when(categoryDAL.getCategoryById("1")).thenReturn(category);

        CategoryDTO categoryToCheck = categoryService.getCategoryById("1");
        Assertions.assertEquals(categoryToCheck.getCategoryName(), "Test");
    }

    @Test
    void getCategory_WithInvalidId_ReturnNull() {
        Category category = new Category("1", "Test");
        lenient().when(categoryDAL.getCategoryById("1")).thenReturn(category);

        CategoryDTO categoryToCheck = categoryService.getCategoryById("100");
        Assertions.assertNull(categoryToCheck);
    }

    @Test
    void addCategory() {
        //Given
        Category category = new Category(null, "Test");
        //When
        CategoryDTO categoryDTO = new CategoryDTO(null,"Test");
        categoryService.addCategory(categoryDTO);
        //then
        ArgumentCaptor<Category> CategoryArgumentCaptor = ArgumentCaptor.forClass(Category.class);
        verify(categoryDAL).addCategory(CategoryArgumentCaptor.capture());
        Category captureCategory = CategoryArgumentCaptor.getValue();
        Assertions.assertEquals(category, captureCategory);
    }

    @Test
    void deleteCategory() {
        //Given
        Category category = new Category("1","Test");
        //When
        when(categoryDAL.getCategoryById("1")).thenReturn(category);
        categoryService.deleteCategory("1");
        //then
        verify(categoryDAL).deleteCategory(category);
    }

    @Test
    void deleteCategory_WithInvalidId_returnFalse() {
        //When
        when(categoryDAL.getCategoryById("1000")).thenReturn(null);
        //then
        Assertions.assertFalse(categoryService.deleteCategory("1000"));
    }

    @Test
    void editCategory_WithCorrectId() {
        //Given
        Category category = new Category("1","Test");
        //When
        when(categoryDAL.getCategoryById("1")).thenReturn(category);
        CategoryDTO categoryDTO = new CategoryDTO("1", "Test");
        categoryService.editCategory(categoryDTO);
        //then
        ArgumentCaptor<Category> CategoryArgumentCaptor = ArgumentCaptor.forClass(Category.class);
        verify(categoryDAL).editCategory(CategoryArgumentCaptor.capture());
        Category captureCategory = CategoryArgumentCaptor.getValue();
        Assertions.assertEquals(category, captureCategory);
    }

    @Test
    void editCategory_WithInvalidId() {
        //given
        CategoryDTO category = new CategoryDTO("10", "test");
        //when
        when(categoryDAL.getCategoryById("10")).thenReturn(null);
        boolean expected = categoryService.editCategory(category);
        //then
        Assertions.assertFalse(expected); //Invalid id return false
    }
}