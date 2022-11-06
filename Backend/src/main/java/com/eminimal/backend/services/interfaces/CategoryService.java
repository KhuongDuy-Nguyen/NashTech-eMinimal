package com.eminimal.backend.services.interfaces;

import com.eminimal.backend.models.Category;

import javax.xml.bind.ValidationException;
import java.util.List;

public interface CategoryService {
    //  Find category
    List<Category> findAll();

    Category findById(String id) throws Exception;

    List<Category> findByName(String name) throws Exception;

    abstract <S extends Category> S save(S entity) throws Exception;

    String deleteById(String uuid) throws Exception;

    Category updateCategory(Category newCategory) throws Exception;
}
