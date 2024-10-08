package com.example.alpe.dto;

import java.math.BigDecimal;
import java.util.Date;

public class NotaFiscalDto {

    private Long id;

    private String numNF;

    private Date dateNF;

    private BigDecimal valor;

    private String nomeEmpresa;

    private String docEmpresa;

    private String nomeCliente;

    private String docCliente;

    private String chave;

    private Boolean isValid;

    public NotaFiscalDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumNF() {
        return numNF;
    }

    public void setNumNF(String numNF) {
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

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public Boolean getValid() {
        return isValid;
    }

    public void setValid(Boolean valid) {
        isValid = valid;
    }
}
