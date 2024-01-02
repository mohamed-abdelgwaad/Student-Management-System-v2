package com.sms.studentSystem.model;

import javax.persistence.*;

@Entity
@Table(name = "teacher")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    private Long teacherId;
    @Column(name = "teacher_name")
    private String teacherName;
    private String age;
    @Column(unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

   
}
