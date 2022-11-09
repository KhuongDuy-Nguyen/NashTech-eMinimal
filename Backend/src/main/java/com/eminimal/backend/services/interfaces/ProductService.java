package com.eminimal.backend.services.interfaces;

import com.eminimal.backend.models.Product;
import com.eminimal.backend.models.Rating;

import java.util.List;

public interface ProductService {
    //  Find product
    List<Product> findAll();

//    List<ProductDetails> findAllProductDetails();

    Product findById(String id) throws Exception;

//    ProductDetails findProductDetailID(String id) throws Exception;

    List<Product> findByName(String name);

    List<Product> findByCategory(String name);

    List<Product> getProductSale();

    //  Create product
    <S extends Product> S save(S entity) throws Exception;

    //  Delete product
    String deleteById(String id);

    //  Update product
    Product updateProduct(Product newProduct) throws Exception;

    Product ratingProduct(String productID, Rating rating) throws Exception;

    public List<Product> OrderProductCostDesc();

    public List<Product> OrderProductCostAsc();

    public List<Product> OrderProductNameDesc();
    public List<Product> OrderProductNameAsc();
}
