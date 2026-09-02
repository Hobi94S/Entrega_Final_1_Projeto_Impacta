package br.edu.unifacisa.impacta.model;

import java.time.LocalDateTime;

public class MutiraoReciclagem extends AcaoSocioambiental {
    private int duracaoHoras;

    public MutiraoReciclagem(int id, String titulo, String descricao, LocalDateTime data,
                             int capacidadeMax, int duracaoHoras) {
        super(id, titulo, descricao, data, capacidadeMax);
        throw new UnsupportedOperationException();
    }

    @Override
    public int calcularPontuacao() {
        return 0;
    }

    public int getDuracaoHoras() {
        return 0;
    }
}
