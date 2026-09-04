package main.java.br.edu.unifacisa.impacta.service;

import br.edu.unifacisa.impacta.exception.AcaoLotadaException;
import br.edu.unifacisa.impacta.exception.EmailDuplicadoException;
import br.edu.unifacisa.impacta.exception.InscricaoDuplicadaException;
import br.edu.unifacisa.impacta.model.AcaoSocioambiental;
import br.edu.unifacisa.impacta.model.Voluntario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Impacta {
    private Map<String, Voluntario> voluntarios;
    private Map<Integer, AcaoSocioambiental> acoes;
    private int proximoIdAcao;

    public Impacta() {
        voluntarios = new HashMap<>();
        acoes = new HashMap<>();
        proximoIdAcao = 1;
    }

    public boolean cadastrarVoluntario(String nome, String email, String matricula)
            throws EmailDuplicadoException {
        if (voluntarios.containsKey(email)) {
            throw new EmailDuplicadoException("Já existe um voluntário com esse e-mail.");
        }

        Voluntario voluntario = new Voluntario(nome, email, matricula);
        voluntarios.put(email, voluntario);
        return true;
    }

    public String exibirVoluntario(String email) {
        Voluntario voluntario = voluntarios.get(email);

        if (voluntario == null) {
            return null;
        }

        return voluntario.toString();
    }

    public String[] listarVoluntarios() {
        List<Voluntario> ranking = new ArrayList<>(voluntarios.values());
        ranking.sort(null);

        String[] lista = new String[ranking.size()];

        for (int i = 0; i < ranking.size(); i++) {
            lista[i] = ranking.get(i).toString();
        }

        return lista;
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
