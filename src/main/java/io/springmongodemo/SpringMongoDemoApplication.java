package io.springmongodemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringMongoDemoApplication {

	public static void main(String[] args) {
		System.out.println("run main line 10");
		SpringApplication.run(SpringMongoDemoApplication.class, args);
	}

}

