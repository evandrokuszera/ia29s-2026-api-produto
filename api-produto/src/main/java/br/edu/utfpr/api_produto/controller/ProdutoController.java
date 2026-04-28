package br.edu.utfpr.api_produto.controller;

import br.edu.utfpr.api_produto.dto.ProdutoDTO;
import br.edu.utfpr.api_produto.dto.VendaDTO;
import br.edu.utfpr.api_produto.model.Produto;
import br.edu.utfpr.api_produto.repositories.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private List<Produto> produtos;
    private ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository; // injeção de dependência.

        produtos = new ArrayList<>();
        produtos.add(new Produto(1L, "Iphone15", 10, 4000.0, "Telefones"));
        produtos.add(new Produto(2L, "S22", 10, 5000.0, "Telefones"));
        produtos.add(new Produto(3L, "Notebook", 10, 8000.0, "Computadores"));
        produtos.add(new Produto(4L, "Cadeira Gamer", 10, 1000.0, "Móveis"));
    }

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> getAll(){
        List<ProdutoDTO> produtosDTO = new ArrayList<>();
        // select * from produto;
        for (Produto produto : this.produtoRepository.findAll()) {
            produtosDTO.add(toProdutoDTO(produto));
        }
        return ResponseEntity.ok(produtosDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> getOne(@PathVariable("id") Long id){
        // select * from produto where id = ?
        Produto produtoBD = this.produtoRepository.findById(id).orElse(null);
        if (produtoBD == null){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(toProdutoDTO(produtoBD));
        }
    }

    @PostMapping
    public ResponseEntity<Void> add(@Valid @RequestBody ProdutoDTO produtoDTO){
        this.produtoRepository.save(  toProduto(produtoDTO)  );
        return ResponseEntity.ok().build();
    }

    // Tarefa: baixarEstoque

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        for (Produto p : this.produtos){
            if (p.getId() == id){
                this.produtos.remove(p);
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> update(@PathVariable Long id, @Valid @RequestBody ProdutoDTO produtoDTO){
        for (Produto p : this.produtos){
            if (p.getId() == id){
                p.setDescription(produtoDTO.description());
                p.setQuantity(produtoDTO.quantity());
                p.setPrice(produtoDTO.price());
                p.setCategory(produtoDTO.category());
                return ResponseEntity.ok(toProdutoDTO(p));
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/venda")
    public ResponseEntity<Void> venda(@PathVariable Long id, @Valid @RequestBody VendaDTO vendaDTO){
        for (Produto p : this.produtos){
            if (p.getId() == id){
                if (p.getQuantity() >= vendaDTO.quantity()){
                    p.setQuantity(p.getQuantity() - vendaDTO.quantity());
                    return ResponseEntity.noContent().build();
                } else {
                    return ResponseEntity.badRequest().build();
                }
            }
        }
        return ResponseEntity.notFound().build();
    }

    private ProdutoDTO toProdutoDTO(Produto produto){
        ProdutoDTO produtoDTO = new ProdutoDTO(
                produto.getId(),
                produto.getDescription(),
                produto.getQuantity(),
                produto.getPrice(),
                produto.getCategory()
        );
        return produtoDTO;
    }

    private Produto toProduto(ProdutoDTO produtoDTO){
        Produto produto = new Produto(
                produtoDTO.id(),
                produtoDTO.description(),
                produtoDTO.quantity(),
                produtoDTO.price(),
                produtoDTO.category()
        );
        return produto;
    }

}
