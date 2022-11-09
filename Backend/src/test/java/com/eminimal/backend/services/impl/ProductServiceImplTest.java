package com.eminimal.backend.services.impl;

import com.eminimal.backend.exceptions.NotFoundException;
import com.eminimal.backend.models.Category;
import com.eminimal.backend.models.Product;
import com.eminimal.backend.models.Rating;
import com.eminimal.backend.models.Users;
import com.eminimal.backend.repository.CategoryRepository;
import com.eminimal.backend.repository.ProductRepository;
import com.eminimal.backend.repository.RatingRepository;
import com.eminimal.backend.repository.UsersRepository;
import com.eminimal.backend.services.interfaces.CategoryService;
import com.eminimal.backend.services.interfaces.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    ProductRepository productRepository;

    @Mock
    CategoryRepository categoryRepository;

    @Mock
    UsersRepository usersRepository;

    @Mock
    RatingRepository ratingRepository;

    @InjectMocks
    ProductServiceImpl productService;

    @Mock
    CategoryService categoryService;

    @Mock
    UserService userService;


    private final static Logger log = LoggerFactory.getLogger(ProductServiceImplTest.class);


    Product initProduct;
    Product expectedProduct;
    Category initCategory;
    Users initUsers;

    private final List<Product> mockProduct = new ArrayList<>();

    @BeforeEach
    void setup() throws Exception {
        initProduct = mock(Product.class);
        expectedProduct = mock(Product.class);
        initCategory = mock(Category.class);
        initUsers = mock(Users.class);

        for(int i =0; i< 5; i++){
            mockProduct.add(new Product());
        }

//        when(categoryService.findById("1")).thenReturn(initCategory);
        initCategory = new Category("1", "name", "desc");
        initProduct = new Product("1","name", "desc", 15f, initCategory);
        initUsers = new Users("1","ad", "123", "ad@gmail.com", "123456", "asd", "asd", false, "ADMIN" );

    }


    @Test
    void findAll() {
        when(productRepository.findAll()).thenReturn(mockProduct);

        List<Product> actualCategory = productService.findAll();

        assertThat(actualCategory.size()).isEqualTo(mockProduct.size());
        verify(productRepository).findAll();
    }

    @Test
    void findById() throws Exception {
        when(productRepository.findByProductID("1")).thenReturn(initProduct);

//        Product result = productService.findById("1");
        assertThat(initProduct).isEqualTo(productService.findById("1"));
    }

    @Test
    void findById_ShouldThrowException_WhenNotFound(){
        NotFoundException actualException = assertThrows(NotFoundException.class, () -> productService.findById("2"));
        assertEquals("Can't find product with id: 2", actualException.getMessage());
    }

    @Test
    void findByName() {
        List<Product> initProduct = new ArrayList<>();

        when(productRepository.findByProductNameContaining("a")).thenReturn(initProduct);
        List<Product> result = productService.findByName("a");
        assertThat(result).isEqualTo(initProduct);
    }

    @Test
    void findByCategory() {
        List<Product> initProduct = new ArrayList<>();

        when(productRepository.findByCategories_CategoryName("a")).thenReturn(initProduct);
        List<Product> result = productService.findByCategory("a");
        assertThat(result).isEqualTo(initProduct);
    }


    @Test
//    @Disabled
    void save() throws Exception {
        Product product = Product.builder().productName("name").productDesc("desc").categories(new Category("1", "name", "desc")).build();

        when(productRepository.save(any(Product.class))).thenReturn(initProduct);
//        when(categoryRepository.findByCategoryID("1")).thenReturn(category);

        Product result = productService.save(product);
        assertEquals(result, initProduct);

    }

    @Test
    void deleteById() {
        when(productRepository.findByProductID("1")).thenReturn(new Product());

        productService.deleteById("1");
        verify(productRepository, times(1)).deleteById("1");
    }

    @Test
    void updateProduct() throws Exception {

        when(productRepository.findByProductID("1")).thenReturn(initProduct);
        when(productRepository.save(initProduct)).thenReturn(expectedProduct);

        when(categoryRepository.findByCategoryID("1")).thenReturn(initCategory);

        Product product = new Product(
                "1", "product name", "product desc", 15f,
                categoryRepository.findByCategoryID("1")
        );

        Product result = productService.updateProduct(product);
        assertEquals(result, expectedProduct);

//        I don't know how to verify
//        verify(result).setProductName("product name");
//        verify(result).setProductDesc("product desc");
    }

    @Test
    void ratingProduct() throws Exception {
        String userID = "";
        when(productRepository.findByProductID("1")).thenReturn(initProduct);
        initProduct.getProductRating().add(new Rating("1", 5));

        when(productRepository.save(initProduct)).thenReturn(expectedProduct);
//        when(usersRepository.findByUserId("2")).thenReturn(initUsers);

        Product result = productService.ratingProduct("1", new Rating("2", 5));
        assertEquals(result, expectedProduct);
    }

    @Test
    void ratingProduct_ShouldThrowException_WhenValueRatingLessThan1(){
        when(productRepository.findByProductID("1")).thenReturn(initProduct);
        Rating newRating = new Rating("1", 0);
        Exception actualException = assertThrows(Exception.class, () -> productService.ratingProduct("1", newRating));
        assertEquals("Rating value not valid", actualException.getMessage());
    }

    @Test
    void ratingProduct_ShouldThrowException_WhenValueRatingLangeThan5(){
        when(productRepository.findByProductID("1")).thenReturn(initProduct);
        Rating newRating = new Rating("1", 6);
        Exception actualException = assertThrows(Exception.class, () -> productService.ratingProduct("1", newRating));
        assertEquals("Rating value not valid", actualException.getMessage());
    }


    @Test
    void getProductSale() {
        List<Product> productsList = new ArrayList<>();
        when(productRepository.findByOrderByProductSaleDesc()).thenReturn(productsList);

        List<Product> result = productService.getProductSale();
        assertThat(result).isEqualTo(productsList);
        verify(productRepository).findByOrderByProductSaleDesc();
    }

    @Test
    void orderProductCostDesc() {
        List<Product> productsList = new ArrayList<>();
        when(productRepository.findByOrderByProductCostDesc()).thenReturn(productsList);

        List<Product> result = productService.OrderProductCostDesc();
        assertThat(result).isEqualTo(productsList);
        verify(productRepository).findByOrderByProductCostDesc();
    }

    @Test
    void orderProductCostAsc() {
        List<Product> productsList = new ArrayList<>();
        when(productRepository.findByOrderByProductCostAsc()).thenReturn(productsList);

        List<Product> result = productService.OrderProductCostAsc();
        assertThat(result).isEqualTo(productsList);
        verify(productRepository).findByOrderByProductCostAsc();
    }

    @Test
    void orderProductNameDesc() {
        List<Product> productsList = new ArrayList<>();
        when(productRepository.findByOrderByProductNameDesc()).thenReturn(productsList);

        List<Product> result = productService.OrderProductNameDesc();
        assertThat(result).isEqualTo(productsList);
        verify(productRepository).findByOrderByProductNameDesc();
    }

    @Test
    void orderProductNameAsc() {
        List<Product> productsList = new ArrayList<>();
        when(productRepository.findByOrderByProductNameAsc()).thenReturn(productsList);

        List<Product> result = productService.OrderProductNameAsc();
        assertThat(result).isEqualTo(productsList);
        verify(productRepository).findByOrderByProductNameAsc();
    }


}