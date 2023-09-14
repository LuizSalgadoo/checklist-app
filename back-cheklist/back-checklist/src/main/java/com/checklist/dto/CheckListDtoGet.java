package com.checklist.dto;

import com.checklist.model.CheckList;
import com.checklist.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
@Getter
@Setter
public class CheckListDtoGet {
    private Integer id;
    private String nome;
    private String prioridade;
    private String horamarcada;

    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDENTE;
    @JsonFormat(timezone = "UTC", pattern="HH:mm")
    private LocalDateTime horainicio;
    @JsonFormat(timezone = "UTC", pattern = "HH:mm")
    private LocalDateTime horafim;

    public CheckListDtoGet(CheckList checkList) {
        this.id = checkList.getId();
        this.nome = checkList.getNome();
        this.prioridade = checkList.getPrioridade();
        this.horamarcada = checkList.getHoramarcada();
        this.status = checkList.getStatus();
        this.horainicio = checkList.getHorainicio();
        this.horafim = checkList.getHorafim();
    }

    public static List<CheckListDtoGet> converter(List<CheckList> checkList) {
        return checkList.stream().map(CheckListDtoGet::new).collect(Collectors.toList());
    }

    public void setStatus(Status novoStatus) {
        this.status = novoStatus;
    }
}
