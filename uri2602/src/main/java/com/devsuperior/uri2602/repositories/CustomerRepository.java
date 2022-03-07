package com.devsuperior.uri2602.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devsuperior.uri2602.dto.CustomerMinDTO;
import com.devsuperior.uri2602.entities.Customer;
import com.devsuperior.uri2602.projections.CustomerMinProjection;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
	/**
	 * 			UTILIZANDO SQL RAÍZ
	 * Procurando clientes de acordo com o estado (UF)
	 * @param state = Estado (UF)
	 * @return customers
	 */
	@Query(nativeQuery = true, value = "SELECT name "
			+ "FROM customers "
			+ "WHERE UPPER(state) = UPPER(:state)")
	List<CustomerMinProjection> searchCustomerName(String state);
	
	/**
	 * 							UTILIZANDO JPQL
	 * Para procurar objetos com projeção em JPQL é preciso fazer um 'alias'
	 * com a ***classe*** (não mais a referência da tabela como em SQL) que se deseja buscar.
	 * Além disso, no momento do SELECT é preciso instanciar um novo objeto
	 * 	passando o caminho completo do DTO + o dado que se quer projetar
	 * 					como parâmetro de função.
	 * @param obj.attribute / more than one
	 * @param state = Estado (UF)
	 * @return customers as dto's
	 */
	@Query("SELECT new com.devsuperior.uri2602.dto.CustomerMinDTO(obj.name) "
			+ "FROM Customer obj "
			+ "WHERE UPPER(obj.state) = UPPER(:state)")
	List<CustomerMinDTO> searchCustomerNameJPQL(String state);
}
