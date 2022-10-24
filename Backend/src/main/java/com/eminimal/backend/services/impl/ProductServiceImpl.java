package com.eminimal.backend.services.impl;

import com.eminimal.backend.models.Category;
import com.eminimal.backend.models.Product;
import com.eminimal.backend.repository.ProductRepository;
import com.eminimal.backend.services.interfaces.ProductService;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
public class ProductServiceImpl implements ProductService {

    private static final String COLLECTION_NAME = "product";
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    private final Firestore dbFirestore = FirestoreClient.getFirestore();

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private ProductRepository repository;

//  Find product
    @Override
    public List<Product> findAll() throws ExecutionException, InterruptedException {
        List<Product> productList = new ArrayList<>();
        ApiFuture<QuerySnapshot> future = dbFirestore.collection(COLLECTION_NAME).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            Product product = document.toObject(Product.class);
            productList.add(product);
        }
        return productList;
    }


    @Override
    public Product findById(String id) throws ExecutionException, InterruptedException {
        ApiFuture<DocumentSnapshot> future = dbFirestore.collection(COLLECTION_NAME).document(id).get();
        DocumentSnapshot document = future.get();
        if(document.exists()){
            return document.toObject(Product.class);
        }else{
            throw new ExecutionException("Can't find product with id: " + id, null);
        }
    }


//  Create product
    @Override
    public <S extends Product> String save(S entity) throws ExecutionException, InterruptedException {
        String categoryID = entity.getCategories().getCategoryID();
        ApiFuture<DocumentSnapshot> futureCategory = dbFirestore.collection("category").document(categoryID).get();
        DocumentSnapshot documentCategory = futureCategory.get();
        if(documentCategory.exists()){
            Category category = documentCategory.toObject(Category.class);
            entity.setCategories(category);
            ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(entity.getProductID()).set(entity);
            return "Create success";
        }else{
            throw new ExecutionException("Can't find category with id: " + categoryID, null);
        }
    }

//  Delete product
    @Override
    public String deleteById(String uuid) {
        dbFirestore.collection(COLLECTION_NAME).document(uuid).delete();
        return "Remove success with product ID: " + uuid;
    }

//  Update product
    @Override
    public String updateProduct(Product newProduct) throws ExecutionException, InterruptedException {
        Product product = findById(newProduct.getProductID());

        product.setDateUpdate(new Date());
        modelMapper.map(newProduct, product);
        dbFirestore.collection(COLLECTION_NAME).document(product.getProductID()).set(product);
        return "Update success";
    }

    public Product ratingProduct(String productID, int rating) throws ExecutionException, InterruptedException {
        Product product = findById(productID);
        product.getProductRating().add(rating);
        return repository.save(product);
    }
}
