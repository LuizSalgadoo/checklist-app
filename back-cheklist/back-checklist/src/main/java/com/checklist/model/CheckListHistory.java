package com.checklist.model;

import com.checklist.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter

public class CheckListHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;


    private String horamarcada;
    @JsonFormat(timezone = "UTC", pattern="HH:mm")
    public LocalDateTime horainicio;


    @JsonFormat(timezone = "UTC", pattern="HH:mm")
    public LocalDateTime horafim;

    public CheckListHistory(Integer id, String nome, String horamarcada, LocalDateTime horainicio, LocalDateTime horafim) {
        this.id = id;
        this.nome = nome;
        this.horamarcada = horamarcada;
        this.horainicio = horainicio;
        this.horafim = horafim;
    }

    public CheckListHistory() {

    }
}
