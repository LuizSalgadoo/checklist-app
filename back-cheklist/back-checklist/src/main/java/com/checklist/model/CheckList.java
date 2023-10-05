package com.checklist.model;


import com.checklist.enums.DiaDaSemana;
import com.checklist.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;
import java.util.List;

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

    private String referencia;

    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDENTE;


    @JsonFormat(timezone = "UTC", pattern="HH:mm")
    public LocalDateTime horainicio;


    @JsonFormat(timezone = "UTC", pattern="HH:mm")
    public LocalDateTime horafim;

    @ElementCollection(targetClass = DiaDaSemana.class)
    @Enumerated(EnumType.STRING)
    private List<DiaDaSemana> diasDaSemana;

    private Integer repeticaoHoras;

    public CheckList() {}

    public CheckList(Integer id, String nome, String prioridade, String horamarcada, String referencia, Status status, LocalDateTime horainicio, LocalDateTime horafim, List<DiaDaSemana> diasDaSemana, Integer repeticaoHoras) {
        this.id = id;
        this.nome = nome;
        this.prioridade = prioridade;
        this.horamarcada = horamarcada;
        this.referencia = referencia;
        this.status = status;
        this.horainicio = horainicio;
        this.horafim = horafim;
        this.diasDaSemana = diasDaSemana;
        this.repeticaoHoras = repeticaoHoras;
    }


    public CheckList(String nome, String prioridade, String horamarcada, String referencia, Status status, List<DiaDaSemana> diasDaSemana, Integer repeticaoHoras) {
        this.nome = nome;
        this.prioridade = prioridade;
        this.horamarcada = horamarcada;
        this.referencia = referencia;
        this.status = status;
        this.diasDaSemana = diasDaSemana;
        this.repeticaoHoras = repeticaoHoras;
    }

    public void ajustarHoraInicio(int horas) {
        if (this.horainicio != null) {
            this.horainicio = this.horainicio.plusHours(horas);
        }
    }

}
