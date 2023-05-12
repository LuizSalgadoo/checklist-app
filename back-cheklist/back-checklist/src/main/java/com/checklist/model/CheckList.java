package com.checklist.model;


import com.checklist.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "CHECKLIST")
public class CheckList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotBlank
    private String nome;
    @NotBlank
    private String prioridade;
    private String horamarcada;

    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDENTE;

    @CreationTimestamp
    @JsonFormat(timezone = "UTC", pattern="HH:mm")
    public LocalDateTime horainicio = LocalDateTime.now();

    @CreationTimestamp
    @JsonFormat(timezone = "UTC", pattern="HH:mm")
    public LocalDateTime horafim = LocalDateTime.now();

    public CheckList() {

    }
    public CheckList(String nome, String prioridade, String horamarcada, Status status) {
        this.nome = nome;
        this.prioridade = prioridade;
        this.horamarcada = horamarcada;
        this.status = status;
    }

}
