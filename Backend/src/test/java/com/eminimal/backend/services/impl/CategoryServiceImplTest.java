package com.eminimal.backend.services.impl;

import com.eminimal.backend.exceptions.NotFoundException;
import com.eminimal.backend.exceptions.ValidationException;
import com.eminimal.backend.models.Category;
import com.eminimal.backend.models.Product;
import com.eminimal.backend.repository.CategoryRepository;
import com.eminimal.backend.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {
    @Mock
    CategoryRepository categoryRepository;

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    CategoryServiceImpl categoryServiceImpl;


    Category initCategory;
    Category expectedCategory;

    private final List<Category> mockCategory = new ArrayList<>();

    @BeforeEach
    void setup() {
        for(int i =0; i< 5; i++){
            mockCategory.add(new Category(Integer.toString(i), "name " + i, "desc " + i));
        }

    }

    @Test
    void findAll_ShouldReturnList_WhenGetAll() {
//        assertEquals(10, categoryService.findAll().size());

        when(categoryRepository.findAll()).thenReturn(mockCategory);

        List<Category> actualCategory = categoryServiceImpl.findAll();

        assertThat(actualCategory.size()).isEqualTo(mockCategory.size());
        verify(categoryRepository).findAll();
    }

//    Find BY ID
    @Test
    void findById_ShouldReturnCategory_WhenFoundById(){
        Category initCategory = mock(Category.class);
        when(categoryRepository.findByCategoryID("1")).thenReturn(initCategory);

        Category actualCategory = categoryServiceImpl.findById("1");

        assertThat(actualCategory).isEqualTo(initCategory);
    }

    @Test
    void findById_ShouldThrowNotFound_WhenNotFoundById(){
        String invalidID = "1";
        NotFoundException actualException = assertThrows(NotFoundException.class, () -> categoryServiceImpl.findById(invalidID));
        assertEquals("Category not found", actualException.getMessage());
    }

    @Test
    void findById_ShouldThrowException_WhenIdIsEmpty(){
        String invalidID = "";
        ValidationException actualException = assertThrows(ValidationException.class, () -> categoryServiceImpl.findById(invalidID));
        assertEquals("ID is requirement", actualException.getMessage());
    }

    @Test
    void findById_ShouldThrowException_WhenIdIsBlank(){
        String invalidID = " ";
        ValidationException actualException = assertThrows(ValidationException.class, () -> categoryServiceImpl.findById(invalidID));
        assertEquals("ID is requirement", actualException.getMessage());
    }

//    Find by name
    @Test
    void findByName_ShouldReturnCategory_WhenFoundByName() {
        when(categoryRepository.findByCategoryNameContaining("name")).thenReturn(mockCategory);
        List<Category> actualCategory = categoryServiceImpl.findByName("name");
        assertThat(actualCategory.size()).isEqualTo(mockCategory.size());
    }

    @Test
    void findByName_ShouldThrowNotFound_WhenNotFoundByName() {
        String invalidName = "name 1";
        NotFoundException actualException = assertThrows(NotFoundException.class, () -> categoryServiceImpl.findByName(invalidName));
        assertEquals("Category not found", actualException.getMessage());
    }

    @Test
    void findByName_ShouldThrowValid_WhenNameIsEmpty() {
        String invalidName = "";
        ValidationException actualException = assertThrows(ValidationException.class, () -> categoryServiceImpl.findByName(invalidName));
        assertEquals("Name is requirement", actualException.getMessage());
    }

    @Test
    void findByName_ShouldThrowException_WhenIdIsBlank(){
        String invalidName = " ";
        ValidationException actualException = assertThrows(ValidationException.class, () -> categoryServiceImpl.findByName(invalidName));
        assertEquals("Name is requirement", actualException.getMessage());
    }

//    Save
    @Test
    void save_ShouldReturnCategory_WhenDataValid() throws Exception {
        Category actualCategory = mock(Category.class);
        Category category = Category.builder().categoryID("1").categoryName("Chair").categoryDesc("This is chair").build();
        when(categoryRepository.save(category)).thenReturn(actualCategory);
        Category result = categoryServiceImpl.save(category);
        assertEquals(result, actualCategory);
    }

    @Test
    void save_ShouldThrowValidException_WhenCategoryNameInvalid() {
        Category category = Category.builder().categoryName("").categoryDesc("This is chair").build();
        ValidationException actualException = assertThrows(ValidationException.class, () -> categoryServiceImpl.save(category));

        assertEquals("Category name is require", actualException.getMessage());
    }

    @Test
    void save_ShouldThrowValidException_WhenCategoryDescInvalid() {
        Category category = Category.builder().categoryName("Chair").categoryDesc("").build();
        ValidationException actualException = assertThrows(ValidationException.class, () -> categoryServiceImpl.save(category));

        assertEquals("Category description is require", actualException.getMessage());
    }

    @Test
    void save_ShouldThrowNullPointException_WhenDataIsNull() {
        Category category = Category.builder().categoryName(null).categoryDesc(null).build();
        ValidationException actualException = assertThrows(ValidationException.class, () -> categoryServiceImpl.save(category));

        assertEquals("Category name is require" , actualException.getMessage());
    }

    @Test
    void save_ShouldThrowValidException_WhenBothInvalid() {
        Category category = Category.builder().categoryName("").categoryDesc("").build();
        ValidationException actualException = assertThrows(ValidationException.class, () -> categoryServiceImpl.save(category));

        assertEquals("Category name is require", actualException.getMessage());
    }

//    Delete
    @Test
    void deleteById_ShouldReturnMessage_WhenIdValid() throws Exception {
        when(categoryRepository.findByCategoryID("1")).thenReturn(new Category());

        categoryServiceImpl.deleteById("1");
        verify(categoryRepository, times(1)).deleteById("1");
    }

    @Test
    void deleteById_ShouldThrowException_WhenHaveProduct() throws Exception {
        initCategory = mock(Category.class);
        List<Product> initProduct = Collections.singletonList(mock(Product.class));

        when(categoryRepository.findByCategoryID("1")).thenReturn(initCategory);
        when(productRepository.findByCategories_CategoryID("1")).thenReturn(initProduct);


        Exception actualException = assertThrows(Exception.class, () -> categoryServiceImpl.deleteById("1"));
        assertEquals("There are products in category", actualException.getMessage());
    }

//    Update
    @Test
    void updateCategory_ShouldReturnCategory_WhenDataValid() throws Exception {
        initCategory = mock(Category.class);
        expectedCategory = mock(Category.class);

        when(categoryRepository.findByCategoryID("1")).thenReturn(initCategory);
        when(categoryRepository.save(initCategory)).thenReturn(expectedCategory);
        Category result = categoryServiceImpl.updateCategory(new Category("1", "name", "desc"));

        verify(initCategory).setCategoryName("name");
        verify(initCategory).setCategoryDesc("desc");

        assertEquals(result, expectedCategory);
    }

}