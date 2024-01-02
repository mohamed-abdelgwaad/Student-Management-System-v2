package com.sms.studentSystem.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;
    @Column(name = "course_name")
    private String courseName;

    @OneToMany(mappedBy = "course")
    private List<Student> students;

    @OneToMany(mappedBy = "course")
    private List<Teacher> teachers;

    @OneToMany(mappedBy = "course")
    private List<Quiz> quizzes;

   
}
