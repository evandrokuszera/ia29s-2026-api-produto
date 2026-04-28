package br.edu.utfpr.api_produto.repositories;

import br.edu.utfpr.api_produto.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
