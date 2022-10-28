package com.eminimal.backend.services.impl;

import com.eminimal.backend.models.Category;
import com.eminimal.backend.models.product.Product;
import com.eminimal.backend.models.product.ProductDetails;
import com.eminimal.backend.repository.ProductDetailsRepository;
import com.eminimal.backend.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceImpl{

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductDetailsRepository detailsRepository;

    @Autowired
    private CategoryServiceImpl categoryService;

//  Find product
    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public List<ProductDetails> findAllProductDetails(){
        return detailsRepository.findAll();
    }

    public Product findById(String id) throws Exception {
        Product product =  productRepository.findByProductID(id);
        if(product == null){
            throw new Exception("Can't find product with id: " + id);
        }else{
            return product;
        }
    }

    public ProductDetails findProductDetailID(String id) throws Exception {
        ProductDetails details =  detailsRepository.findByProductDetailID(id);
        if(details == null){
            throw new Exception("Can't find product detail with id: " + id);
        }else{
            return details;
        }
    }


    public List<Product> findByName(String name){
        return productRepository.findByProductNameContaining(name);
    }


//  Create product
    public <S extends Product> S save(S entity) throws Exception {
//        Check category valid
        Category category = categoryService.findById(entity.getDetails().getCategories().getCategoryID());
        entity.getDetails().setCategories(category);

//        detailsRepository.save(entity.getDetails());
        return productRepository.save(entity);
    }

//  Delete product
    public String deleteById(String id) {
        Product product = productRepository.findByProductID(id);
        productRepository.deleteById(id);
        return "Remove success with product ID: " + id;
    }

//  Update product
    public Product updateProduct(Product newProduct) throws Exception {
        Product product = findById(newProduct.getProductID());
        ProductDetails details = findProductDetailID(product.getDetails().getProductDetailID());

        details = ProductDetails.builder()
                .productDetailID(details.getProductDetailID())
                .dateCreate(details.getDateCreate())
                .dateUpdate(new Date())
                .productAmount(newProduct.getDetails().getProductAmount())
                .productSale(newProduct.getDetails().getProductSale())
                .dateSale(newProduct.getDetails().getDateSale())
                .categories(newProduct.getDetails().getCategories())
                .build();

        product = Product.builder()
                 .productID(newProduct.getProductID())
                 .productName(newProduct.getProductName())
                 .productDesc(newProduct.getProductDesc())
                 .productImage(newProduct.getProductImage())
                 .productCost(newProduct.getProductCost())
                 .productRating(newProduct.getProductRating())
                 .details(details)
                 .build();

        return productRepository.save(product);
    }

    public Product ratingProduct(String productID, int rating) throws Exception {
        Product product = findById(productID);
        product.getProductRating().add(rating);
        return productRepository.save(product);
    }
}
