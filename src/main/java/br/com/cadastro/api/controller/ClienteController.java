package br.com.cadastro.api.controller;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.com.cadastro.api.model.Cliente;
import br.com.cadastro.domain.repository.ClienteRepository;
import br.com.cadastro.domain.service.CadastrarClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private CadastrarClienteService cadastroClienteService;
	
	
	@GetMapping
	public Iterable<Cliente> listar(){
		
		return clienteRepository.findAll();
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long id){
		
		return clienteRepository.findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
		
	@GetMapping("/{qtdPagina}/{tamanhoPagina}")
	public Iterable<Cliente> buscarPorPagina(@PathVariable int qtdPagina, @PathVariable int tamanhoPagina){
		
		Pageable page = PageRequest.of(qtdPagina, tamanhoPagina);

		
		return clienteRepository.findAll(page);
	}
	
	
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Cliente cadastrar( @Valid @RequestBody Cliente cliente) {
		
		return cadastroClienteService.salvar(cliente);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @RequestBody Cliente cliente){
		
		if(!clienteRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		cliente.setId(id);
		cliente = cadastroClienteService.salvar(cliente);
		
		return ResponseEntity.ok(cliente);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Cliente> deletar(@PathVariable Long id){
		
		if(!clienteRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		clienteRepository.deleteById(id);
		
		
		return ResponseEntity.noContent().build();
		
	}
}
