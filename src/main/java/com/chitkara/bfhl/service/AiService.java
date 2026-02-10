package com.chitkara.bfhl.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class AiService {

    @Value("${GEMINI_API_KEY:}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public String getAnswer(String question) {

        if (apiKey == null || apiKey.isEmpty()) {
            return "Unknown";
        }

        String url =
                "https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent?key="
                        + apiKey;

        Map<String, Object> body = Map.of(
                "contents", List.of(
                        Map.of("parts", List.of(
                                Map.of("text", question + ". Answer in ONE WORD only.")
                        ))
                )
        );

        try {
            Map<String, Object> response =
                    restTemplate.postForObject(url, body, Map.class);

            List<Map<String, Object>> candidates =
                    (List<Map<String, Object>>) response.get("candidates");

            Map<String, Object> content =
                    (Map<String, Object>) candidates.get(0).get("content");

            List<Map<String, Object>> parts =
                    (List<Map<String, Object>>) content.get("parts");

            String text = (String) parts.get(0).get("text");

            return text.replaceAll("[^A-Za-z]", "").split(" ")[0];

        } catch (Exception e) {
            return "Unknown";
        }
    }
}
