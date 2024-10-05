package com.example.alpe.repositories;

import com.example.alpe.models.BoletoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface BoletoRepository extends JpaRepository<BoletoModel, Long> {
    Optional<BoletoModel> findBoletoModelByNumIdentBoleto(Long numIdentBoleto);
    BoletoModel findBoletoModelByDocBeneficiarioAndDocPagadorAndValorBoleto(String docEmpresa, String docCliente, BigDecimal valor);
}
