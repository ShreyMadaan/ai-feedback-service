package com.interviewprep.aifeedbackservice.service;

import com.interviewprep.aifeedbackservice.model.Feedback;
import com.interviewprep.aifeedbackservice.repository.FeedbackRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;

    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
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
        feedback.setCreatedAt(new Date());

        return feedbackRepository.save(feedback);
    }

    public Feedback getFeedback(Long id){
        return feedbackRepository.findById(id).orElse(null);
    }
}
