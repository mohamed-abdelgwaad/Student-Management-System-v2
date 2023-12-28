package com.sms.studentSystem.service;

import com.sms.studentSystem.dto.CourseDto;
import com.sms.studentSystem.dto.GenericResponse;

public interface CourseService {

    GenericResponse<?> getAllCourses();

    GenericResponse<?> getCourseById(Long id);

    GenericResponse<?> addCourse(CourseDto courseDto);

    GenericResponse<?> updateCourse(CourseDto courseDto);

    GenericResponse<?> deleteCourse(Long id);
}
