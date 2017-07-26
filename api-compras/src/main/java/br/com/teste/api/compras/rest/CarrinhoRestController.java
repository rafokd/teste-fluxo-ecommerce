package br.com.teste.api.compras.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import br.com.teste.api.compras.dao.CarrinhoDAO;
import br.com.teste.api.compras.model.Carrinho;
import br.com.teste.api.compras.model.Produto;

@RestController
public class CarrinhoRestController {

	private String produtoApiUrl = "http://localhost:8080";
	
	@RequestMapping(value = "/carrinho", method = RequestMethod.GET)
	public ResponseEntity<?> teste(){
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/carrinho/{idProduto}/{quantidade}", method = RequestMethod.POST)
	public ResponseEntity<?> adicionaProduto(
			@PathVariable("idProduto") Long idProduto, 
			@PathVariable("quantidade") Long quantidade,
			@RequestBody Carrinho carrinho){
		
		Map<String, Long> params = new HashMap<>();
	    params.put("id", idProduto);
		Produto produto = new RestTemplate().getForObject(produtoApiUrl + "/produtos/{id}", Produto.class, params);
		
		if(produto == null){
			return new ResponseEntity<>("Produto nao encontrado", HttpStatus.FORBIDDEN);
		}
		
		if(quantidade <= produto.getQuantidadeEstoque()){
			return new ResponseEntity<Carrinho>(new CarrinhoDAO().adiciona(carrinho, produto, quantidade), HttpStatus.OK);
		}else{
			return new ResponseEntity<>("Estoque insuficiente", HttpStatus.FORBIDDEN);
		}
	}
}
