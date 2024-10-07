package com.example.alpe.controllers;

import com.example.alpe.dto.NotaFiscalDto;
import com.example.alpe.models.BoletoModel;
import com.example.alpe.models.NotaFiscalModel;
import com.example.alpe.repositories.BoletoRepository;
import com.example.alpe.repositories.NotaFiscalRepository;
import com.example.alpe.serpro.apis.SerproApi;
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
    SerproApi serproApi;

    @Autowired
    NotaFiscalRepository notaFiscalRepository;

    @Autowired
    BoletoRepository boletoRepository;

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
    public ResponseEntity<Object> receiveNotaFiscal(NotaFiscalDto notaFiscal) {
        NotaFiscalModel notaFiscalModel;
        Optional<NotaFiscalModel> notaFiscalOptional;

        try {
            LOGGER.info("[Nota-Fiscal-Controller-Impl] - Verificando se NF já foi importada.");
            notaFiscalOptional = notaFiscalRepository.findById(notaFiscal.getNumNF());

            if (notaFiscalOptional.isPresent()) {
                LOGGER.info("[Nota-Fiscal-Controller-Impl] - Nota Fiscal {} já importada.", notaFiscal.getNumNF());
                return ResponseEntity.status(HttpStatus.OK).body("Nota Fiscal já importada!");
            }

            // TODO: FAZER A VALIDAÇÃO DA NOTA FISCAL NO GOVERNO

            notaFiscalModel = new NotaFiscalModel();
            notaFiscalModel.setNumNF(notaFiscal.getNumNF());
            notaFiscalModel.setDateNF(notaFiscal.getDateNF());
            notaFiscalModel.setValor(notaFiscal.getValor());
            notaFiscalModel.setNomeEmpresa(notaFiscal.getNomeEmpresa());
            notaFiscalModel.setDocEmpresa(notaFiscal.getDocEmpresa());
            notaFiscalModel.setNomeCliente(notaFiscal.getNomeCliente());
            notaFiscalModel.setDocCliente(notaFiscal.getDocCliente());

            LOGGER.info("[NOTA-FISCAL-CONTROLLER] - Buscando boleto referente a NF {}.", notaFiscal.getNumNF());
            BoletoModel boleto = boletoRepository.findBoletoModelByDocBeneficiarioAndDocPagadorAndValorBoleto(
                    notaFiscal.getDocEmpresa(), notaFiscal.getDocCliente(), notaFiscal.getValor());

            if (boleto != null) {
                notaFiscalModel.setBoletoModel(boleto);
            }

            LOGGER.info("[NOTA-FISCAL-CONTROLLER] - Salvando Nota Fiscal {}.", notaFiscalModel.getNumNF());
            notaFiscalRepository.save(notaFiscalModel);
            LOGGER.info("[NOTA-FISCAL-CONTROLLER] - Nota fiscal {} importada com sucesso!", notaFiscalModel.getNumNF());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(String.format("Nota Fiscal %s importada com sucesso!", notaFiscalModel.getNumNF()));
    }

    @Override
    public ResponseEntity<Object> consultNotaFiscal() {
        try {
            serproApi.consultaNotaFiscal("31160906347409006953550110008369841081956475");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
