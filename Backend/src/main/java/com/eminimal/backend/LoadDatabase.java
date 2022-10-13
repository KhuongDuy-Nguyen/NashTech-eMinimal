package com.eminimal.backend;

import com.eminimal.backend.models.Category;
import com.eminimal.backend.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(CategoryRepository repository){
        return args -> {
            repository.deleteAll();
          log.info("Inserting" + repository.save(new Category("Sofa", "Tong hop cac loai sofa")));
          log.info("Inserting" + repository.save(new Category("Lighting", "Tong hop cac loai den trang tri")));

        };
    }

}
