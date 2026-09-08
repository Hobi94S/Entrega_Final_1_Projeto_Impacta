package br.edu.unifacisa.impacta.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class AcaoSocioambiental { //toda ação socioambiental vai ter essas características
    private int id;
    private String titulo;
    private String descricao;
    private LocalDateTime data;
    private int capacidadeMax;
    private List<Voluntario> inscritos = new ArrayList<>();

    protected AcaoSocioambiental(int id, String titulo, String descricao,
                                 LocalDateTime data, int capacidadeMax) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.data = data;
        this.capacidadeMax = capacidadeMax;
    }

    public abstract int calcularPontuacao();

    public int getId() {

        return id;
    }

    public String getTitulo() {

        return titulo;
    }

    public String getDescricao() {

        return descricao;
    }

    public LocalDateTime getData() {

        return data;
    }

    public int getCapacidadeMax() {

        return capacidadeMax;
    }

    public List<Voluntario> getInscritos() {

        return inscritos;
    }

}
