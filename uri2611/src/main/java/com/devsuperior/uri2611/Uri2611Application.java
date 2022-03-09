package com.devsuperior.uri2611;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devsuperior.uri2611.dto.MovieDTO;
import com.devsuperior.uri2611.projections.MovieProjection;
import com.devsuperior.uri2611.repositories.MovieRepository;

@SpringBootApplication
public class Uri2611Application implements CommandLineRunner{
	
	@Autowired
	private MovieRepository movieRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(Uri2611Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<MovieProjection> list = movieRepository.search1("Action");
		List<MovieDTO> result = list.stream().map(x -> new MovieDTO(x)).collect(Collectors.toList());
		System.out.println("\n\nSQL RAÍZ:");
		for(MovieDTO obj : result) {
			System.out.println(obj.getId()+", "+obj.getName());
		}
		System.out.println("------------------------------");
		
		List<MovieDTO> result2 = movieRepository.search2("Action");
		System.out.println("\n\nJPQL:");
		for(MovieDTO obj : result2) {
			System.out.println(obj.getId()+", "+obj.getName());
		}
	}
}
