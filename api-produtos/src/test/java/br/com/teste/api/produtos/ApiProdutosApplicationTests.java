package br.com.teste.api.produtos;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.teste.api.produtos.model.Produto;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ApiProdutosApplicationTests {

	@Autowired
    private TestRestTemplate testRestTemplate;
	
	@Test
	public void testaBuscaProduto(){
		
		String uri = "/produtos/{id}";
		long id = 1l;
		
		Map<String, Long> params = new HashMap<>();
	    params.put("id", id);
	    Produto produto = testRestTemplate.getForObject(uri, Produto.class, params);
	     
	    Assert.assertEquals(id, produto.getId());
	}
	
	@Test
	public void testaCadastroProduto(){
		
		String uri = "/produtos";
		
		Produto produto = new Produto(0, "Teste Produto", new BigDecimal("10"), 10l);
		produto = testRestTemplate.postForObject(uri, produto, Produto.class);
		
		Assert.assertNotEquals(0, produto.getId());
	}
	
	@Test
	public void testaRemoverEstoque(){
		
		String uriBuscaProduto = "/produtos/{id}";
		long id = 1l;
		Map<String, Long> paramsBuscaProduto = new HashMap<>();
		paramsBuscaProduto.put("id", id);
		
		String uriRemoverEstoque = "/produtos/removerEstoque/{quantidade}";
		long quantidade = 5l;
		Map<String, Long> paramsRemoverEstoque = new HashMap<>();
		paramsRemoverEstoque.put("quantidade", quantidade);
		
		Produto produto = testRestTemplate.getForObject(uriBuscaProduto, Produto.class, paramsBuscaProduto);
		long estoqueAnterior = produto.getQuantidadeEstoque();
		
		testRestTemplate.put(uriRemoverEstoque, produto, paramsRemoverEstoque);
		
		produto = testRestTemplate.getForObject(uriBuscaProduto, Produto.class, paramsBuscaProduto);
		
		Assert.assertEquals(estoqueAnterior - quantidade, produto.getQuantidadeEstoque());
	}

}
