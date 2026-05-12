package br.edu.utfpr.api_produto.repositories;

import br.edu.utfpr.api_produto.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido,Long> { }
