package com.eminimal.backend.services.impl;

import com.eminimal.backend.models.Category;
import com.eminimal.backend.models.Product;
import com.eminimal.backend.models.Rating;
import com.eminimal.backend.repository.CategoryRepository;
import com.eminimal.backend.repository.ProductRepository;
import com.eminimal.backend.services.interfaces.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    ProductRepository productRepository;

    @Mock
    CategoryRepository categoryRepository;

    @InjectMocks
    ProductServiceImpl productService;


    @Mock
    CategoryServiceImpl categoryService;




//    @Autowired
//    ProductRepository productRepository;

//    @InjectMocks
//    ProductServiceImpl productServiceImpl;

    private final static Logger log = LoggerFactory.getLogger(ProductServiceImplTest.class);


    Product initProduct;
    Product expectedProduct;
    Category category;

    private final List<Product> mockProduct = new ArrayList<>();

    @BeforeEach
    void setup() {
        initProduct = mock(Product.class);
        expectedProduct = mock(Product.class);
        category = mock(Category.class);

        for(int i =0; i< 5; i++){
            mockProduct.add(new Product());
        }

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
    @Disabled
//    BUG:  Cannot invoke "com.eminimal.backend.models.Category.getCategoryName()" because the return value of "com.eminimal.backend.models.Product.getCategories()" is null
    void save() throws Exception {
        Product initProduct = mock(Product.class);

        Category newCategory = new Category("name", "desc");
        when(categoryRepository.findByCategoryName("name")).thenReturn(newCategory);
        initProduct.setCategories(category);
        when(productRepository.save(initProduct)).thenReturn(expectedProduct);

        Product product = new Product("1", "name", "desc", 15f, newCategory);
        log.info("Result category " + categoryRepository.findByCategoryName(product.getCategories().getCategoryName()));
        log.info("Product: " + product);

        Product result = productService.save(product);
        log.info("Result: " + productService.save(product));
        assertThat(expectedProduct).isEqualTo(result);

    }

    @Test
    void deleteById() {
        when(productRepository.findByProductID("1")).thenReturn(new Product());

        productService.deleteById("1");
        verify(productRepository, times(1)).deleteById("1");
    }

    @Test
    @Disabled
    void updateProduct() throws Exception {
        when(productRepository.findByProductID("1")).thenReturn(initProduct);
        when(categoryRepository.findByCategoryID("1")).thenReturn(category);
        initProduct.setCategories(category);
        when(productRepository.save(initProduct)).thenReturn(expectedProduct);

        List<String> urlList = new ArrayList<>();
        List<Rating> ratingList = new ArrayList<>();

        ratingList.add(new Rating("1", 5));
        urlList.add("url1");

        Product product = new Product(
                "1", "name", "desc", 15f, categoryRepository.findByCategoryID("1")
        );

        Product result = productService.updateProduct(product);
        verify(initProduct).setProductName("name");
        verify(initProduct).setProductDesc("desc");

        assertThat(result).isEqualTo(expectedProduct);
    }

    @Test
    void ratingProduct() throws Exception {
        when(productRepository.findByProductID("1")).thenReturn(initProduct);
        initProduct.getProductRating().add(new Rating("1", 5));
        when(productRepository.save(initProduct)).thenReturn(expectedProduct);

        Product result = productService.ratingProduct("1", new Rating("1", 5));
        assertThat(result).isEqualTo(expectedProduct);

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