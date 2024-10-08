package com.example.alpe.repositories;

import com.example.alpe.models.NotaFiscalModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface NotaFiscalRepository extends JpaRepository<NotaFiscalModel, Long> {

    Optional<NotaFiscalModel> findNotaFiscalModelByNumNF(String numNF);

    Optional<NotaFiscalModel> findNotaFiscalModelByDocClienteAndDocEmpresaAndValor(String docBeneficiario, String docPagador, BigDecimal valorBoleto);
}
