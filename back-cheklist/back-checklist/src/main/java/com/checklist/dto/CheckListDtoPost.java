package com.checklist.dto;

import com.checklist.model.CheckList;
import com.checklist.enums.Status;
import com.checklist.repositories.CheckRepository;
import lombok.Getter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
public class CheckListDtoPost {
    private Integer id;
    private String nome;
    private String prioridade;
    private String horamarcada;

    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDENTE;

    public CheckListDtoPost() {

    }

    public CheckListDtoPost(CheckList checkListPost) {
        this.id = checkListPost.getId();
        this.nome = checkListPost.getNome();
        this.prioridade = checkListPost.getPrioridade();
        this.horamarcada = checkListPost.getHoramarcada();
    }


    public CheckList converter() {
        return new CheckList(nome, prioridade, horamarcada, status);
    }

    public CheckList converter(CheckRepository checkRepository) {
        CheckList checkList = checkRepository.findByNome(nome);
        return new CheckList(nome, prioridade, horamarcada, status);
    }
}
