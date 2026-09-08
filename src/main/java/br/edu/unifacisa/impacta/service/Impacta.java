package br.edu.unifacisa.impacta.service;

import br.edu.unifacisa.impacta.exception.AcaoLotadaException;
import br.edu.unifacisa.impacta.exception.EmailDuplicadoException;
import br.edu.unifacisa.impacta.exception.InscricaoDuplicadaException;
import br.edu.unifacisa.impacta.model.AcaoSocioambiental;
import br.edu.unifacisa.impacta.model.Voluntario;
import br.edu.unifacisa.impacta.model.PlantioMudas;
import br.edu.unifacisa.impacta.model.MutiraoReciclagem;
import br.edu.unifacisa.impacta.model.OficinaEcologica;



import java.time.LocalDateTime;
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

    private LocalDateTime converterData(String data) {
        return LocalDateTime.parse(data);  // transforma o texto da data em um localdatetime
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
        int id = proximoIdAcao++;
        LocalDateTime dataConvertida = converterData(data); //converte
        PlantioMudas acao = new PlantioMudas(id, titulo, descricao, dataConvertida, maxParticipantes, qtdMudas); //ação
        acoes.put(id, acao);
        return id;
    }

    public int cadastrarMutirao(String titulo, String descricao, String data,
                                int maxParticipantes, int duracaoHoras) {
        int id = proximoIdAcao++;
        LocalDateTime dataConvertida = converterData(data);
        MutiraoReciclagem acao = new MutiraoReciclagem(id, titulo, descricao, dataConvertida, maxParticipantes, duracaoHoras);
        acoes.put(id, acao);
        return id;
    }

    public int cadastrarOficina(String titulo, String descricao, String data,
                                int maxParticipantes, int duracaoHoras, boolean kitMaterial) {
        int id = proximoIdAcao++;
        LocalDateTime dataConvertida = converterData(data);
        OficinaEcologica acao = new OficinaEcologica(id, titulo, descricao, dataConvertida, maxParticipantes, duracaoHoras, kitMaterial);
        acoes.put(id, acao);
        return id;
    }

    public boolean inscreverVoluntario(String emailVoluntario, int idAcao)
            throws AcaoLotadaException, InscricaoDuplicadaException {
        AcaoSocioambiental acao = acoes.get(idAcao); //busca acao pelo id
        Voluntario voluntario = voluntarios.get(emailVoluntario); //mesma logica

        if (acao.getInscritos().contains(voluntario)) {  //inscrito?
            throw new InscricaoDuplicadaException("Voluntário já inscrito nessa ação."); //erro
        }
        if (acao.getInscritos().size() >= acao.getCapacidadeMax()) {  //lotou?
            throw new AcaoLotadaException("Ação já atingiu a capacidade máxima."); //erro
        }

        acao.getInscritos().add(voluntario);
        voluntario.setQuantidadeAcoes(voluntario.getQuantidadeAcoes() + 1); //soma mais 1 na contagem da acao
        voluntario.setPontuacaoImpacto(voluntario.getPontuacaoImpacto() + acao.calcularPontuacao()); //soma pontuacao na acao
        return true;
    }

    public String exibirDetalhesAcao(int idAcao) {
        AcaoSocioambiental acao = acoes.get(idAcao);

        if (acao == null) {
            return null;
        }
        return "Título: " + acao.getTitulo()
                + ", Descrição: " + acao.getDescricao()
                + ", Data: " + acao.getData()
                + ", Pontuação: " + acao.calcularPontuacao()
                + ", Incristos: " + acao.getInscritos().size();
    }
}

