package com.sms.studentSystem.service;

import com.sms.studentSystem.dto.GenericResponse;
import com.sms.studentSystem.dto.StudentDto;
import com.sms.studentSystem.model.Student;

public interface StudentService {

    GenericResponse<?> getAllStudents();

    GenericResponse<?> getStudentById(Long id);

    GenericResponse<?> addStudent(StudentDto studentDto);

    GenericResponse<?> updateStudent(Student student);

    GenericResponse<?> deleteStudentById(Long id);
}
