package br.com.cadastro.domain.service;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cadastro.api.model.Cliente;
import br.com.cadastro.domain.exception.NegocioException;
import br.com.cadastro.domain.repository.ClienteRepository;

@Service
public class CadastrarClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	
	public Cliente buscar(Long id) {
		
		return clienteRepository.findById(id)
				.orElseThrow(()-> new NegocioException("Cliente não encontrado"));
		
		
	}
	
	@Transactional
	public Cliente salvar(Cliente cliente) {
		
		boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
				.stream()
				.anyMatch((clienteExistente)-> !clienteExistente.equals(cliente));
		
		
		if(emailEmUso == true) {
			throw new NegocioException("já existe um cliente cadastrado com esse e-mail.");
		}
		
		boolean cpfCnpjEmUso = clienteRepository.findByCpfCnpj(cliente.getCpfCnpj())
				.stream()
				.anyMatch((clienteExistente)-> !clienteExistente.equals(cliente));
		
		if(cpfCnpjEmUso == true) {
			throw new NegocioException("Cpf ou Cnpj digitado já está em uso");
			
		}
		
		return clienteRepository.save(cliente);
		
	}
	

}
