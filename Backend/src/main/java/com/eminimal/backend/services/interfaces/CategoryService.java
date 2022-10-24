package com.eminimal.backend.services.interfaces;

import com.eminimal.backend.models.Category;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;


public interface CategoryService {
    //  Find product
    List<Category> findAll() throws ExecutionException, InterruptedException;

    Category findById(String uuid) throws ExecutionException, InterruptedException;

    //  Create new product
    String save(Category category) throws ExecutionException, InterruptedException;

    String deleteById(String uuid);


    //  Update product
    String updateCategory(Category category) throws ExecutionException, InterruptedException;
}
