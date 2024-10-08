package com.example.alpe.serpro.apis;

import com.example.alpe.exceptions.SerproException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class SerproApiImpl implements SerproApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(SerproApiImpl.class);

    @Value(value = "${serpro.mock.authorization}")
    private String autorization;

    private String urlConsultaNfe = "https://gateway.apiserpro.serpro.gov.br/consulta-nfe-df-trial/api/v1/nfe/";
    private String urlStatus = "https://gateway.apiserpro.serpro.gov.br/consulta-nfe-df-trial/api/v1/nfe/status";

    public String consultaNotaFiscal(String nf) throws SerproException {
        String statusNf;

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.set("Authorization", autorization);

            HttpEntity request = new HttpEntity(headers);

            String url = urlConsultaNfe + nf;

            LOGGER.info("[SERPRO-API] - Consultando se nota fiscal {} é valida na Serpro", nf);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    request,
                    String.class
            );

            if (response.getStatusCode().toString().equals("400")) {
                return response.getStatusCode().toString();
            }

            JSONObject jsonObject = new JSONObject(response.getBody());
            statusNf = jsonObject.getJSONObject("nfeProc")
                    .getJSONObject("protNFe")
                    .getJSONObject("infProt")
                    .get("cStat").toString();

        } catch (Exception e) {
            throw new SerproException(e);
        }

        return statusNf;
    }

    @Override
    public void consultaStatusSerpro() throws SerproException {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.set("Authorization", autorization);

            HttpEntity request = new HttpEntity(headers);

            LOGGER.info("[SERPRO-API] - Consultando se sistema da Serpro esta online");
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(
                    urlStatus,
                    HttpMethod.GET,
                    request,
                    String.class
            );

            if (response.getStatusCode().toString().equals("500")) {
                LOGGER.info("[SERPRO-API] - O sistema da Serpro esta offline");
                throw new SerproException("O sistema da Serpro esta offline");
            }

            LOGGER.info("[SERPRO-API] - O sistema da Serpro esta online");
        } catch (Exception e) {
            throw new SerproException(e);
        }
    }
}
