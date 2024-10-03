package com.example.alpe.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "NOTA_FISCAL")
public class NotaFiscalModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "`id`", unique = true, nullable = false)
    private Long id;

    @Column(name = "`numNF`", unique = true, nullable = false)
    private Long numNF;

    @Column(name = "`dateNF`", nullable = false)
    private BigDecimal dateNF;

    @Column(name = "`valor`", nullable = false)
    private BigDecimal valor;

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

    public BigDecimal getDateNF() {
        return dateNF;
    }

    public void setDateNF(BigDecimal dateNF) {
        this.dateNF = dateNF;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "NotaFiscalModel{" +
                "id=" + id +
                ", numNF=" + numNF +
                ", dateNF=" + dateNF +
                ", valor=" + valor +
                '}';
    }
}
