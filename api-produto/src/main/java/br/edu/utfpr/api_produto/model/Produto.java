package br.edu.utfpr.api_produto.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produto {
    private Long id;
    private String description;
    private Integer quantity;
    private Double price;
    private String category;
}
