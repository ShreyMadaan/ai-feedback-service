package com.interviewprep.aifeedbackservice.llm;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class LLMClient {
    private final WebClient webClient;

    public LLMClient() {
        this.webClient = WebClient.builder()
                .baseUrl("https://api.openai.com/v1/chat/completions") // Example: OpenAI endpoint
                .defaultHeader("Authorization", "Bearer " + System.getenv("OPENAI_API_KEY"))
                .build();
    }

    public String getFeedback(String prompt) {
        // Example request body for OpenAI GPT-4
        String response = webClient.post()
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

        // For now, return raw JSON. Later, parse into feedback text.
        return response;
    }
}
