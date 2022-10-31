package com.eminimal.backend;

import com.eminimal.backend.models.Category;
import com.eminimal.backend.models.product.Product;
import com.eminimal.backend.models.product.ProductDetails;
import com.eminimal.backend.models.users.Users;
import com.eminimal.backend.repository.*;
import com.eminimal.backend.services.impl.UserAuthServiceImpl;
import com.eminimal.backend.services.interfaces.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(CategoryRepository categoryRepository,
                                   ProductRepository productRepository,
                                   UserService userService,
                                   CartRepository cartRepository){
        return args -> {
            categoryRepository.deleteAll();
            productRepository.deleteAll();
            cartRepository.deleteAll();
//            usersRepository.deleteAll();
//          Insert Category
            log.info("Inserting --> " + categoryRepository.save(new Category("Sofas", "Tong hop cac loai sofa")));
            log.info("Inserting --> " + categoryRepository.save(new Category("Lighting", "Tong hop cac loai den trang tri")));
            log.info("Inserting --> " + categoryRepository.save(new Category("Beds", "Tong hop cac loai giuong")));
            log.info("Inserting --> " + categoryRepository.save(new Category("Lamp", "Tong hop cac loai den")));
            log.info("Inserting --> " + categoryRepository.save(new Category("Stools", "Tong hop cac loai ghe co dinh")));
            log.info("Inserting --> " + categoryRepository.save(new Category("Chairs", "Tong hop cac loai ghe di dong")));

//          Insert product
            List<String> list = new ArrayList<>();
            list.add("url-1");
            list.add("url-2");
            list.add("url-3");

            log.info("Inserting --> " + productRepository.save(new Product(
                    "Ghe xoay 1",
                    "Day la ghe xoay",
                    list,
                    12.5f,
                    new ProductDetails(100, categoryRepository.findByCategoryName("Sofas"))
            )));

            log.info("Inserting --> " + productRepository.save(new Product(
                    "Ghe xoay 2",
                    "Day la ghe xoay",
                    list,
                    12.5f,
                    new ProductDetails(100, categoryRepository.findByCategoryName("Sofas"))
            )));

            log.info("Inserting --> " + productRepository.save(new Product(
                    "Ghe xoay 3",
                    "Day la ghe xoay",
                    list,
                    12.5f,
                    new ProductDetails(100, categoryRepository.findByCategoryName("Beds"))
            )));

            log.info("Inserting --> " + productRepository.save(new Product(
                    "Ghe xoay 4",
                    "Day la ghe xoay",
                    list,
                    12.5f,
                    new ProductDetails(100, categoryRepository.findByCategoryName("Beds"))
            )));

            log.info("Inserting --> " + userService.save(new Users(
                    "admin",
                    "123",
                    "admin@gmail.com"
            )));
        };
    }

}
