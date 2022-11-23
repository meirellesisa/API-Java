package br.com.cadastro.domain.repository;

import java.util.List; 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.cadastro.api.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>, CrudRepository<Produto, Long>, PagingAndSortingRepository<Produto, Long>{

	List<Produto> findByDescricaoProduto(String descricaoProduto);
}
