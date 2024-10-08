package com.example.alpe.controllers;

import com.example.alpe.dto.NotaFiscalDto;
import com.example.alpe.exceptions.NotaFiscalException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface NotaFiscalController {

    @PostMapping(path = "/nota-fiscal")
    ResponseEntity<Object> receiveNotaFiscal(@RequestBody NotaFiscalDto notaFiscal) throws NotaFiscalException;
}