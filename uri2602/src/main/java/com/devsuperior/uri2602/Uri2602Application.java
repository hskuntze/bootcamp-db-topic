package com.devsuperior.uri2602;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devsuperior.uri2602.dto.CustomerMinDTO;
import com.devsuperior.uri2602.projections.CustomerMinProjection;
import com.devsuperior.uri2602.repositories.CustomerRepository;

@SpringBootApplication
public class Uri2602Application implements CommandLineRunner{
	
	@Autowired
	private CustomerRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(Uri2602Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<CustomerMinProjection> list = repository.searchCustomerName("RS");
		List<CustomerMinDTO> result = list.stream().map(x -> new CustomerMinDTO(x)).collect(Collectors.toList());
		System.out.println("RESULTADO DO SQL RAÍZ:");
		for(CustomerMinDTO obj : result) {
			System.out.println(obj.getName());
		}
		System.out.println("\n----------------------\n");
		
		List<CustomerMinDTO> result2 = repository.searchCustomerNameJPQL("RS");
		System.out.println("RESULTADO DO JPQL:");
		for(CustomerMinDTO obj : result2) {
			System.out.println(obj.getName());
		}
	}
}
