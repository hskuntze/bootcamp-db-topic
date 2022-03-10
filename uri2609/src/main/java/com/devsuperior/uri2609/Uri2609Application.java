package com.devsuperior.uri2609;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devsuperior.uri2609.dto.CategorySumDTO;
import com.devsuperior.uri2609.projections.CategorySumProjection;
import com.devsuperior.uri2609.repositories.CategoryRepository;

@SpringBootApplication
public class Uri2609Application implements CommandLineRunner {

	@Autowired
	private CategoryRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(Uri2609Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<CategorySumProjection> list = repository.search1();
		List<CategorySumDTO> result = list.stream().map(x -> new CategorySumDTO(x)).collect(Collectors.toList());
		System.out.println("SQL Raíz\n\n");
		for(CategorySumDTO obj : result) {
			System.out.println(obj.getName() + ", " + obj.getSum());
		}
		System.out.println("------------------");
		List<CategorySumDTO> result2 = repository.search2();
		System.out.println("JPQL\n\n");
		for(CategorySumDTO obj : result2) {
			System.out.println(obj.getName() + ", " + obj.getSum());
		}
	}
}
