package br.com.cadastro.api.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class Produto {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@NotBlank
	private String descricaoProduto;
	


	@NotNull
	@Min(0)
	private Double precoUnitario;
	
	@NotNull
	@Min(0)
	private Integer quantidadeEstoque;
	
	
	@NotNull
	@Min(0)
	@Max(1)
	private double desconto;
	
	public Produto() {
		// TODO Auto-generated constructor stub
	}
	

	

}
