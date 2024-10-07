package com.example.alpe.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
    private Date dateNF;

    @Column(name = "`valor`", nullable = false)
    private BigDecimal valor;

    @Column(name = "`nomeEmpresa`", nullable = false)
    private String nomeEmpresa;

    @Column(name = "`docEmpresa`", nullable = false)
    private String docEmpresa;

    @Column(name = "`nomeCliente`", nullable = false)
    private String nomeCliente;

    @Column(name = "`docCliente`", nullable = false)
    private String docCliente;

    @Column(name = "`chave`", unique = true, nullable = false)
    private String chave;

    @Column(name = "`status`")
    private String status;

    @OneToOne
    @JoinColumn(name = "boleto_model_id", nullable = true)
    private BoletoModel boletoModel;

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

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
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

    @Override
    public String toString() {
        return "NotaFiscalModel{" +
                "id=" + id +
                ", numNF=" + numNF +
                ", dateNF=" + dateNF +
                ", valor=" + valor +
                ", nomeEmpresa='" + nomeEmpresa + '\'' +
                ", docEmpresa='" + docEmpresa + '\'' +
                ", nomeCliente='" + nomeCliente + '\'' +
                ", docCliente='" + docCliente + '\'' +
                ", boletoModel=" + boletoModel +
                '}';
    }
}
