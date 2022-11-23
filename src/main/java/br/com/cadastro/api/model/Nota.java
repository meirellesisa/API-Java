package br.com.cadastro.api.model;

import java.time.LocalDate;   

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Nota {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonProperty(access = Access.READ_ONLY)
	private LocalDate emissao;
	
	private String naturezaOperacao;
	
	private int qtdProduto;
	
	@Valid
	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
	private Cliente cliente;
	
	@Valid
	@NotNull
	@OneToOne( cascade = CascadeType.ALL)
	private Produto produto;
	
	
	@JsonProperty(access = Access.READ_ONLY)
	private Double valorNota;

	
	public Nota() {
		emissao = LocalDate.now();
	    
	}	
	
	
}
