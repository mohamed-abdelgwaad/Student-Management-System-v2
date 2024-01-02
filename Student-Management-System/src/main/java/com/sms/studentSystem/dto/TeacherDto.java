package com.sms.studentSystem.dto;

import com.sms.studentSystem.model.Course;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeacherDto {
    private Long teacherId;
    private String teacherName;
    private String age;
    private String email;

    private Course course;

	public TeacherDto(Long teacherId, String teacherName, String age, String email, Course course) {
		super();
		this.teacherId = teacherId;
		this.teacherName = teacherName;
		this.age = age;
		this.email = email;
		this.course = course;
	}

	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
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
