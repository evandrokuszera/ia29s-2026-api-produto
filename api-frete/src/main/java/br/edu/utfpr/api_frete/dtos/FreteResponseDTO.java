package br.edu.utfpr.api_frete.dtos;

public record FreteResponseDTO(
        Double shippingPrice,
        Integer deliveryDays,
        String city,
        String state) {
}
