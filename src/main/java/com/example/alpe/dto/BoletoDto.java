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

    public @NotNull Long getNumIdentBoleto() {
        return numIdentBoleto;
    }

    public void setNumIdentBoleto(@NotNull Long numIdentBoleto) {
        this.numIdentBoleto = numIdentBoleto;
    }

    public @NotNull Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(@NotNull Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public @NotNull BigDecimal getValorBoleto() {
        return valorBoleto;
    }

    public void setValorBoleto(@NotNull BigDecimal valorBoleto) {
        this.valorBoleto = valorBoleto;
    }

    public @NotNull String getNomePagador() {
        return nomePagador;
    }

    public void setNomePagador(@NotNull String nomePagador) {
        this.nomePagador = nomePagador;
    }

    public @NotNull String getDocPagador() {
        return docPagador;
    }

    public void setDocPagador(@NotNull String docPagador) {
        this.docPagador = docPagador;
    }

    public @NotNull String getNomeBeneficiario() {
        return nomeBeneficiario;
    }

    public void setNomeBeneficiario(@NotNull String nomeBeneficiario) {
        this.nomeBeneficiario = nomeBeneficiario;
    }

    public @NotNull String getDocBeneficiario() {
        return docBeneficiario;
    }

    public void setDocBeneficiario(@NotNull String docBeneficiario) {
        this.docBeneficiario = docBeneficiario;
    }

    @Override
    public String toString() {
        return "BoletoModel{" +
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
