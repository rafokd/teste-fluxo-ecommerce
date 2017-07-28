package br.com.teste.api.produtos;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.teste.api.produtos.model.Produto;
import br.com.teste.api.produtos.repository.ProdutoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ApiProdutosApplicationTests {

	private static final String URI_BUSCA_TODOS = "/produtos";
	private static final String URI_BUSCA_PRODUTO = "/produtos/{id}";
	private static final String URI_CADASTRA_PRODUTO = "/produtos";
	private static final String URI_REMOVE_ESTOQUE = "/produtos/{id}/removerEstoque/{quantidade}";
	
	@Autowired
    private TestRestTemplate testRestTemplate;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Test
	public void testaBuscaTodos(){
		Assert.assertEquals(HttpStatus.OK, testRestTemplate.getForEntity(URI_BUSCA_TODOS, Produto[].class).getStatusCode());
	}
	
	@Test
	public void testaBuscaProduto(){
		
		Produto produto = buscaQualquerProduto();
		
		if(produto != null){
			
			Map<String, Long> params = new HashMap<>();
		    params.put("id", produto.getId());

		    Assert.assertEquals(HttpStatus.OK, testRestTemplate.getForEntity(URI_BUSCA_PRODUTO, Produto.class, params).getStatusCode());
		}
	}
	
	@Test
	public void testaCadastroProduto(){
		
		Produto produto = new Produto(null, "Teste Produto", new BigDecimal("10"), 10l);
		produto = testRestTemplate.postForObject(URI_CADASTRA_PRODUTO, produto, Produto.class);
		
		Assert.assertNotEquals(null, produto.getId());
	}
	
	@Test
	public void testaRemoverEstoque(){
		
		Produto produto = buscaQualquerProduto();
		
		if(produto != null){
			
			long quantidade = 5l;
			long estoqueAnterior = produto.getQuantidadeEstoque();
			
			Map<String, Long> params = new HashMap<>();
			params.put("id", produto.getId());
			params.put("quantidade", quantidade);
			
			testRestTemplate.put(URI_REMOVE_ESTOQUE, null, params);
			
			produto = buscaProduto(produto.getId());
			
			Assert.assertEquals(estoqueAnterior - quantidade, produto.getQuantidadeEstoque());
		}
	}
	
	private Produto buscaProduto(Long id){
	    return produtoRepository.findOne(id);
	}
	
	private Produto buscaQualquerProduto(){
		
		List<Produto> produtos = produtoRepository.findAll();
		return produtos != null && !produtos.isEmpty() ? produtos.get(0) : null;
	}

}
