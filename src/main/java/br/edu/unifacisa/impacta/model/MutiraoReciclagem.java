package br.edu.unifacisa.impacta.model;

import java.time.LocalDateTime;

public class MutiraoReciclagem extends AcaoSocioambiental {
    private int duracaoHoras;

    public MutiraoReciclagem(int id, String titulo, String descricao, LocalDateTime data,
                             int capacidadeMax, int duracaoHoras) {
        super(id, titulo, descricao, data, capacidadeMax);
        this.duracaoHoras = duracaoHoras;
    }

    @Override
    public int calcularPontuacao() {

        return 4 * duracaoHoras;
    }

    public int getDuracaoHoras() {

        return duracaoHoras;
    }
}
