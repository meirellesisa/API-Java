package br.com.cadastro.domain.repository;

import java.util.List; 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.cadastro.api.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>, CrudRepository<Cliente, Long>, PagingAndSortingRepository<Cliente, Long>{

	List<Cliente> findByEmail(String email);
	
	List<Cliente> findByCpfCnpj(String cpfCnpj);
	

}
