package br.edu.unifacisa.impacta.service;

import br.edu.unifacisa.impacta.exception.AcaoLotadaException;
import br.edu.unifacisa.impacta.exception.EmailDuplicadoException;
import br.edu.unifacisa.impacta.exception.InscricaoDuplicadaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ExcecoesImpactaTest {

    private Impacta impacta;

    @BeforeEach
    void criarImpacta() {
        impacta = new Impacta();
    }

    @Test
    void deveLancarExcecaoAoCadastrarEmailDuplicado() throws Exception {
        impacta.cadastrarVoluntario("Ana", "ana@email.com", "1");

        assertThrows(
                EmailDuplicadoException.class,
                () -> impacta.cadastrarVoluntario(
                        "Outra Ana",
                        "ana@email.com",
                        "2"
                )
        );
    }

    @Test
    void deveLancarExcecaoAoInscreverEmAcaoLotada() throws Exception {
        impacta.cadastrarVoluntario("Ana", "ana@email.com", "1");
        impacta.cadastrarVoluntario("Bruno", "bruno@email.com", "2");

        int idAcao = impacta.cadastrarPlantio(
                "Plantio",
                "Mudas nativas",
                "2026-09-02T10:00",
                1,
                5
        );

        impacta.inscreverVoluntario("ana@email.com", idAcao);

        assertThrows(
                AcaoLotadaException.class,
                () -> impacta.inscreverVoluntario("bruno@email.com", idAcao)
        );
    }

    @Test
    void deveLancarExcecaoAoDuplicarInscricao() throws Exception {

    }
}
