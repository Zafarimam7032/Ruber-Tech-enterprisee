package com.catelog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class RubeberTechCatelogApplication {

	public static void main(String[] args) {
		SpringApplication.run(RubeberTechCatelogApplication.class, args);
		//new SpringApplicationBuilder().sources(RubeberTechCatelogApplication.class).profiles("dev").build().run(args);

	}

}
