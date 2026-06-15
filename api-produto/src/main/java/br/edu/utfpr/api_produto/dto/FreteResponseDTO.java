package br.edu.utfpr.api_produto.dto;

public record FreteResponseDTO (Double shippingPrice, Integer deliveryDays, String city, String state) {
}
