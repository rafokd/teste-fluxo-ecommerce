package br.com.teste.api.compras.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Carrinho {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ElementCollection
	private List<ProdutoCarrinho> produtos;
	
	private boolean realizouCheckout;
	
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
	public boolean isRealizouCheckout() {
		return realizouCheckout;
	}
	public void setRealizouCheckout(boolean realizouCheckout) {
		this.realizouCheckout = realizouCheckout;
	}
}
