package com.sms.studentSystem.service.impl;

import com.sms.studentSystem.dto.GenericResponse;
import com.sms.studentSystem.dto.StudentDto;
import com.sms.studentSystem.exception.NotFoundException;
import com.sms.studentSystem.model.Student;
import com.sms.studentSystem.service.StudentService;
import com.sms.studentSystem.util.ModelMapperUtil;
import com.sms.studentSystem.util.enums.ErrorMessage;
import com.sms.studentSystem.util.enums.ResponseMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public GenericResponse<?> getAllStudents() {
        try {
            List<Student> students = this.hibernateTemplate.loadAll(Student.class);
            return GenericResponse.builder()
                    .data(students.stream().map(student -> ModelMapperUtil.MAPPER().map(student, StudentDto.class)).collect(Collectors.toList()))
                    .responseMessage(ResponseMessage.SUCCESS)
                    .responseCode(ResponseMessage.SUCCESS.getCode())
                    .build();
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @Override
    public GenericResponse<?> getStudentById(Long id) {
        try {
            Student student = this.hibernateTemplate.get(Student.class, id);
            if (student == null)
                throw new NotFoundException("No Students Found with this ID");
            return GenericResponse.builder()
                    .data(ModelMapperUtil.MAPPER().map(student, StudentDto.class))
                    .responseMessage(ResponseMessage.SUCCESS)
                    .responseCode(ResponseMessage.SUCCESS.getCode())
                    .build();

        } catch (Exception e) {
            return handleException(e);
        }
    }

    @Override
    @Transactional
    public GenericResponse<?> addStudent(StudentDto studentDto) {
        try {
            Student student = ModelMapperUtil.MAPPER().map(studentDto, Student.class);
            this.hibernateTemplate.save(student);
            return GenericResponse.builder()
                    .data(student)
                    .responseMessage(ResponseMessage.SUCCESS)
                    .responseCode(ResponseMessage.SUCCESS.getCode())
                    .build();
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @Override
    @Transactional
    public GenericResponse<?> updateStudent(Student student) {
        try {
            this.hibernateTemplate.update(student);
            return GenericResponse.builder()
                    .data(student)
                    .responseMessage(ResponseMessage.SUCCESS)
                    .responseCode(ResponseMessage.SUCCESS.getCode())
                    .build();
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @Override
    @Transactional
    public GenericResponse<?> deleteStudentById(Long id) {
        try {
            Student student = this.hibernateTemplate.get(Student.class, id);
            if (student == null)
                throw new NotFoundException("No Students Found with this ID");
            else {
                this.hibernateTemplate.delete(student);
                return GenericResponse.builder()
                        .data(null)
                        .responseMessage(ResponseMessage.SUCCESS)
                        .responseCode(ResponseMessage.SUCCESS.getCode())
                        .build();
            }

        } catch (Exception e) {
            return handleException(e);
        }
    }

    private <T> GenericResponse<T> handleException(Exception e) {
        return GenericResponse.<T>builder()
                .data(null)
                .responseMessage(ResponseMessage.FAIL)
                .responseCode(ResponseMessage.FAIL.getCode())
                .errorMessage(ErrorMessage.INVALID_CREDENTIALS.getMessage())
                .build();
    }
}
