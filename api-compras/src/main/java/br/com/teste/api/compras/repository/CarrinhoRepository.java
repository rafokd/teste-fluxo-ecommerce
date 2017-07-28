package br.com.teste.api.compras.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.teste.api.compras.model.Carrinho;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Long>{

}
