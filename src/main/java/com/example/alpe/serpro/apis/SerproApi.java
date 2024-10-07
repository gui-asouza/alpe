package com.example.alpe.serpro.apis;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class SerproApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(SerproApi.class);

    @Value(value = "${serpro.mock.authorization}")
    private String autorization;

    @Value(value = "${serpro.url.consulta-nfe-df-trial}")
    private String urlConsultaNfe;

    public JSONObject consultaNotaFiscal(String nf) {
        JSONObject statusNf;

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.set("Authorization", autorization);

            HttpEntity request = new HttpEntity(headers);

            String url = urlConsultaNfe.concat(nf);

            LOGGER.info("[Serpro-Api] - Consultando se nota fiscal {} é valida na Serpro", nf);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    request,
                    String.class
            );

            JSONObject jsonObject = new JSONObject(response.getBody());
            statusNf = jsonObject.getJSONObject("nfeProc")
                    .getJSONObject("protNFe")
                    .getJSONObject("infProt");

//            LOGGER.info("[Serpro-Api] - Nota fiscal {} é válida na Serpro com status {}", nf, statusNf.get("cStat").toString());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return statusNf;
    }
}
