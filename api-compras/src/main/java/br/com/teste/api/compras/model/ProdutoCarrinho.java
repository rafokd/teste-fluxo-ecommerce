package br.com.teste.api.compras.model;

public class ProdutoCarrinho {

	private Produto produto;
	private Long quantidade;
	
	public ProdutoCarrinho(){}
	
	public ProdutoCarrinho(Produto produto, Long quantidade){
		this.produto = produto;
		this.quantidade = quantidade;
	}
	
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}
}
