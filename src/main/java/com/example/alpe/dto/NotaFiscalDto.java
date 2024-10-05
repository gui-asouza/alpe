package com.example.alpe.dto;

import com.example.alpe.models.BoletoModel;
import jakarta.persistence.Column;
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

    private BoletoModel boletoModel;

    public NotaFiscalDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumNF() {
        return numNF;
    }

    public void setNumNF(Long numNF) {
        this.numNF = numNF;
    }

    public Date getDateNF() {
        return dateNF;
    }

    public void setDateNF(Date dateNF) {
        this.dateNF = dateNF;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getDocEmpresa() {
        return docEmpresa;
    }

    public void setDocEmpresa(String docEmpresa) {
        this.docEmpresa = docEmpresa;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getDocCliente() {
        return docCliente;
    }

    public void setDocCliente(String docCliente) {
        this.docCliente = docCliente;
    }

    public BoletoModel getBoletoModel() {
        return boletoModel;
    }

    public void setBoletoModel(BoletoModel boletoModel) {
        this.boletoModel = boletoModel;
    }
}
