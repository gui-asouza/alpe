package com.example.alpe.repositories;

import com.example.alpe.models.NotaFiscalModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotaFiscalRepository extends JpaRepository<NotaFiscalModel, Long> {
}
