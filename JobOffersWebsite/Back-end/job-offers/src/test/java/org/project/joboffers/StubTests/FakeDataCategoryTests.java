package org.project.joboffers.StubTests;

import org.project.joboffers.Model.Category;
import org.project.joboffers.DAL.StubDAL.FakeJobDAL;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

class FakeDataCategoryTests {

    @Test
    void GetAllCategories_returnAllCategoriesTest(){
        //arrange
        FakeJobDAL fakeJobDAL = new FakeJobDAL();
        List<Category> actual = new ArrayList<>();
        actual.add(new Category("1", "FullTime" ));
        actual.add(new Category("2", "PartTime"));
        actual.add(new Category("3", "Temporary"));
        actual.add(new Category("4", "Side Job"));
        //act
        List<Category> expected = fakeJobDAL.findAllCategories();
        //assert
        Assertions.assertNotNull(expected);
        Assertions.assertEquals(expected.size(), 4);
        Assertions.assertEquals(expected.get(0).getCategoryName(), actual.get(0).getCategoryName());
        Assertions.assertEquals(expected.get(1).getCategoryName(), actual.get(1).getCategoryName());
        Assertions.assertEquals(expected.get(2).getCategoryName(), actual.get(2).getCategoryName());
        Assertions.assertEquals(expected.get(3).getCategoryName(), actual.get(3).getCategoryName());
    }

    @Test
    void GetCategory_returnTheCorrectCategoryTest(){
        //arrange
        FakeJobDAL fakeJobDAL = new FakeJobDAL();
        Category actual = new Category("1", "FullTime");
        //act
        Category expected = fakeJobDAL.getCategoryById("1");
        //assert
        Assertions.assertNotNull(expected);
        Assertions.assertEquals(expected.getCategoryName(),actual.getCategoryName());
        Assertions.assertEquals(expected.getCategoryId(),actual.getCategoryId());
    }

    @Test
    void GetCategory_returnNullObjectTest(){
        //arrange
        FakeJobDAL fakeJobDAL = new FakeJobDAL();
        //act
        Category expected = fakeJobDAL.getCategoryById("1000");
        //assert
        Assertions.assertNull(expected);
    }

    @Test
    void AddCategory_returnTrueTest(){
        //arrange
        FakeJobDAL fakeJobDAL = new FakeJobDAL();
        Category category = new Category("6", "Test");
        //act
        boolean expected = fakeJobDAL.addCategory(category);
        Category checkCategory = fakeJobDAL.getCategoryById("6");// I will check of this category is successfully added to the list
        //assert
        Assertions.assertTrue(expected);
        Assertions.assertNotNull(checkCategory);
        Assertions.assertEquals(category.getCategoryName(), checkCategory.getCategoryName());
    }

    @Test
     void AddCategory_returnFalseTest(){
        //arrange
        FakeJobDAL fakeJobDAL = new FakeJobDAL();
        Category category = new Category("1", "Test11");
        //act
        boolean expected = fakeJobDAL.addCategory(category);
        Category checkCategory = fakeJobDAL.getCategoryById("1"); // I will test that the list didn't change
        //assert
        Assertions.assertFalse(expected);
        Assertions.assertNotEquals(category.getCategoryName() , checkCategory.getCategoryName()); // so I have to compare these two objects in order to know that the list didn't change.
    }

    @Test
     void DeleteCategory_returnTrueTest(){
        //arrange
        FakeJobDAL fakeJobDAL = new FakeJobDAL();
        //act
        boolean expected = fakeJobDAL.removeCategory("2");
        Category checkCategory = fakeJobDAL.getCategoryById("2"); //I will check if this object is deleted successfully
        //assert
        Assertions.assertTrue(expected);
        Assertions.assertNull(checkCategory); //If this object is null, then I know that this object is deleted
    }

    @Test
     void DeleteCategory_returnFalseTest(){
        //arrange
        FakeJobDAL fakeJobDAL = new FakeJobDAL();
        //act
        boolean expected = fakeJobDAL.removeJob("2000");
        Category CheckCategory = fakeJobDAL.getCategoryById("2000"); // Check if this object already exist
        //assert
        Assertions.assertNull(CheckCategory);// this object is null if it doesn't exist
        Assertions.assertFalse(expected);
    }

    @Test
     void EditCategory_returnTrueTest() {
        //arrange
        FakeJobDAL fakeJobDAL = new FakeJobDAL();
        Category category = new Category("1", "");
        //act
        boolean expected = fakeJobDAL.editCategory(category);
        Category checkCategory = fakeJobDAL.getCategoryById("1");//Check if this object is updated successfully
        //assert
        Assertions.assertTrue(expected);
        Assertions.assertEquals(category.getCategoryName(), checkCategory.getCategoryName()); //The first object has to be equal to the other object
    }

    @Test
     void EditCategory_returnFalseTest(){
        //arrange
        FakeJobDAL fakeJobDAL = new FakeJobDAL();
        Category category = new Category("1000" , "");
        //act
        boolean expected = fakeJobDAL.editCategory(category);
        Category checkCategory = fakeJobDAL.getCategoryById("1000");  //Check this id if exist in the data store
        //assert
        Assertions.assertFalse(expected);
        Assertions.assertNull(checkCategory);// this id not exist in the data store, so it is a null object
    }
}
