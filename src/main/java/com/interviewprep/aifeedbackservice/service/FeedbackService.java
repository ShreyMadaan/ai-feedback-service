package com.interviewprep.aifeedbackservice.service;

import com.interviewprep.aifeedbackservice.model.Feedback;
import com.interviewprep.aifeedbackservice.repository.FeedbackRepository;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;

    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }
    public Feedback generateFeedback(String submissionId, String code){
        Feedback feedback = new Feedback();
        feedback.setSubmissionId(submissionId);

        feedback.setCorrectness("Pending");
        feedback.setOptimization("Pending");
        feedback.setStyle("Pending");
        feedback.setComments("AI feedback not yet implemented");
        return feedbackRepository.save(feedback);
    }
    public Feedback getFeedback(Long id){
        return feedbackRepository.findById(id).orElse(null);
    }
}
