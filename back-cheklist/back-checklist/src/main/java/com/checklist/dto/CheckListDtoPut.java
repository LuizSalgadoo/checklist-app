package com.checklist.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CheckListDtoPut {

    @NotNull
    public String nome;
    @NotNull
    public String prioridade;
    @NotNull
    public String horamarcada;

}
