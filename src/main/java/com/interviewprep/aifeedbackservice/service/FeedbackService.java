package com.interviewprep.aifeedbackservice.service;

import com.interviewprep.aifeedbackservice.llm.LLMClient;
import com.interviewprep.aifeedbackservice.model.Feedback;
import com.interviewprep.aifeedbackservice.repository.FeedbackRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final LLMClient llmClient;

    public FeedbackService(FeedbackRepository feedbackRepository, LLMClient llmClient) {
        this.feedbackRepository = feedbackRepository;
        this.llmClient = llmClient;
    }
    public Feedback generateFeedback(String code, String output, String language) {
        String prompt = "Analyze the following " + language + " code:\n" + code +
                "\nExecution Output:\n" + output +
                "\nProvide feedback on correctness, efficiency, and style.";

        String aiResponse = llmClient.getFeedback(prompt);

        Feedback feedback = new Feedback();
        feedback.setCode(code);
        feedback.setOutput(output);
        feedback.setLanguage(language);
        feedback.setFeedbackText(aiResponse);
        feedback.setCreatedAt(LocalDateTime.now());

        return feedbackRepository.save(feedback);
    }

    // Retrieve feedback by ID
    public Optional<Feedback> getFeedback(Long id) {
        return feedbackRepository.findById(id);
    }
}
