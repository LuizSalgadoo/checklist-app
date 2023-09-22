package com.checklist.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public class CheckListDtoPut {

    @NotNull
    public String nome;
    @NotNull
    public String prioridade;
    @NotNull
    public String horamarcada;

    public String referencia;

    public LocalDateTime horainicio = LocalDateTime.now();

    public LocalDateTime horafim = LocalDateTime.now();

}
