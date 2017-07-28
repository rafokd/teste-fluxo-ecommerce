package br.com.teste.api.produtos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.teste.api.produtos.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
