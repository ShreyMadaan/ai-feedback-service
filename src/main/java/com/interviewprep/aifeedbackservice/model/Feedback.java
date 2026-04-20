package com.interviewprep.aifeedbackservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String submissionId;
    private String correctness;
    private String optimization;
    private String style;
    @Column(length = 5000)
    private String comments;
}
