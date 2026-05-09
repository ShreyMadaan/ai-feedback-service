package com.interviewprep.aifeedbackservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private String output;
    private String language;

    @Column(length = 2000) // allow longer feedback text
    private String feedbackText;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();
}
