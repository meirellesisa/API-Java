package br.com.cadastro.api.controller;

import javax.validation.Valid ;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.cadastro.api.model.Produto;
import br.com.cadastro.domain.repository.ProdutoRepository;
import br.com.cadastro.domain.service.CadastrarProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CadastrarProdutoService cadastrarProdutoServer;
	
	
	@GetMapping
	public Iterable<Produto> listarTodos(){
		
	      return produtoRepository.findAll();
	
	}
	
	@GetMapping("/{id}")
	public  ResponseEntity<Produto> buscarPorId(@PathVariable Long id){
		
		return produtoRepository.findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
				
		}
		
		
	@GetMapping("/{qtdPagina}/{tamanhoPagina}")
	public Page<Produto> buscarPorPagina(@PathVariable  int qtdPagina, @PathVariable int tamanhoPagina) {
		
		Pageable page  = PageRequest.of(qtdPagina, tamanhoPagina);;
		
		return produtoRepository.findAll(page);
		
	}
		
	

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Produto adicionarProduto(@RequestBody @Valid Produto produto){
		
	return cadastrarProdutoServer.salvar(produto);
	}
	
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Produto> atualizar(@PathVariable  Long id,@RequestBody Produto produto){
		
		if(!produtoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		produto.setId(id);
		produto = cadastrarProdutoServer.salvar(produto);
		
		
		return ResponseEntity.ok(produto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Produto> deletar(@PathVariable Long id) {
		
		if(!produtoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		produtoRepository.deleteById(id);
		
		return ResponseEntity.noContent().build();
		
		
	}

}
