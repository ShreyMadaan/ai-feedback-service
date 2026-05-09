package com.interviewprep.aifeedbackservice.controller;

import com.interviewprep.aifeedbackservice.model.Feedback;
import com.interviewprep.aifeedbackservice.service.FeedbackService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {
    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping("/generate")
    public ResponseEntity<Feedback> generateFeedback(@RequestBody FeedbackRequest request) {
        Feedback feedback = feedbackService.generateFeedback(
                request.getCode(),
                request.getOutput(),
                request.getLanguage()
        );
        return ResponseEntity.ok(feedback);
    }

    // Retrieve feedback by ID
    @GetMapping("/{id}")
    public ResponseEntity<Feedback> getFeedback(@PathVariable Long id) {
        Optional<Feedback> feedback = feedbackService.getFeedback(id);
        return feedback.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

// DTO for request body
@Getter
@Setter
class FeedbackRequest {
    private String code;
    private String output;
    private String language;
}
