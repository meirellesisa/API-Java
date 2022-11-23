package br.com.cadastro.api.controller;


import javax.validation.Valid;   

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

import br.com.cadastro.api.model.Nota;
import br.com.cadastro.domain.repository.NotaRepository;
import br.com.cadastro.domain.service.CadastrarNotaFiscalService;

@RestController
@RequestMapping("/notas")
public class NotaController {
	
	@Autowired
	private  CadastrarNotaFiscalService cadastrarNotaFiscalService;
	
	@Autowired
	private NotaRepository notaRepository;

	
	@GetMapping()
	public Iterable<Nota> listarNotas(){
		
		return notaRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Nota> listarNotaId(@PathVariable Long id){
		
		return notaRepository.findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	
	@GetMapping("/{qtdPagina}/{tamanhoPagina}")
	public Page<Nota> buscarPorPagina(@PathVariable  int qtdPagina, @PathVariable int tamanhoPagina) {
		
		Pageable page  = PageRequest.of(qtdPagina, tamanhoPagina);;
		
		return notaRepository.findAll(page);
		
	}
	
	
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Nota cadastrarNotaFiscal(@Valid @RequestBody Nota nota) throws Throwable{
		
		return notaRepository.save(cadastrarNotaFiscalService.cadastrarNota(nota));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Nota> atualizarNota(@PathVariable Long id,@RequestBody Nota nota) {
		
		if(!notaRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		nota.setId(id);
		nota = cadastrarNotaFiscalService.cadastrarNota(nota);
		
		return ResponseEntity.ok(nota);
	}
	
	@DeleteMapping("/{id}")
	
	public ResponseEntity<Nota> deletarNota(@PathVariable Long id){
		
		if(!notaRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		
		notaRepository.deleteById(id);
		
		return ResponseEntity.noContent().build();
		
	}

}
