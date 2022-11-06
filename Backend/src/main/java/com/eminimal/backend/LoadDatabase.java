package com.eminimal.backend;

import com.eminimal.backend.models.*;
import com.eminimal.backend.repository.*;
import com.eminimal.backend.services.interfaces.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

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

            Random random = new Random();
            for (int i = 1; i <= 50 ; i++){
                productRepository.save(new Product(
                        "Ghe xoay " + i,
                        "Day la ghe xoay",
                        list,
                        random.nextInt(100000),
                        new ProductDetails(random.nextInt(100), random.nextInt(50), new Date(), categoryRepository.findByCategoryName("Chairs"))
                ));
            }

            for (int i = 1; i <= 10 ; i++){
                userService.save(new Users(
                        "admin" + i,
                        "123",
                        "admin" + i + "@gmail.com",
                        new UserDetails(
                                "0794536542",
                                "BTD",
                                "Ho Chi Minh",
                                true,
                                "ADMIN"
                        )
                ));
            }

            for (int i = 1; i <= 10 ; i++){
                userService.save(new Users(
                        "user" + i,
                        "123",
                        "user" + i + "@gmail.com"
                ));
            }
        };
    }

}
