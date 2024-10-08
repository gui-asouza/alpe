package com.example.alpe.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Date;

public class BoletoDto {

    private Long id;

    @NotNull
    private Long numIdentBoleto;

    @NotNull
    private Date dataVencimento;

    @NotNull
    private BigDecimal valorBoleto;

    @NotNull
    private String nomePagador;

    @NotNull
    private String docPagador;

    @NotNull
    private String nomeBeneficiario;

    @NotNull
    private String docBeneficiario;

    public BoletoDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumIdentBoleto() {
        return numIdentBoleto;
    }

    public void setNumIdentBoleto(Long numIdentBoleto) {
        this.numIdentBoleto = numIdentBoleto;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public BigDecimal getValorBoleto() {
        return valorBoleto;
    }

    public void setValorBoleto(BigDecimal valorBoleto) {
        this.valorBoleto = valorBoleto;
    }

    public String getNomePagador() {
        return nomePagador;
    }

    public void setNomePagador(String nomePagador) {
        this.nomePagador = nomePagador;
    }

    public String getDocPagador() {
        return docPagador;
    }

    public void setDocPagador(String docPagador) {
        this.docPagador = docPagador;
    }

    public String getNomeBeneficiario() {
        return nomeBeneficiario;
    }

    public void setNomeBeneficiario(String nomeBeneficiario) {
        this.nomeBeneficiario = nomeBeneficiario;
    }

    public String getDocBeneficiario() {
        return docBeneficiario;
    }

    public void setDocBeneficiario(String docBeneficiario) {
        this.docBeneficiario = docBeneficiario;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", numIdentBoleto=" + numIdentBoleto +
                ", dataVencimento=" + dataVencimento +
                ", valorBoleto=" + valorBoleto +
                ", nomePagador='" + nomePagador + '\'' +
                ", docPagador='" + docPagador + '\'' +
                ", nomeBeneficiario='" + nomeBeneficiario + '\'' +
                ", docBeneficiario='" + docBeneficiario + '\'' +
                '}';
    }
}
