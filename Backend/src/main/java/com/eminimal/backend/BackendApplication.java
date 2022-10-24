package com.eminimal.backend;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import com.google.firebase.FirebaseOptions;

@SpringBootApplication
public class BackendApplication {

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}


	public static FileInputStream getPath(String fileName) throws FileNotFoundException {
		ClassLoader classLoader = BackendApplication.class.getClassLoader();
		File file = new File(Objects.requireNonNull(classLoader.getResource(fileName)).getFile());

		return new FileInputStream(file.getAbsolutePath());

	}

	public static void main(String[] args) throws IOException {

//		Firebase
		FileInputStream serviceAccount = getPath("serviceAccountKey.json");

		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.build();

		if(FirebaseApp.getApps().isEmpty()) {
			FirebaseApp.initializeApp(options);
		}

		SpringApplication.run(BackendApplication.class, args);

	}
}
