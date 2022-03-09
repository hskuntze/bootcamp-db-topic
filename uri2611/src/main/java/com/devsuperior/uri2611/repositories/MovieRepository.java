package com.devsuperior.uri2611.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devsuperior.uri2611.dto.MovieDTO;
import com.devsuperior.uri2611.entities.Movie;
import com.devsuperior.uri2611.projections.MovieProjection;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>{
	//SQL Raíz
	@Query(nativeQuery = true, value = 
			"SELECT movies.id, movies.name "
			+ "FROM movies "
			+ "INNER JOIN genres ON movies.id_genres = genres.id "
			+ "WHERE genres.description = :genreName")
	List<MovieProjection> search1(String genreName);
	
	//JPQL
	@Query("SELECT new com.devsuperior.uri2611.dto.MovieDTO(obj.id, obj.name) "
			+ "FROM Movie obj "
			+ "WHERE obj.genre.description = :genreName")
	List<MovieDTO> search2(String genreName);
}
