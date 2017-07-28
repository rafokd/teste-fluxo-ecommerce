package br.com.teste.api.compras.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import br.com.teste.api.compras.model.Carrinho;
import br.com.teste.api.compras.model.Produto;
import br.com.teste.api.compras.model.ProdutoCarrinho;
import br.com.teste.api.compras.repository.CarrinhoRepository;

@RestController
public class CarrinhoRestController {

	private static final String API_PRODUTO_URL = "http://localhost:8080";
	
	@Autowired
	private CarrinhoRepository carrinhoRepository;
	
	@RequestMapping(value = "/carrinho/{idProduto}/{quantidade}", method = RequestMethod.POST)
	public ResponseEntity<?> adicionaProduto(
			@PathVariable("idProduto") Long idProduto, 
			@PathVariable("quantidade") Long quantidade,
			@RequestBody Carrinho carrinho){
		
		Map<String, Long> params = new HashMap<>();
	    params.put("id", idProduto);
		Produto produto = new RestTemplate().getForObject(API_PRODUTO_URL + "/produtos/{id}", Produto.class, params);
		
		if(produto == null){
			return new ResponseEntity<>("Produto nao encontrado", HttpStatus.FORBIDDEN);
		}
		
		if(quantidade <= produto.getQuantidadeEstoque()){
			carrinho.getProdutos().add(new ProdutoCarrinho(produto.getId(), quantidade));
			return new ResponseEntity<Carrinho>(carrinhoRepository.save(carrinho), HttpStatus.OK);
		}else{
			return new ResponseEntity<>("Estoque insuficiente", HttpStatus.FORBIDDEN);
		}
	}
	
	@RequestMapping(value = "/carrinho/checkout", method = RequestMethod.POST)
	public ResponseEntity<?> checkout(@RequestBody Carrinho carrinho){
		
		if(carrinho.getProdutos().isEmpty()){
			return new ResponseEntity<>("Carrinho vazio", HttpStatus.FORBIDDEN);
		}
		
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Long> params = new HashMap<>();
		
		carrinho.getProdutos().forEach(produto -> {
			params.put("id", produto.getIdProduto());
			params.put("quantidade", produto.getQuantidade());
			restTemplate.put(API_PRODUTO_URL + "/produtos/{id}/removerEstoque/{quantidade}", null, params);
		});
		
		carrinho.setRealizouCheckout(true);
		carrinhoRepository.save(carrinho);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
