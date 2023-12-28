package com.sms.studentSystem.service;

import com.sms.studentSystem.dto.GenericResponse;
import com.sms.studentSystem.dto.TeacherDto;

public interface TeacherService {

    GenericResponse<?> getAllTeachers();
    GenericResponse<?> getTeacherById(Long id);
    GenericResponse<?> addTeacher(TeacherDto teacherDto);
    GenericResponse<?> updateTeacher(TeacherDto teacherDto);
    GenericResponse<?> deleteTeacher(Long id);
}
