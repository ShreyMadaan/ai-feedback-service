package com.interviewprep.aifeedbackservice.llm;

import org.springframework.stereotype.Component;

@Component
public class LLMClient {
    private final String apiKey = System.getenv("OPENAI_API_KEY");

    public String getFeedback(String prompt){

        return "AI feedback placeholder:" + prompt;
    }
}
