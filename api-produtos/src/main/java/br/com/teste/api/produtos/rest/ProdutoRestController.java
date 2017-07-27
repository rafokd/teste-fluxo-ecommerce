package br.com.teste.api.produtos.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.teste.api.produtos.dao.ProdutoDAO;
import br.com.teste.api.produtos.model.Produto;

@RestController
public class ProdutoRestController {
	
	@RequestMapping(value = "/produtos/{id}", method = RequestMethod.GET)
	public ResponseEntity<Produto> buscaProduto(@PathVariable("id") Long id){
		return new ResponseEntity<Produto>(new ProdutoDAO().buscaProduto(id), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/produtos", method = RequestMethod.POST)
	public ResponseEntity<Produto> cadastraProduto(@RequestBody Produto produto){
		new ProdutoDAO().adiciona(produto);
		return new ResponseEntity<Produto>(produto, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/produtos/{id}/removerEstoque/{quantidade}", method = RequestMethod.PUT)
	public ResponseEntity<String> removerEstoque(@PathVariable("id") Long id, @PathVariable("quantidade") Long quantidade){
		new ProdutoDAO().removerEstoque(id, quantidade);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
}
