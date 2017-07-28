package br.com.teste.api.produtos.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.teste.api.produtos.model.Produto;
import br.com.teste.api.produtos.repository.ProdutoRepository;

@RestController
public class ProdutoRestController {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@RequestMapping(value = "/produtos", method = RequestMethod.GET)
	public ResponseEntity<Produto[]> buscaTodos(){
		List<Produto> produtos = produtoRepository.findAll();
		return new ResponseEntity<Produto[]>(produtos.toArray(new Produto[produtos.size()]), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/produtos/{id}", method = RequestMethod.GET)
	public ResponseEntity<Produto> buscaProduto(@PathVariable("id") Long id){
		return new ResponseEntity<Produto>(produtoRepository.findOne(id), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/produtos", method = RequestMethod.POST)
	public ResponseEntity<Produto> cadastraProduto(@RequestBody Produto produto){
		return new ResponseEntity<Produto>(produtoRepository.save(produto), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/produtos/{id}/removerEstoque/{quantidade}", method = RequestMethod.PUT)
	public ResponseEntity<String> removerEstoque(@PathVariable("id") Long id, @PathVariable("quantidade") Long quantidade){
		
		Produto produto = produtoRepository.findOne(id);
		produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - quantidade);
		produtoRepository.save(produto);
		
		return new ResponseEntity<String>(HttpStatus.OK);
	}
}
