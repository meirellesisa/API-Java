package br.com.cadastro.domain.service;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cadastro.api.model.Cliente;
import br.com.cadastro.api.model.Nota;
import br.com.cadastro.api.model.Produto;
import br.com.cadastro.domain.repository.NotaRepository;

@Service
public class CadastrarNotaFiscalService {
	
	@Autowired
	private CadastrarClienteService cadastrarClienteService;
	
	@Autowired
	private CadastrarProdutoService cadastrarProdutoService;
	
	
	@Autowired
	private NotaRepository notaRepository;
	
	@Transactional
	public Nota cadastrarNota(Nota nota){
		
		Cliente cliente = cadastrarClienteService.buscar(nota.getCliente().getId());
		Produto produto = cadastrarProdutoService.buscar(nota.getProduto().getId());
			
		nota.setCliente(cliente);
		nota.setEmissao(LocalDate.now());
		nota.setProduto(produto);
		
		double desconto = (produto.getPrecoUnitario() * produto.getDesconto());
		double totalProd = (produto.getPrecoUnitario() - desconto);
		
		double totalNota = (totalProd * nota.getQtdProduto());
		
		nota.setValorNota(totalNota);
		
		return notaRepository.save(nota);
	}
	

	
	
	
	

}
