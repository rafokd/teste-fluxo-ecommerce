package br.com.teste.api.compras.model;

import javax.persistence.Embeddable;

@Embeddable
public class ProdutoCarrinho {

	private Long idProduto;
	private Long quantidade;
	
	public ProdutoCarrinho(){}
	
	public ProdutoCarrinho(Long idProduto, Long quantidade){
		this.idProduto = idProduto;
		this.quantidade = quantidade;
	}
	
	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}
}
