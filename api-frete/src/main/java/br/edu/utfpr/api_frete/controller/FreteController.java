package br.edu.utfpr.api_frete.controller;

import br.edu.utfpr.api_frete.dtos.FreteRequestDTO;
import br.edu.utfpr.api_frete.dtos.FreteResponseDTO;
import br.edu.utfpr.api_frete.dtos.ViaCepResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.Arrays;
import java.util.List;
import java.util.random.RandomGenerator;

@RestController
@RequestMapping("/fretes")
public class FreteController {

    // BIBLIOTECA PARA COMUNICAÇÃO SÍNCRONA
    private RestClient restClient = RestClient
            .create("https://viacep.com.br/ws/");

    // NOVO MÉTODO PARA CHAMAR API VIACEP
    private ViaCepResponseDTO getViaCEP(String cep) {
        return restClient.get()
                .uri(cep + "/json/")
                .retrieve()
                .body(ViaCepResponseDTO.class);
    }

    @PostMapping
    public ResponseEntity<FreteResponseDTO> calcularFrete(@RequestBody FreteRequestDTO freteRequestDTO){
        String cidade = "N/D";

        //String estado = this.getEstado(freteRequestDTO.cep());

        ViaCepResponseDTO viaCepResponseDTO = getViaCEP(freteRequestDTO.cep());
        if (viaCepResponseDTO.erro() != null && viaCepResponseDTO.erro()){
            return ResponseEntity.notFound().build();
        }
        String estado = viaCepResponseDTO.uf();
        cidade = viaCepResponseDTO.localidade();

        int numeroDiasEntrega = this.getNumeroDiasEntrega(estado);

        double valorFrete = this.getValorFrete(estado);

        double porcentagemDescontoFrete = this.getPorcentagemDescontoFrete(freteRequestDTO.orderAmount());

        valorFrete = valorFrete - (valorFrete * porcentagemDescontoFrete);

        FreteResponseDTO freteResponseDTO = new FreteResponseDTO(
                valorFrete, numeroDiasEntrega, cidade, estado
        );

        return ResponseEntity.ok(freteResponseDTO);
    }

    // --------------------------------------------------------------------------------------
    // Sorteia um dos Estados do Brasil ao buscar um CEP
    //  para fins de testes.
    // --------------------------------------------------------------------------------------
    private String getEstado(String cep){
        List<String> estadosBR = Arrays.asList(
                "AC","AL","AP","AM","BA",
                "CE","DF","ES","GO","MA",
                "MT","MS","MG","PA","PB",
                "PR","PE","PI","RJ","RN",
                "RS","RO","RR","SC","SP",
                "SE","TO");

        int estadoSorteado = RandomGenerator.getDefault().nextInt(0,26);
        return estadosBR.get(estadoSorteado);
    }

    // --------------------------------------------------------------------------------------
    // Retorna número de dias para entrega, conforme Estado
    // --------------------------------------------------------------------------------------
    private int getNumeroDiasEntrega(String estado){
        switch (estado) {
            case "PR": case "SC":case "RS": return 5;
            case "SP": case "RJ":case "MG": return 10;
            default: return 15;
        }
    }

    // --------------------------------------------------------------------------------------
    // Retorna o valor do frete, conforme Estado
    // --------------------------------------------------------------------------------------
    private int getValorFrete(String estado){
        switch (estado) {
            case "PR": case "SC":case "RS": return 50;
            case "SP": case "RJ":case "MG": return 100;
            default: return 200;
        }
    }

    // --------------------------------------------------------------------------------------
    // Retorna o portentagem de desconto do frete, conforme valor do pedido
    // --------------------------------------------------------------------------------------
    private double getPorcentagemDescontoFrete(Double valorPedido){
        double desconto = 0;
        if (valorPedido > 500){
            desconto = 100;
        } else if (valorPedido > 300){
            desconto = 50;
        }
        return desconto / 100;
    }

}
