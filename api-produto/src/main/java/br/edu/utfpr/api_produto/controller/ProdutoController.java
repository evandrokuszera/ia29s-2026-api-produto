package br.edu.utfpr.api_produto.controller;

import br.edu.utfpr.api_produto.model.Produto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private List<Produto> produtos;

    public ProdutoController(){
        produtos = new ArrayList<>();
        produtos.add(new Produto(1L, "Iphone15", 4000.0, "Telefones"));
        produtos.add(new Produto(2L, "S22", 5000.0, "Telefones"));
        produtos.add(new Produto(3L, "Notebook", 8000.0, "Computadores"));
        produtos.add(new Produto(4L, "Cadeira Gamer", 1000.0, "Móveis"));
    }

    @GetMapping
    public List<Produto> getAll(){
        return this.produtos;
    }

    @GetMapping("/{id}")
    public Produto getOne(@PathVariable("id") Long id){
        for (Produto p : this.produtos){
            if (p.getId() == id){
                return p;
            }
        }
        return null;
    }

    @PostMapping
    public String add(@RequestBody Produto produto){
        if (produto.getDescription().isBlank() || produto.getPrice() < 0){
            return "Descrição ou preço inválidos";
        }
        this.produtos.add(produto);
        return "Produto criado com sucesso";
    }




    // get

    // post

    // delete

}
