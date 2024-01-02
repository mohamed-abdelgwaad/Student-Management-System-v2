package com.sms.studentSystem.model;

import javax.persistence.*;

@Entity
@Table(name = "quiz")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quizId;
    @Column(name = "quiz_name")
    private String quizName;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

  
}
