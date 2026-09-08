package br.edu.unifacisa.impacta.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;


class CalculoPontuacaoTest {
    @Test
    void deveCalcularPontuacaoDoPlantioPolimorficamente() {
        AcaoSocioambiental acao = new PlantioMudas(1, "Plantio", "Plantio de girassol",LocalDateTime.of(2026,9,2,10,0
        ), 10, 5 ); //criando um LocalDateTime com as informações q estou passando

        assertEquals(15, acao.calcularPontuacao());
    }

    @Test
    void deveCalcularPontuacaoDoMutiraoPolimorficamente() {
    }

    @Test
    void deveCalcularPontuacaoDaOficinaPolimorficamente() {
    }
}
