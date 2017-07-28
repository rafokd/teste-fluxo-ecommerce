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
	
	private static final String URL_TESTA_API_PRODUTO = "http://localhost:8080/produtos/1";
	private static final String URL_BUSCA_TODOS_PRODUTO = "http://localhost:8080/produtos";
	private static final String URI_ADICIONA_CARRINHO = "/carrinho/{idProduto}/{quantidade}";
	private static final String URI_CHECKOUT = "/carrinho/checkout";

	@Test
	public void testaAdicionaCarrinho(){
		
		if(isApiProdutoAtivo()){
			
			Long quantidade = 5l;
			Produto produto = buscaQualquerProdutoComEstoque(quantidade);
			
			if(produto != null){
				
				Carrinho carrinho = adicionaCarrinho(produto.getId(), quantidade);
			    Assert.assertEquals(quantidade, carrinho.getProdutos().get(0).getQuantidade());
			}
		}
	}
	
	@Test
	public void testaCheckout(){
		
		if(isApiProdutoAtivo()){
			
			Long quantidade = 5l;
			Produto produto = buscaQualquerProdutoComEstoque(quantidade);
			
			if(produto != null){
				
				Carrinho carrinho = adicionaCarrinho(produto.getId(), quantidade);
				Assert.assertEquals(HttpStatus.OK, testRestTemplate.postForEntity(URI_CHECKOUT, carrinho, Object.class).getStatusCode());
			}
		}
	}
	
	private Carrinho adicionaCarrinho(Long idProduto, Long quantidade){
		
		Map<String, Long> params = new HashMap<>();
	    params.put("idProduto", idProduto);
	    params.put("quantidade", quantidade);
		
	    return testRestTemplate.postForObject(URI_ADICIONA_CARRINHO, new Carrinho(), Carrinho.class, params);
	}
	
	private Produto buscaQualquerProdutoComEstoque(long quantidade){
		
		Produto[] produtos = new RestTemplate().getForObject(URL_BUSCA_TODOS_PRODUTO, Produto[].class);
		
		for(Produto produto : produtos){
			if(produto.getQuantidadeEstoque() >= quantidade){
				return produto;
			}
		}
		return null;
	}
	
	private boolean isApiProdutoAtivo(){
		try {
			return new RestTemplate().getForEntity(URL_TESTA_API_PRODUTO, Produto.class).getStatusCode().equals(HttpStatus.OK);
		} catch (Exception e) {
			return false;
		}
	}
}
