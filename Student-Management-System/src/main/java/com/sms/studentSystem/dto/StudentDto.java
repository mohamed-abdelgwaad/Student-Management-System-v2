package com.sms.studentSystem.dto;

import com.sms.studentSystem.model.Course;

import lombok.Builder;

@Builder
public class StudentDto {

    private Long studentId;
    private String studentName;
    private int age;
    private String email;

    private Course course;

    public StudentDto(Long studentId, String studentName, int age, String email, Course course) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.age = age;
        this.email = email;
        this.course = course;
    }

    public StudentDto() {
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
