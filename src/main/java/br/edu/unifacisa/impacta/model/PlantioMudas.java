package br.edu.unifacisa.impacta.model;

import java.time.LocalDateTime;

public class PlantioMudas extends AcaoSocioambiental {
    private int qtdMudas;

    public PlantioMudas(int id, String titulo, String descricao, LocalDateTime data,
                        int capacidadeMax, int qtdMudas) {
        super(id, titulo, descricao, data, capacidadeMax);
        throw new UnsupportedOperationException();
    }

    @Override
    public int calcularPontuacao() {
        return 0;
    }

    public int getQtdMudas() {
        return 0;
    }
}
