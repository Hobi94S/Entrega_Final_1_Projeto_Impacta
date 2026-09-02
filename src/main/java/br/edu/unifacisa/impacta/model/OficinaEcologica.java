package br.edu.unifacisa.impacta.model;

import java.time.LocalDateTime;

public class OficinaEcologica extends AcaoSocioambiental {
    private int duracaoHoras;
    private boolean kitMaterial;

    public OficinaEcologica(int id, String titulo, String descricao, LocalDateTime data,
                            int capacidadeMax, int duracaoHoras, boolean kitMaterial) {
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

    public boolean isKitMaterial() {
        return false;
    }
}
