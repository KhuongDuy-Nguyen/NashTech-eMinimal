package com.eminimal.backend.services.impl;

import com.eminimal.backend.models.Category;
import com.eminimal.backend.models.Product;
import com.eminimal.backend.models.Rating;
import com.eminimal.backend.repository.ProductRepository;
import com.eminimal.backend.services.interfaces.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryServiceImpl categoryService;


//  Find product
    @Override
    public List<Product> findAll(){
        return productRepository.findAll();
    }

//    @Override
//    public List<ProductDetails> findAllProductDetails(){
//        return detailsRepository.findAll();
//    }

    @Override
    public Product findById(String id) throws Exception {
        Product product =  productRepository.findByProductID(id);
        if(product == null){
            throw new Exception("Can't find product with id: " + id);
        }else{
            return product;
        }
    }

    @Override
    public List<Product> findByName(String name){
        return productRepository.findByProductNameContaining(name);
    }

    @Override
    public List<Product> findByCategory(String name){
        return productRepository.findByCategories_CategoryName(name);
    }

//  Create product
    @Override
    public <S extends Product> S save(S entity) throws Exception {
//        Check category valid
        Category category = categoryService.findByCategoryName(entity.getCategories().getCategoryName());
        entity.setCategories(category);

//        detailsRepository.save(entity.getDetails());
//        logger.error("Entity: "  + entity);
        return productRepository.save(entity);
    }

//  Delete product
    @Override
    public String deleteById(String id) {
        Product product = productRepository.findByProductID(id);
        productRepository.deleteById(id);
        return "Remove success with product ID: " + id;
    }

//  Update product
    @Override
    public Product updateProduct(Product newProduct) throws Exception {
        Product product = findById(newProduct.getProductID());
        Category category = categoryService.findByCategoryName(newProduct.getCategories().getCategoryName());

//        TODO: Fix date create - It's create new Date() when update

        product = Product.builder()
                 .productID(newProduct.getProductID())
                 .productName(newProduct.getProductName())
                 .productDesc(newProduct.getProductDesc())
                 .productImage(newProduct.getProductImage())
                 .productCost(newProduct.getProductCost())
                 .productRating(newProduct.getProductRating())
                 .productAmount(newProduct.getProductAmount())

                 .dateCreate(newProduct.getDateCreate())
                 .dateUpdate(new Date())

                 .productSale(newProduct.getProductSale())
                 .dateSale(newProduct.getDateSale())

                 .categories(category)
                 .build();

        return productRepository.save(product);
    }

    @Override
    public Product ratingProduct(String productID, Rating rating) throws Exception {
        Product product = findById(productID);
        product.getProductRating().add(rating);
        return productRepository.save(product);
    }

    @Override
    public List<Product> getProductSale(){
        return productRepository.findByOrderByProductSaleDesc();
    }

    @Override
    public List<Product> OrderProductCostDesc(){
        return productRepository.findByOrderByProductCostDesc();
    }

    @Override
    public List<Product> OrderProductCostAsc(){
        return productRepository.findByOrderByProductCostAsc();
    }

    @Override
    public List<Product> OrderProductNameDesc(){
        return productRepository.findByOrderByProductNameDesc();
    }

    @Override
    public List<Product> OrderProductNameAsc(){
        return productRepository.findByOrderByProductNameAsc();
    }
}
