package br.com.cadastro.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cadastro.api.model.Produto;
import br.com.cadastro.domain.exception.NegocioException;
import br.com.cadastro.domain.repository.ProdutoRepository;

@Service
public class CadastrarProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	
	public Produto buscar(Long id) {
		
		return produtoRepository.findById(id)
				.orElseThrow(()-> new NegocioException("Produto não encontrado"));
	}
	
	@Transactional
	public Produto salvar(Produto produto) {
		
		boolean produtoExistente = produtoRepository.findByDescricaoProduto(produto.getDescricaoProduto())
				.stream()
				.anyMatch((descricao) -> !descricao.equals(produto));
		
		if(produtoExistente == true) {
			throw new NegocioException("esse produto já foi cadastrado");
		}
			
			return produtoRepository.save(produto);

      }
	
}
