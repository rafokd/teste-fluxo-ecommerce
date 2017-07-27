package br.com.teste.api.compras;

import java.util.HashMap;
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
import org.springframework.web.client.RestTemplate;

import br.com.teste.api.compras.model.Carrinho;
import br.com.teste.api.compras.model.Produto;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ApiComprasApplicationTests {

	@Autowired
    private TestRestTemplate testRestTemplate;
	
	private String URL_TESTA_API_PRODUTO = "http://localhost:8080/produtos/1";
	private String URI_ADICIONA_CARRINHO = "/carrinho/{idProduto}/{quantidade}";
	private String URI_CHECKOUT = "/carrinho/checkout";

	@Test
	public void testaAdicionaCarrinho(){
		
		if(isApiProdutoAtivo()){
			
			Long quantidade = 5l;
			Carrinho carrinho = adicionaCarrinho(1l, quantidade);
		    Assert.assertEquals(quantidade, carrinho.getProdutos().get(0).getQuantidade());
		}
	}
	
	@Test
	public void testaCheckout(){
		
		if(isApiProdutoAtivo()){
			
			Long quantidade = 5l;
			Carrinho carrinho = adicionaCarrinho(1l, quantidade);
			Assert.assertEquals(HttpStatus.OK, testRestTemplate.postForEntity(URI_CHECKOUT, carrinho, Object.class).getStatusCode());
		}
	}
	
	private Carrinho adicionaCarrinho(Long idProduto, Long quantidade){
		
		Map<String, Long> params = new HashMap<>();
	    params.put("idProduto", 1l);
	    params.put("quantidade", quantidade);
		
	    return testRestTemplate.postForObject(URI_ADICIONA_CARRINHO, null, Carrinho.class, params);
	}
	
	private boolean isApiProdutoAtivo(){
		try {
			return new RestTemplate().getForEntity(URL_TESTA_API_PRODUTO, Produto.class).getStatusCode().equals(HttpStatus.OK);
		} catch (Exception e) {
			return false;
		}
	}
}
