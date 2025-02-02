package com.shell.tasktracker;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@SpringBootApplication
public class TasktrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TasktrackerApplication.class, args);
    }

	@PostConstruct
	public void createJson() throws IOException {
		ClassLoader classLoader = getClass().getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream("tasks.json");

		if (Objects.isNull(inputStream)) {
			Path filePath = Paths.get("tasks.json");
			if (!Files.exists(filePath)) {
				Files.createFile(filePath);
				System.out.println("File created.." + filePath);
			}
		}
	}

}
