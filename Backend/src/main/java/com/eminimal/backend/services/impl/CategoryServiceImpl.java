package com.eminimal.backend.services.impl;

import com.eminimal.backend.models.Category;
import com.eminimal.backend.repository.CategoryRepository;
import com.eminimal.backend.services.interfaces.CategoryService;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
public class CategoryServiceImpl implements CategoryService {
    private static final String COLLECTION_NAME = "category";
    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);
    private final Firestore dbFirestore = FirestoreClient.getFirestore();


    @Autowired
    private CategoryRepository repository;

//  Find product
    @Override
    public List<Category> findAll() throws ExecutionException, InterruptedException {
        List<Category> categoryList = new ArrayList<>();
        ApiFuture<QuerySnapshot> future = dbFirestore.collection(COLLECTION_NAME).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            Category category = document.toObject(Category.class);
            categoryList.add(category);
        }
        return categoryList;
    }



    @Override
    public Category findById(String id) throws ExecutionException, InterruptedException {
        ApiFuture<DocumentSnapshot> future = dbFirestore.collection(COLLECTION_NAME).document(id).get();
        DocumentSnapshot document = future.get();
        if(document.exists()){
            return document.toObject(Category.class);
        }else{
            throw new ExecutionException("Can't find category with id: " + id, null);
        }
    }

//  Create new product
    @Override
    public String save(Category category) throws ExecutionException, InterruptedException {
        dbFirestore.collection(COLLECTION_NAME).document(category.getCategoryID()).set(category);
        return "Create success";
    }

//  Delete product
    @Override
    public String deleteById(String uuid) {
        dbFirestore.collection(COLLECTION_NAME).document(uuid).delete();
        return "Remove success with category ID: " + uuid;
    }

//  Update product
    @Override
    public String updateCategory(Category category) throws ExecutionException, InterruptedException {
        findById(category.getCategoryID());
        dbFirestore.collection(COLLECTION_NAME).document(category.getCategoryID()).update(
                "categoryDesc", category.getCategoryDesc(),
                "categoryName", category.getCategoryName());
        return "Update success";
    }

}
