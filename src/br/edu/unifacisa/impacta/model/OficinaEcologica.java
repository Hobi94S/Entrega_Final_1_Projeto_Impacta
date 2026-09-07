package br.edu.unifacisa.impacta.model;

import java.time.LocalDateTime;

public class OficinaEcologica extends br.edu.unifacisa.impacta.model.AcaoSocioambiental {
    private int duracaoHoras;
    private boolean kitMaterial;

    public OficinaEcologica(int id, String titulo, String descricao, LocalDateTime data,
                            int capacidadeMax, int duracaoHoras, boolean kitMaterial) {
        super(id, titulo, descricao, data, capacidadeMax);
        this.duracaoHoras = duracaoHoras;
        this.kitMaterial = kitMaterial;
    }

    @Override
    public int calcularPontuacao() {
        if (kitMaterial) {
            return 3 * duracaoHoras + 10;
        } else {
            return 3 * duracaoHoras;
        }
    }

    public int getDuracaoHoras() {
        return duracaoHoras;
    }

    public boolean isKitMaterial() {
        return kitMaterial;
    }
}
