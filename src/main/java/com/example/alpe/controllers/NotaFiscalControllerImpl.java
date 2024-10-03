package com.example.alpe.controllers;

import com.example.alpe.dto.NotaFiscalDto;
import com.example.alpe.models.NotaFiscalModel;
import com.example.alpe.repositories.NotaFiscalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class NotaFiscalControllerImpl implements NotaFiscalController {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotaFiscalControllerImpl.class);

    @Autowired
    NotaFiscalRepository notaFiscalRepository;

    @Override
    public ResponseEntity<List<NotaFiscalModel>> getAllNotaFiscal() {
        List<NotaFiscalModel> notaFiscalList;

        try {
            notaFiscalList = notaFiscalRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.status(HttpStatus.OK).body(notaFiscalList);
    }

    @Override
    public ResponseEntity<Object> getNotaFiscalById(Long id) {
        Optional<NotaFiscalModel> notaFiscal;

        try {
            notaFiscal = notaFiscalRepository.findById(id);

            if (notaFiscal.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nota Fiscal Not Found.");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.status(HttpStatus.OK).body(notaFiscal.get());
    }

    @Override
    public ResponseEntity<Object> saveNotaFiscal(NotaFiscalDto notaFiscal) {
        NotaFiscalModel notaFiscalModel;
        Optional<NotaFiscalModel> notaFiscalOptional;

        try {
            notaFiscalOptional = notaFiscalRepository.findById(notaFiscal.getNumNF());

            if (notaFiscalOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body("Nota Fiscal Já Importada!");
            }

            notaFiscalModel = new NotaFiscalModel();
            notaFiscalModel.setNumNF(notaFiscal.getNumNF());
            notaFiscalModel.setDateNF(notaFiscal.getDateNF());
            notaFiscalModel.setValor(notaFiscal.getValor());
            notaFiscalRepository.save(notaFiscalModel);

        } catch (Exception e) {
            LOGGER.error("[NOTA-FISCA-CONTROLLER] - Error: {}", e.getMessage());
            throw new RuntimeException(e);
        }

        LOGGER.info("[NOTA-FISCA-CONTROLLER] - Nota fiscal {} importada com sucesso!", notaFiscalModel.getNumNF());
        return ResponseEntity.status(HttpStatus.CREATED).body(notaFiscalModel);
    }
}
