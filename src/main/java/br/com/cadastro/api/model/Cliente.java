package br.com.cadastro.api.model;


import javax.persistence.Entity;   
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class Cliente{
	
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	

	@NotBlank
	@Size(max = 60)
	private String nomeRazaoSocial;
	

	
	
	
	@NotBlank
	@Size(max = 20)
	private String cpfCnpj;
	
	@NotBlank
	@Email
	@Size(max = 255)
	private String email;
	
	@NotBlank
	@Size(max= 20)
	private String contato;
	
	@NotBlank
	@Size(max = 255)
	private String logradouro;
	
	@NotBlank
	@Size(max = 8)
	private String cep;
	
	@NotBlank
	@Size(max = 50)
	private String municipio;
	
	@NotBlank
	@Size(max = 2, min = 2)
	private String uf;
	
	@Size(max = 100)
	@NotNull
	private String numero;
	
	
	@NotBlank
	private String complemento;
		  
	
	public Cliente() {
		// TODO Auto-generated constructor stub
	}
}
