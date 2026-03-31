package br.edu.utfpr.api_produto.controller;

import br.edu.utfpr.api_produto.dto.ProdutoDTO;
import br.edu.utfpr.api_produto.model.Produto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private List<Produto> produtos;

    public ProdutoController(){
        produtos = new ArrayList<>();
        produtos.add(new Produto(1L, "Iphone15", 10, 4000.0, "Telefones"));
        produtos.add(new Produto(2L, "S22", 10, 5000.0, "Telefones"));
        produtos.add(new Produto(3L, "Notebook", 10, 8000.0, "Computadores"));
        produtos.add(new Produto(4L, "Cadeira Gamer", 10, 1000.0, "Móveis"));
    }

    @GetMapping
    public List<ProdutoDTO> getAll(){
        List<ProdutoDTO> produtosDTO = new ArrayList<>();

        for (Produto produto : this.produtos) {
            produtosDTO.add(toProdutoDTO(produto));
        }

        return produtosDTO;
    }

    @GetMapping("/{id}")
    public ProdutoDTO getOne(@PathVariable("id") Long id){
        for (Produto p : this.produtos){
            if (p.getId() == id){
                return toProdutoDTO(p);
            }
        }
        return null;
    }

    @PostMapping
    public String add(@Valid @RequestBody ProdutoDTO produtoDTO){
        this.produtos.add(toProduto(produtoDTO));
        return "Produto criado com sucesso";
    }

    // Tarefa: baixarEstoque

    // delete update

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
