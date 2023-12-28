package com.sms.studentSystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "student")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long studentId;
    @Column(name = "student_name")
    private String studentName;
    private int age;
    @Column(unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}
