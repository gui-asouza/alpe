package com.example.alpe.services;

import com.example.alpe.dto.BoletoDto;
import com.example.alpe.exceptions.BoletoException;

public interface BoletoService {

    void entradaBoleto(BoletoDto boleto) throws BoletoException;
}
