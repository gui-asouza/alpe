package com.example.alpe.controllers;

import com.example.alpe.dto.NotaFiscalDto;
import com.example.alpe.models.NotaFiscalModel;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface NotaFiscalController {

    @GetMapping("/nota-fiscal")
    public ResponseEntity<List<NotaFiscalModel>> getAllNotaFiscal();

    @GetMapping("/nota-fiscal/id")
    public ResponseEntity<Object> getNotaFiscalById(@PathVariable(value = "id") Long id);

    @PostMapping(path = "/nota-fiscal")
    ResponseEntity<Object> saveNotaFiscal(@RequestBody @Valid NotaFiscalDto notaFiscal);

}
