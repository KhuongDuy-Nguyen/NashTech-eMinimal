package com.eminimal.backend;

import com.eminimal.backend.models.Product;
import com.eminimal.backend.repository.CategoryRepository;
import com.eminimal.backend.repository.ProductRepository;
import com.eminimal.backend.repository.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(CategoryRepository categoryRepository,
                                   ProductRepository productRepository,
                                   UsersRepository usersRepository){
        return args -> {
            usersRepository.deleteAll();
            productRepository.deleteAll();
            categoryRepository.deleteAll();


//          Insert Category
//            log.info("Inserting --> " + categoryRepository.save(new Category("Sofas", "Tong hop cac loai sofa")));
//            log.info("Inserting --> " + categoryRepository.save(new Category("Lighting", "Tong hop cac loai den trang tri")));
//
//            log.info("Inserting --> " + categoryRepository.save(new Category("Beds", "Tong hop cac loai giuong")));
//            log.info("Inserting --> " + categoryRepository.save(new Category("Lamp", "Tong hop cac loai den")));
//
//            log.info("Inserting --> " + categoryRepository.save(new Category("Stools", "Tong hop cac loai ghe co dinh")));
//            log.info("Inserting --> " + categoryRepository.save(new Category("Chairs", "Tong hop cac loai ghe di dong")));

            log.info("Inserting --> " + productRepository.save(new Product(
                    "Ghe xoay 1",
                    "Day la ghe xoay",
                    "...",
                    12.5f,
                    99,
                    categoryRepository.findByCategoryName("Sofas")
            )));

            log.info("Inserting --> " + productRepository.save(new Product(
                    "Ghe xoay 2",
                    "Day la ghe xoay",
                    "...",
                    12.5f,
                    99,
                    categoryRepository.findByCategoryName("Sofas")
            )));

            log.info("Inserting --> " + productRepository.save(new Product(
                    "Ghe xoay 3",
                    "Day la ghe xoay",
                    "...",
                    12.5f,
                    99,
                    categoryRepository.findByCategoryName("Beds")
            )));

            log.info("Inserting --> " + productRepository.save(new Product(
                    "Ghe xoay 4",
                    "Day la ghe xoay",
                    "...",
                    12.5f,
                    99,
                    categoryRepository.findByCategoryName("Beds")
            )));

//            log.info("Inserting --> " + rolesRepository.save(new UsersRole(
//                    "Guest"
//            )));
//
//            log.info("Inserting --> " + rolesRepository.save(new UsersRole(
//                    "Admin"
//            )));

        };
    }

}
