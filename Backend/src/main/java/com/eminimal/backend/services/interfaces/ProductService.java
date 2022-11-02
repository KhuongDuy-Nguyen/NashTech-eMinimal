package com.eminimal.backend.services.interfaces;

import com.eminimal.backend.models.product.Product;
import com.eminimal.backend.models.product.ProductDetails;

import java.util.List;

public interface ProductService {
    //  Find product
    List<Product> findAll();

    List<ProductDetails> findAllProductDetails();

    Product findById(String id) throws Exception;

    ProductDetails findProductDetailID(String id) throws Exception;

    List<Product> findByName(String name);

    List<Product> findByCategory(String name);

    List<Product> getProductSale();

    //  Create product
    <S extends Product> S save(S entity) throws Exception;

    //  Delete product
    String deleteById(String id);

    //  Update product
    Product updateProduct(Product newProduct) throws Exception;

    Product ratingProduct(String productID, int rating) throws Exception;
}
