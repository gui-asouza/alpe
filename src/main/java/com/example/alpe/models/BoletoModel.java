package com.example.alpe.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "BOLETO")
public class BoletoModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "`id`", unique = true, nullable = false)
    private Long id;

    @Column(name = "`numIdentBoleto`", unique = true, nullable = false)
    private Long numIdentBoleto;

    @Column(name = "`dataVencimento`", nullable = false)
    private Date dataVencimento;

    @Column(name = "`valorBoleto`", nullable = false)
    private BigDecimal valorBoleto;

    @Column(name = "`nomePagador`", nullable = false)
    private String nomePagador;

    @Column(name = "`docPagador`", nullable = false)
    private String docPagador;

    @Column(name = "`nomeBeneficiario`", nullable = false)
    private String nomeBeneficiario;

    @Column(name = "`docBeneficiario`", nullable = false)
    private String docBeneficiario;

    @OneToOne
    @JoinColumn(name = "nota_fiscal_model_id", nullable = true)
    private NotaFiscalModel notaFiscalModel;

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

    public NotaFiscalModel getNotaFiscalModel() {
        return notaFiscalModel;
    }

    public void setNotaFiscalModel(NotaFiscalModel notaFiscalModel) {
        this.notaFiscalModel = notaFiscalModel;
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
                ", notaFiscalModel=" + notaFiscalModel +
                '}';
    }
}
