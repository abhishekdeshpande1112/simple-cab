package com.datarepublic.simplecab;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class Client {
  private static Logger logger = LoggerFactory.getLogger(Client.class);

  public static void main(String[] args){
    RestTemplate restTemplate = new RestTemplate();
    getMedallionsSummary(restTemplate, true);
  }

  private static void getMedallionsSummary(RestTemplate restTemplate, boolean ignoreCache)
  {
    final String url = "http://localhost:8080/cab/trips";

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> entity = new HttpEntity<String>(getInputJson(),headers);

    if(ignoreCache){
      deleteCache(restTemplate);
    }
    String result = restTemplate.postForObject(url, entity, String.class);
    logger.info(result);
  }

  private static void deleteCache(RestTemplate restTemplate){
    final String url = "http://localhost:8080/cab/cache/clear";

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    String result = restTemplate.getForObject(url, String.class);

    logger.info(result);
  }

  private static String getInputJson(){
    String requestJson = "{\n" +
            "\t\"medallions\": [\"0C107B532C1207A74F0D8609B9E092FF\",\"B672154F0FD3D6B5277580C3B7CBBF8E\"],\n" +
            "\t\"pickupDate\": \"2013-12-01\"\n" +
            "}";
    return requestJson;
  }

}
