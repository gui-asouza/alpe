package com.example.alpe.dto;

import com.example.alpe.models.BoletoModel;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Date;

public class NotaFiscalDto {

    private Long id;

    @NotNull
    private Long numNF;

    @NotNull
    private Date dateNF;

    @NotNull
    private BigDecimal valor;

    @NotNull
    private String nomeEmpresa;

    @NotNull
    private String docEmpresa;

    @NotNull
    private String nomeCliente;

    @NotNull
    private String docCliente;

    @NotNull
    private String chave;

    private String status;

    private BoletoModel boletoModel;

    public NotaFiscalDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull Long getNumNF() {
        return numNF;
    }

    public void setNumNF(@NotNull Long numNF) {
        this.numNF = numNF;
    }

    public @NotNull Date getDateNF() {
        return dateNF;
    }

    public void setDateNF(@NotNull Date dateNF) {
        this.dateNF = dateNF;
    }

    public @NotNull BigDecimal getValor() {
        return valor;
    }

    public void setValor(@NotNull BigDecimal valor) {
        this.valor = valor;
    }

    public @NotNull String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(@NotNull String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public @NotNull String getDocEmpresa() {
        return docEmpresa;
    }

    public void setDocEmpresa(@NotNull String docEmpresa) {
        this.docEmpresa = docEmpresa;
    }

    public @NotNull String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(@NotNull String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public @NotNull String getDocCliente() {
        return docCliente;
    }

    public void setDocCliente(@NotNull String docCliente) {
        this.docCliente = docCliente;
    }

    public @NotNull String getChave() {
        return chave;
    }

    public void setChave(@NotNull String chave) {
        this.chave = chave;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BoletoModel getBoletoModel() {
        return boletoModel;
    }

    public void setBoletoModel(BoletoModel boletoModel) {
        this.boletoModel = boletoModel;
    }
}
