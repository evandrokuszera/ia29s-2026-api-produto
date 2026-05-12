package br.edu.utfpr.api_produto.repositories;

import br.edu.utfpr.api_produto.model.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> { }
