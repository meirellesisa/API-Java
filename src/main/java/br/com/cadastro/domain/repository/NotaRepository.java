package br.com.cadastro.domain.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.cadastro.api.model.Nota;

public interface NotaRepository extends JpaRepository<Nota, Long>, CrudRepository<Nota, Long>, PagingAndSortingRepository<Nota, Long>{
	


	
}
