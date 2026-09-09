package br.edu.unifacisa.impacta.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class OrdenacaoVoluntariosTest {

    private Impacta impacta;

    @BeforeEach
    void criarImpacta() {
        impacta = new Impacta();
    }

    @Test
    void deveOrdenarVoluntariosPorPontuacaoDecrescente() throws Exception {
        impacta.cadastrarVoluntario("Ana", "ana@email.com", "1");
        impacta.cadastrarVoluntario("Bruno", "bruno@email.com", "2");

        int idPlantio = impacta.cadastrarPlantio(
                "Plantio",
                "Plantio de mudas nativas",
                "2026-09-02T10:00",
                1,
                5
        );

        int idMutirao = impacta.cadastrarMutirao(
                "Mutirão",
                "Coleta seletiva",
                "2026-09-03T10:00",
                1,
                4
        );

        impacta.inscreverVoluntario("ana@email.com", idPlantio);
        impacta.inscreverVoluntario("bruno@email.com", idMutirao);

        String[] ranking = impacta.listarVoluntarios();

        assertTrue(ranking[0].startsWith("Nome: Bruno,"));
        assertTrue(ranking[1].startsWith("Nome: Ana,"));
    }

    @Test
    void deveDesempatarVoluntariosPorOrdemAlfabetica() throws Exception {
        impacta.cadastrarVoluntario("Carlos", "carlos@email.com", "1");
        impacta.cadastrarVoluntario("Ana", "ana@email.com", "2");

        int primeiroPlantio = impacta.cadastrarPlantio(
                "Plantio 1",
                "Plantio de mudas nativas",
                "2026-09-02T10:00",
                1,
                5
        );

        int segundoPlantio = impacta.cadastrarPlantio(
                "Plantio 2",
                "Plantio de mudas nativas",
                "2026-09-03T10:00",
                1,
                5
        );

        impacta.inscreverVoluntario("carlos@email.com", primeiroPlantio);
        impacta.inscreverVoluntario("ana@email.com", segundoPlantio);

        String[] ranking = impacta.listarVoluntarios();

        assertTrue(ranking[0].startsWith("Nome: Ana,"));
        assertTrue(ranking[1].startsWith("Nome: Carlos,"));
    }
}