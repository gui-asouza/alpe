package com.example.alpe.serpro.apis;

import com.example.alpe.exceptions.SerproException;


public interface SerproApi {

    String consultaNotaFiscal(String nf) throws SerproException;

    void consultaStatusSerpro() throws SerproException;
}
