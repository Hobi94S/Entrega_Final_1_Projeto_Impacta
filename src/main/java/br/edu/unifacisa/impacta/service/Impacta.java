package br.edu.unifacisa.impacta.service;

import br.edu.unifacisa.impacta.exception.AcaoLotadaException;
import br.edu.unifacisa.impacta.exception.EmailDuplicadoException;
import br.edu.unifacisa.impacta.exception.InscricaoDuplicadaException;
import br.edu.unifacisa.impacta.model.AcaoSocioambiental;
import br.edu.unifacisa.impacta.model.Voluntario;

import java.util.Map;

public class Impacta {
    private Map<String, Voluntario> voluntarios;
    private Map<Integer, AcaoSocioambiental> acoes;
    private int proximoIdAcao;

    public Impacta() {
        throw new UnsupportedOperationException();
    }

    public boolean cadastrarVoluntario(String nome, String email, String matricula)
            throws EmailDuplicadoException {
        return false;
    }

    public String exibirVoluntario(String email) {
        return null;
    }

    public String[] listarVoluntarios() {
        return null;
    }

    public int cadastrarPlantio(String titulo, String descricao, String data,
                                int maxParticipantes, int qtdMudas) {
        return 0;
    }

    public int cadastrarMutirao(String titulo, String descricao, String data,
                                int maxParticipantes, int duracaoHoras) {
        return 0;
    }

    public int cadastrarOficina(String titulo, String descricao, String data,
                                int maxParticipantes, int duracaoHoras, boolean kitMaterial) {
        return 0;
    }

    public boolean inscreverVoluntario(String emailVoluntario, int idAcao)
            throws AcaoLotadaException, InscricaoDuplicadaException {
        return false;
    }

    public String exibirDetalhesAcao(int idAcao) {
        return null;
    }
}
