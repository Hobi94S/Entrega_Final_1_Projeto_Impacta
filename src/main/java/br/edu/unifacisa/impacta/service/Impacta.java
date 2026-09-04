package br.edu.unifacisa.impacta.service;

import br.edu.unifacisa.impacta.exception.AcaoLotadaException;
import br.edu.unifacisa.impacta.exception.EmailDuplicadoException;
import br.edu.unifacisa.impacta.exception.InscricaoDuplicadaException;
import br.edu.unifacisa.impacta.model.AcaoSocioambiental;
import br.edu.unifacisa.impacta.model.MutiraoReciclagem;
import br.edu.unifacisa.impacta.model.OficinaEcologica;
import br.edu.unifacisa.impacta.model.PlantioMudas;
import br.edu.unifacisa.impacta.model.Voluntario;

import java.util.Map;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Impacta {
    private Map<String, Voluntario> voluntarios;
    private Map<Integer, AcaoSocioambiental> acoes;
    private int proximoIdAcao;

    public Impacta() {
        this.voluntarios = new HashMap<>();
        this.acoes = new HashMap<>();
        this.proximoIdAcao = 1;
    }

    public boolean cadastrarVoluntario(String nome, String email, String matricula)
            throws EmailDuplicadoException {
        if (voluntarios.containsKey(email)) {
            throw new EmailDuplicadoException("Email já cadastrado: " + email);
        }
        Voluntario v = new Voluntario(nome, email, matricula);
        voluntarios.put(email, v);
        return true;
    }

    public String exibirVoluntario(String email) {
        Voluntario v = voluntarios.get(email);
        if (v == null) {
            return "Voluntário não encontrado.";
        }
        return "Nome: " + v.getNome() +
                "\nEmail: " + v.getEmail() +
                "\nMatrícula: " + v.getMatricula() +
                "\nAções Participadas: " + v.getQuantidadeAcoes() +
                "\nPontuação de Impacto: " + v.getPontuacaoImpacto();
    }

    public String[] listarVoluntarios() {
        List<String> lista = new ArrayList<>();
        for (Voluntario v : voluntarios.values()) {
            lista.add(v.getNome() + " (" + v.getEmail() + ") - Pontuação: " + v.getPontuacaoImpacto());
        }
        return lista.toArray(new String[0]);
    }

    public int cadastrarPlantio(String titulo, String descricao, String data,
                                int maxParticipantes, int qtdMudas) {
        LocalDateTime dataHora = LocalDateTime.parse(data);
        PlantioMudas plantio = new PlantioMudas(proximoIdAcao, titulo, descricao, dataHora, maxParticipantes, qtdMudas);
        acoes.put(proximoIdAcao, plantio);
        return proximoIdAcao++;
    }

    public int cadastrarMutirao(String titulo, String descricao, String data,
                                int maxParticipantes, int duracaoHoras) {
        LocalDateTime dataHora = LocalDateTime.parse(data);
        MutiraoReciclagem mutirao = new MutiraoReciclagem(proximoIdAcao, titulo, descricao, dataHora, maxParticipantes, duracaoHoras);
        acoes.put(proximoIdAcao, mutirao);
        return proximoIdAcao++;
    }

    public int cadastrarOficina(String titulo, String descricao, String data,
                                int maxParticipantes, int duracaoHoras, boolean kitMaterial) {
        LocalDateTime dataHora = LocalDateTime.parse(data);
        OficinaEcologica oficina = new OficinaEcologica(proximoIdAcao, titulo, descricao, dataHora, maxParticipantes, duracaoHoras, kitMaterial);
        acoes.put(proximoIdAcao, oficina);
        return proximoIdAcao++;
    }

    public boolean inscreverVoluntario(String emailVoluntario, int idAcao)
            throws AcaoLotadaException, InscricaoDuplicadaException {
        Voluntario voluntario = voluntarios.get(emailVoluntario);
        if (voluntario == null) {
            return false;
        }

        AcaoSocioambiental acao = acoes.get(idAcao);
        if (acao == null) {
            return false;
        }

        if (acao.getInscritos(). contains(voluntario)) {
            throw new InscricaoDuplicadaException("Voluntário já inscrito nesta ação.");
        }

        if (acao.getInscritos().size() >= acao.getCapacidadeMax()) {
            throw new AcaoLotadaException("Ação lotada. Capacidade máxima: " + acao.getCapacidadeMax());
        }

        acao.getInscritos().add(voluntario);
        voluntario.setQuantidadeAcoes(voluntario.getQuantidadeAcoes() + 1);
        voluntario.setPontuacaoImpacto(voluntario.getPontuacaoImpacto() + acao.calcularPontuacao());

        return true;

    }

    public String exibirDetalhesAcao(int idAcao) {
        AcaoSocioambiental acao = acoes.get(idAcao);
        if (acao == null) {
            return "Ação não encontrada.";
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(acao.getId()).append("\n");
        sb.append("Título: ").append(acao.getTitulo()).append("\n");
        sb.append("Descrição: ").append(acao.getDescricao()).append("\n");
        sb.append("Data: ").append(acao.getData().format(formatter)).append("\n");
        sb.append("Capacidade Máxima: ").append(acao.getCapacidadeMax()).append("\n");
        sb.append("Inscritos: ");

        List<Voluntario> inscritos = acao.getInscritos();
        if (inscritos.isEmpty()) {
            sb.append("Nenhum inscrito");
        } else {
            for (int i = 0; i < inscritos.size(); i++) {
                if (i > 0) sb.append(", ");
                sb.append(inscritos.get(i).getNome());
            }
        }
        sb.append("\n");

        if (acao instanceof PlantioMudas) {
            sb.append("Quantidade de Mudas: ").append(((PlantioMudas) acao).getQtdMudas()).append("\n");
        } else if (acao instanceof MutiraoReciclagem) {
            sb.append("Duração (horas): ").append(((MutiraoReciclagem) acao).getDuracaoHoras()).append("\n");
        } else if (acao instanceof OficinaEcologica) {
            sb.append("Duração (horas): ").append(((OficinaEcologica) acao).getDuracaoHoras()).append("\n");
            sb.append("Kit de Material: ").append(((OficinaEcologica) acao).isKitMaterial() ? "Sim" : "Nao").append("\n");
        }

        return sb.toString();
    }
}
