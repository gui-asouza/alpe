package com.example.alpe.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class NotaFiscalDto {

    @NotNull
    private Long numNF;

    @NotNull
    private BigDecimal dateNF;

    @NotNull
    private BigDecimal valor;

    public Long getNumNF() {
        return numNF;
    }

    public void setNumNF(@NotNull Long numNF) {
        this.numNF = numNF;
    }

    public BigDecimal getDateNF() {
        return dateNF;
    }

    public void setDateNF(@NotNull BigDecimal dateNF) {
        this.dateNF = dateNF;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(@NotNull BigDecimal valor) {
        this.valor = valor;
    }
}
