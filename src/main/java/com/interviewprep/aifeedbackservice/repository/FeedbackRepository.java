package com.interviewprep.aifeedbackservice.repository;

import com.interviewprep.aifeedbackservice.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

}
