package com.interviewprep.aifeedbackservice.llm;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

@Component
public class LLMClient {
    private final WebClient webClient;
    private final ObjectMapper mapper = new ObjectMapper();

    public LLMClient() {
        this.webClient = WebClient.builder()
                .baseUrl("https://api.openai.com/v1/chat/completions")
                .defaultHeader("Authorization", "Bearer " + System.getenv("OPENAI_API_KEY"))
                .build();
    }

    public String getFeedback(String prompt) {
        String responseJson = webClient.post()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("""
                    {
                      "model": "gpt-4",
                      "messages": [
                        {"role": "system", "content": "You are a code reviewer."},
                        {"role": "user", "content": "%s"}
                      ],
                      "max_tokens": 300
                    }
                    """.formatted(prompt))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        try {
            JsonNode root = mapper.readTree(responseJson);
            return root.path("choices").get(0).path("message").path("content").asText();
        } catch (Exception e) {
            throw new RuntimeException("Error parsing LLM response", e);
        }
    }
}
