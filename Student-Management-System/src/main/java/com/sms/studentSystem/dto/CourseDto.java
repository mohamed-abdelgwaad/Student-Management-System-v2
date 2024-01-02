package com.sms.studentSystem.dto;

import java.util.List;

import com.sms.studentSystem.model.Quiz;
import com.sms.studentSystem.model.Student;
import com.sms.studentSystem.model.Teacher;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseDto {
    private Long courseId;
    private String courseName;
  private List<Student> students;
   private List<Teacher> teachers;
   private List<Quiz> quizzes;
public CourseDto(Long courseId, String courseName, List<Student> students, List<Teacher> teachers, List<Quiz> quizzes) {
	super();
	this.courseId = courseId;
	this.courseName = courseName;
	this.students = students;
	this.teachers = teachers;
	this.quizzes = quizzes;
}
public Long getCourseId() {
	return courseId;
}
public void setCourseId(Long courseId) {
	this.courseId = courseId;
}
public String getCourseName() {
	return courseName;
}
public void setCourseName(String courseName) {
	this.courseName = courseName;
}
public List<Student> getStudents() {
	return students;
}
public void setStudents(List<Student> students) {
	this.students = students;
}
public List<Teacher> getTeachers() {
	return teachers;
}
public void setTeachers(List<Teacher> teachers) {
	this.teachers = teachers;
}
public List<Quiz> getQuizzes() {
	return quizzes;
}
public void setQuizzes(List<Quiz> quizzes) {
	this.quizzes = quizzes;
}
   
   
   

}
