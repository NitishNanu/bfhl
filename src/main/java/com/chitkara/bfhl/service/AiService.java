package com.chitkara.bfhl.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class AiService {

    @Value("${GEMINI_API_KEY:}")
    private String apiKey;

    private static final String GEMINI_URL =
    	    "https://generativelanguage.googleapis.com/v1/models/gemini-1.5-flash:generateContent?key=";

    public String getAnswer(String question) {

        System.out.println("GEMINI_API_KEY loaded: " + (!apiKey.isEmpty()));

        if (apiKey == null || apiKey.isEmpty()) {
            return "Unknown";
        }

        try {
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            Map<String, Object> body = Map.of(
                "contents", List.of(
                    Map.of("parts", List.of(
                        Map.of("text", question)
                    ))
                )
            );

            HttpEntity<Map<String, Object>> entity =
                new HttpEntity<>(body, headers);

            ResponseEntity<Map> response =
                restTemplate.postForEntity(
                    GEMINI_URL + apiKey,
                    entity,
                    Map.class
                );

            List<Map> candidates =
                (List<Map>) response.getBody().get("candidates");

            Map content =
                (Map) candidates.get(0).get("content");

            List<Map> parts =
                (List<Map>) content.get("parts");

            return parts.get(0).get("text").toString().trim();

        } catch (Exception e) {
            e.printStackTrace();
            return "Unknown";
        }
    }
}
