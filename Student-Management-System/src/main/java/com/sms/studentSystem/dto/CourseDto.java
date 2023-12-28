package com.sms.studentSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.sms.studentSystem.model.Quiz;
import com.sms.studentSystem.model.Student;
import com.sms.studentSystem.model.Teacher;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseDto {
    private Long courseId;
    private String courseName;
//    private List<Student> students;
//    private List<Teacher> teachers;
//    private List<Quiz> quizzes;

}
