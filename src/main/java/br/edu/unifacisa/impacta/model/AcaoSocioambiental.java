package br.edu.unifacisa.impacta.model;

import java.time.LocalDateTime;
import java.util.List;

public abstract class AcaoSocioambiental {
    private int id;
    private String titulo;
    private String descricao;
    private LocalDateTime data;
    private int capacidadeMax;
    private List<Voluntario> inscritos;

    protected AcaoSocioambiental(int id, String titulo, String descricao,
                                 LocalDateTime data, int capacidadeMax) {
        throw new UnsupportedOperationException();
    }

    public abstract int calcularPontuacao();

    public int getId() {
        return 0;
    }

    public String getTitulo() {
        return null;
    }

    public String getDescricao() {
        return null;
    }

    public LocalDateTime getData() {
        return null;
    }

    public int getCapacidadeMax() {
        return 0;
    }

    public List<Voluntario> getInscritos() {
        return null;
    }
}
