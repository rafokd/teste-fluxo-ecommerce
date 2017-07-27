package br.com.teste.api.produtos.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import br.com.teste.api.produtos.model.Produto;

public class ProdutoDAO {
	
	private static Map<Long, Produto> banco = new HashMap<>();
	private static AtomicLong id = new AtomicLong(5);
	
	static{
		banco.put(1l, new Produto(1l, "Produto 1", new BigDecimal("100"), 30l));
		banco.put(2l, new Produto(2l, "Produto 2", new BigDecimal("200"), 20l));
		banco.put(3l, new Produto(3l, "Produto 3", new BigDecimal("300"), 50l));
		banco.put(4l, new Produto(4l, "Produto 4", new BigDecimal("400"), 10l));
		banco.put(5l, new Produto(5l, "Produto 5", new BigDecimal("500"), 5l));
	}

	public void adiciona(Produto produto){
		long newId = id.incrementAndGet();
		produto.setId(newId);
		banco.put(newId, produto);
	}
	
	public Produto buscaProduto(Long id){
		return banco.get(id);
	}
	
	public void removerEstoque(Long id, Long quantidade){
		banco.get(id).setQuantidadeEstoque(banco.get(id).getQuantidadeEstoque() - quantidade);
	}
}
