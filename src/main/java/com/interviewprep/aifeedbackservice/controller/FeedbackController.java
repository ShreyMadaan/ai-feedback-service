package com.interviewprep.aifeedbackservice.controller;

import com.interviewprep.aifeedbackservice.model.Feedback;
import com.interviewprep.aifeedbackservice.service.FeedbackService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {
    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping("/generate")
    public Feedback generateFeedback(@RequestParam String submissionId, @RequestBody String code){
        return feedbackService.generateFeedback(submissionId, code);
    }
    @GetMapping("/{id}")
    public Feedback getFeedback(@PathVariable Long id){
        return feedbackService.getFeedback(id);
    }
}
