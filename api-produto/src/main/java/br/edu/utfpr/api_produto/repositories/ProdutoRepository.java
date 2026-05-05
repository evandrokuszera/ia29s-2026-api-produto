package br.edu.utfpr.api_produto.repositories;

import br.edu.utfpr.api_produto.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    // Buscar produtos por category
    public List<Produto> findByCategory(String category);
    // Buscar produtos com price entre min e max
    public List<Produto> findByPriceBetween(double min, double max);
    // Buscar produtos ordenados por descrição
    public List<Produto> findByOrderByDescriptionAsc();
    // Buscar produtos por category
    public boolean existsByDescription(String description);
}
