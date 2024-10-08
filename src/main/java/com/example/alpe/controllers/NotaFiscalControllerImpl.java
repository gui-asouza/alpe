package com.example.alpe.controllers;

import com.example.alpe.dto.NotaFiscalDto;
import com.example.alpe.exceptions.NotaFiscalException;
import com.example.alpe.exceptions.SerproException;
import com.example.alpe.models.NotaFiscalModel;
import com.example.alpe.repositories.NotaFiscalRepository;
import com.example.alpe.serpro.apis.SerproApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class NotaFiscalControllerImpl implements NotaFiscalController {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotaFiscalControllerImpl.class);

    @Autowired
    SerproApi serproApi;

    @Autowired
    NotaFiscalRepository notaFiscalRepository;

    @Override
    public ResponseEntity<Object> receiveNotaFiscal(NotaFiscalDto notaFiscal) throws NotaFiscalException {
        NotaFiscalModel notaFiscalModel;
        Optional<NotaFiscalModel> notaFiscalOptional;

        try {
            LOGGER.info("[NOTA-FISCAL-CONTROLLER] - Verificando se NF já foi importada.");
            notaFiscalOptional = notaFiscalRepository.findNotaFiscalModelByNumNF(notaFiscal.getNumNF());

            if (notaFiscalOptional.isPresent()) {
                LOGGER.info("[NOTA-FISCAL-CONTROLLER] - Nota Fiscal {} já importada.", notaFiscal.getNumNF());
                return ResponseEntity.status(HttpStatus.OK).body("Nota Fiscal já importada!");
            }

            LOGGER.info("[NOTA-FISCAL-CONTROLLER] - Consultando a NF {} no Serpro.", notaFiscal.getChave());
            String responseConsultaNF = validateNotaFiscal(notaFiscal.getChave());

            notaFiscalModel = new NotaFiscalModel();
            notaFiscalModel.setNumNF(notaFiscal.getNumNF());
            notaFiscalModel.setDateNF(notaFiscal.getDateNF());
            notaFiscalModel.setValor(notaFiscal.getValor());
            notaFiscalModel.setNomeEmpresa(notaFiscal.getNomeEmpresa());
            notaFiscalModel.setDocEmpresa(notaFiscal.getDocEmpresa());
            notaFiscalModel.setNomeCliente(notaFiscal.getNomeCliente());
            notaFiscalModel.setDocCliente(notaFiscal.getDocCliente());
            notaFiscalModel.setChave(notaFiscal.getChave());

            if (responseConsultaNF.equals("100")) {
                notaFiscalModel.setIsValid(Boolean.TRUE);
            } else {
                notaFiscalModel.setIsValid(Boolean.FALSE);
            }

            LOGGER.info("[NOTA-FISCAL-CONTROLLER] - Salvando Nota Fiscal {}.", notaFiscalModel);
            notaFiscalRepository.save(notaFiscalModel);
            LOGGER.info("[NOTA-FISCAL-CONTROLLER] - Nota fiscal {} importada com sucesso!", notaFiscalModel.getNumNF());

            if (!responseConsultaNF.equals("100")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Esta nota fiscal não esta valida!");
            }

        } catch (Exception e) {
            throw new NotaFiscalException(e);
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(String.format("Nota Fiscal %s importada com sucesso!", notaFiscalModel.getNumNF()));
    }

    private String validateNotaFiscal(String numNf) throws SerproException {
        String responseConsultaNF;

        try {
            serproApi.consultaStatusSerpro();
            responseConsultaNF = serproApi.consultaNotaFiscal(numNf);
        } catch (Exception e) {
            throw new SerproException(e);
        }

        return responseConsultaNF;
    }

}
