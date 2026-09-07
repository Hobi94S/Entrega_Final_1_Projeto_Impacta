package br.edu.unifacisa.impacta.model;

import java.time.LocalDateTime;

public class PlantioMudas extends AcaoSocioambiental {
    private int qtdMudas;

    public PlantioMudas(int id, String titulo, String descricao, LocalDateTime data,
                        int capacidadeMax, int qtdMudas) {
        super(id, titulo, descricao, data, capacidadeMax);
        this.qtdMudas = qtdMudas;
    }

    @Override
    public int calcularPontuacao() {

        return 5 + (2 * qtdMudas);
    }

    public int getQtdMudas() {
        return qtdMudas;
    }
}
