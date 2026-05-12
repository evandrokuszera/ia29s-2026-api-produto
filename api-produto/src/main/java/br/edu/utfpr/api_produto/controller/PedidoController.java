package br.edu.utfpr.api_produto.controller;

import br.edu.utfpr.api_produto.dto.AddItemPedidoDTO;
import br.edu.utfpr.api_produto.model.ItemPedido;
import br.edu.utfpr.api_produto.model.Pedido;
import br.edu.utfpr.api_produto.model.Produto;
import br.edu.utfpr.api_produto.repositories.ItemPedidoRepository;
import br.edu.utfpr.api_produto.repositories.PedidoRepository;
import br.edu.utfpr.api_produto.repositories.ProdutoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private ProdutoRepository produtoRepository;
    private PedidoRepository pedidoRepository;
    private ItemPedidoRepository itemPedidoRepository;

    public PedidoController(ProdutoRepository produtoRepository, PedidoRepository pedidoRepository, ItemPedidoRepository itemPedidoRepository) {
        this.produtoRepository = produtoRepository;
        this.pedidoRepository = pedidoRepository;
        this.itemPedidoRepository = itemPedidoRepository;
    }

    @PostMapping
    public ResponseEntity<Pedido> createPedido(){
        Pedido pedido = new Pedido();
        pedido.setDate( LocalDateTime.now() );
        pedido.setAmount(0.0);

        Pedido pedidoSalvo = this.pedidoRepository.save(pedido);
        return ResponseEntity.ok( pedidoSalvo );
    }

    @PostMapping("/{id}/items")
    public ResponseEntity<ItemPedido> addItem(@PathVariable Long id, @RequestBody AddItemPedidoDTO addItemPedidoDTO){
        Pedido pedido = this.pedidoRepository.findById(id).orElse(null);
        if (pedido == null) return ResponseEntity.notFound().build();

        Produto produto = this.produtoRepository.findById(  addItemPedidoDTO.produtoID()  ).orElse(null);
        if (produto == null) return ResponseEntity.notFound().build();

        ItemPedido item = new ItemPedido();
        item.setProduto( produto );
        item.setPedido( pedido );
        item.setQuantity( addItemPedidoDTO.quantity() );
        item.setUnitPrice( produto.getPrice() );

        ItemPedido itemSalvo = this.itemPedidoRepository.save( item );

        // atualizar amount do Pedido
        // dar baixa no estoque, em Produto

        return ResponseEntity.ok( itemSalvo );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getPedido(@PathVariable Long id){

        Pedido pedido =  this.pedidoRepository.findById(id).orElse(null);
        if (pedido == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok( pedido );
    }

}
