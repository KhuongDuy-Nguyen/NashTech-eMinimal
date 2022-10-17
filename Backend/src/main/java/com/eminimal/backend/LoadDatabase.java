package com.eminimal.backend;

import com.eminimal.backend.models.Category;
import com.eminimal.backend.models.Product;
import com.eminimal.backend.models.User;
import com.eminimal.backend.repository.CategoryRepository;
import com.eminimal.backend.repository.ProductRepository;
import com.eminimal.backend.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(CategoryRepository categoryRepository, ProductRepository productRepository, UserRepository userRepository){
        return args -> {
            productRepository.deleteAll();
            categoryRepository.deleteAll();
            userRepository.deleteAll();

//          Insert Category
            log.info("Inserting --> " + categoryRepository.save(new Category("Sofas", "Tong hop cac loai sofa")));
            log.info("Inserting --> " + categoryRepository.save(new Category("Lighting", "Tong hop cac loai den trang tri")));

            log.info("Inserting --> " + categoryRepository.save(new Category("Beds", "Tong hop cac loai giuong")));
            log.info("Inserting --> " + categoryRepository.save(new Category("Lamp", "Tong hop cac loai den")));

            log.info("Inserting --> " + categoryRepository.save(new Category("Stools", "Tong hop cac loai ghe co dinh")));
            log.info("Inserting --> " + categoryRepository.save(new Category("Chairs", "Tong hop cac loai ghe di dong")));

//          Insert Product
            log.info("Inserting --> " + productRepository.save(
                  new Product("Sofa hien dai 1",
                          "Day la chiec sofa cuc xin",
                          "...",
                          15.2f,
                          99,
                          categoryRepository.findCategoriesByCategoryName("Sofas")
                  ))
            );

            log.info("Inserting --> " + productRepository.save(
                    new Product("Sofa hien dai 2",
                            "Day la chiec sofa cuc xin",
                            "...",
                            15.2f,
                            99,
                            categoryRepository.findCategoriesByCategoryName("Sofas")
                    ))
            );

            log.info("Inserting --> " + productRepository.save(
                    new Product("Sofa hien dai 3",
                            "Day la chiec sofa cuc xin",
                            "...",
                            15.2f,
                            99,
                            categoryRepository.findCategoriesByCategoryName("Sofas")
                    ))
            );

            log.info("Inserting --> " + productRepository.save(
                    new Product("Sofa hien dai 4",
                            "Day la chiec sofa cuc xin",
                            "...",
                            15.2f,
                            99,
                            categoryRepository.findCategoriesByCategoryName("Sofas")
                    ))
            );

            log.info("Inserting --> " + productRepository.save(
                new Product("Den trang tri hinh con gau 1",
                        "Day la den trang tri cuc cute",
                        "...",
                        30f,
                        99,
                        categoryRepository.findCategoriesByCategoryName("Lighting")
                ))
            );

            log.info("Inserting --> " + productRepository.save(
                    new Product("Den trang tri hinh con gau 2",
                            "Day la den trang tri cuc cute",
                            "...",
                            30f,
                            99,
                            categoryRepository.findCategoriesByCategoryName("Lighting")
                    ))
            );

            log.info("Inserting --> " + productRepository.save(
                    new Product("Den trang tri hinh con gau 3",
                            "Day la den trang tri cuc cute",
                            "...",
                            30f,
                            99,
                            categoryRepository.findCategoriesByCategoryName("Lighting")
                    ))
            );

            log.info("Inserting --> " + productRepository.save(
                    new Product("Den trang tri hinh con gau 4",
                            "Day la den trang tri cuc cute",
                            "...",
                            30f,
                            99,
                            categoryRepository.findCategoriesByCategoryName("Lighting")
                    ))
            );

            log.info("Inserting --> " + userRepository.save(
                    new User(
                            "admin",
                            "123",
                            "admin@gmail.com"
                    ))
            );

        };
    }

}
