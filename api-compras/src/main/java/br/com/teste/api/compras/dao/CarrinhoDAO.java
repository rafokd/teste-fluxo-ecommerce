package br.com.teste.api.compras.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import br.com.teste.api.compras.model.Carrinho;
import br.com.teste.api.compras.model.Produto;
import br.com.teste.api.compras.model.ProdutoCarrinho;

public class CarrinhoDAO {

	private static Map<Long, Carrinho> banco = new HashMap<>();
	private static AtomicLong id = new AtomicLong(0);
	
	public Carrinho adiciona(Carrinho carrinho, Produto produto, Long quantidade){
		
		if(carrinho.getId() == null){
			long newId = id.incrementAndGet();
			carrinho.setId(newId);
		}
		
		carrinho.getProdutos().add(new ProdutoCarrinho(produto, quantidade));
		
		banco.put(carrinho.getId(), carrinho);
		
		return carrinho;
	}
}
