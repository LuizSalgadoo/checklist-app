package com.checklist.dto;

import com.checklist.enums.DiaDaSemana;
import com.checklist.model.CheckList;
import com.checklist.enums.Status;
import com.checklist.repositories.CheckRepository;
import lombok.Getter;

import javax.persistence.ElementCollection;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

@Getter
public class CheckListDtoPost {
    private Integer id;
    private String nome;
    private String prioridade;
    private String horamarcada;
    private String referencia;

    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDENTE;

    @ElementCollection(targetClass = DiaDaSemana.class)
    @Enumerated(EnumType.STRING)
    private List<DiaDaSemana> diasDaSemana;

    private Integer repeticaoHoras;

    public CheckListDtoPost() {
    }

    public CheckListDtoPost(CheckList checkListPost) {
        this.id = checkListPost.getId();
        this.nome = checkListPost.getNome();
        this.prioridade = checkListPost.getPrioridade();
        this.horamarcada = checkListPost.getHoramarcada();
        this.referencia = checkListPost.getReferencia();
        this.repeticaoHoras = checkListPost.getRepeticaoHoras();
        this.diasDaSemana = checkListPost.getDiasDaSemana();
    }

    public CheckList converter() {
        return new CheckList(nome, prioridade, horamarcada, referencia, status, diasDaSemana, repeticaoHoras);
    }

    public CheckList converter(CheckRepository checkRepository) {
        CheckList checkList = checkRepository.findByNome(nome);
        return new CheckList(nome, prioridade, horamarcada, referencia, status, diasDaSemana,repeticaoHoras);
    }
}

