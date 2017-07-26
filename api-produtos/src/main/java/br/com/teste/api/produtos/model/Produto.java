package br.com.teste.api.produtos.model;

import java.math.BigDecimal;

public class Produto {

	private long id;
	private String nome;
	private BigDecimal preco;
	private long quantidadeEstoque;
	
	public Produto(){}
	
	public Produto(long id, String nome, BigDecimal preco, long quantidadeEstoque){
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.quantidadeEstoque = quantidadeEstoque;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public long getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void setQuantidadeEstoque(long quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}
}
