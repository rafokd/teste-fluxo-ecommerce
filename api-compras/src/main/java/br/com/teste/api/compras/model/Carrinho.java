package br.com.teste.api.compras.model;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {

	private Long id;
	private List<ProdutoCarrinho> produtos;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<ProdutoCarrinho> getProdutos() {
		return produtos == null ? produtos = new ArrayList<>() : produtos;
	}
	public void setProdutos(List<ProdutoCarrinho> produtos) {
		this.produtos = produtos;
	}
}
