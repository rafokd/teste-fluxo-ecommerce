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

	private final String URI_BUSCA_PRODUTO = "/produtos/{id}";
	private final String URI_CADASTRA_PRODUTO = "/produtos";
	private final String URI_REMOVE_ESTOQUE = "/produtos/{id}/removerEstoque/{quantidade}";
	
	@Autowired
    private TestRestTemplate testRestTemplate;
	
	@Test
	public void testaBuscaProduto(){
		
		Long id = 1l;
	    Produto produto = buscaProduto(id);
	     
	    Assert.assertEquals(id, produto.getId());
	}
	
	@Test
	public void testaCadastroProduto(){
		
		Produto produto = new Produto(null, "Teste Produto", new BigDecimal("10"), 10l);
		produto = testRestTemplate.postForObject(URI_CADASTRA_PRODUTO, produto, Produto.class);
		
		Assert.assertNotEquals(null, produto.getId());
	}
	
	@Test
	public void testaRemoverEstoque(){
		
		Long id = 1l;						    
		long quantidade = 5l;
		
		Map<String, Long> paramsRemoverEstoque = new HashMap<>();
		paramsRemoverEstoque.put("id", id);
		paramsRemoverEstoque.put("quantidade", quantidade);
		
		Produto produto = buscaProduto(id);
		long estoqueAnterior = produto.getQuantidadeEstoque();
		
		testRestTemplate.put(URI_REMOVE_ESTOQUE, null, paramsRemoverEstoque);
		
		produto = buscaProduto(id);
		
		Assert.assertEquals(estoqueAnterior - quantidade, produto.getQuantidadeEstoque());
	}
	
	private Produto buscaProduto(Long id){
		
		Map<String, Long> params = new HashMap<>();
	    params.put("id", id);
	    
	    return testRestTemplate.getForObject(URI_BUSCA_PRODUTO, Produto.class, params);
	}

}
