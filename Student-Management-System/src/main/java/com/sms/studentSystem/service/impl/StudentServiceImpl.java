package com.sms.studentSystem.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sms.studentSystem.dto.GenericResponse;
import com.sms.studentSystem.dto.StudentDto;
import com.sms.studentSystem.exception.NotFoundException;
import com.sms.studentSystem.model.Course;
import com.sms.studentSystem.model.Student;
import com.sms.studentSystem.service.StudentService;
import com.sms.studentSystem.util.ModelMapperUtil;
import com.sms.studentSystem.util.enums.ErrorMessage;
import com.sms.studentSystem.util.enums.ResponseMessage;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public GenericResponse<StudentDto> getAllStudents() {
        try {
            List<Student> students = this.hibernateTemplate.loadAll(Student.class);
            List<StudentDto> studentDtos =students.stream().map(student -> ModelMapperUtil.MAPPER().map(student, StudentDto.class)).collect(Collectors.toList());
            GenericResponse<StudentDto> response= new  GenericResponse.Builder<StudentDto>()
                    .data(studentDtos)
                    .responseMessage(ResponseMessage.SUCCESS)
                    .responseCode(ResponseMessage.SUCCESS.getCode())
                    .build();
            return response;
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @Override
    public GenericResponse<StudentDto> getStudentById(Long id) {
        try {
            Student student = this.hibernateTemplate.get(Student.class, id);
            if (student == null)
                throw new NotFoundException("No Students Found with this ID");
            List<StudentDto> studentDtos =  new ArrayList<StudentDto>();
            StudentDto studentDto=ModelMapperUtil.MAPPER().map(student, StudentDto.class);
            studentDtos.add(studentDto);
            GenericResponse<StudentDto> response= new GenericResponse.Builder<StudentDto>()
                    .data(studentDtos)
                    .responseMessage(ResponseMessage.SUCCESS)
                    .responseCode(ResponseMessage.SUCCESS.getCode())
                    .build();
            return response;
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @Override
    @Transactional
    public GenericResponse<Student> addStudent(StudentDto studentDto) {
        try {
            Student student = ModelMapperUtil.MAPPER().map(studentDto, Student.class);
            List<Student> students =  new ArrayList<Student>();
            students.add(student);
            this.hibernateTemplate.save(student);
            GenericResponse<Student> response= new GenericResponse.Builder<Student>()
                    .data(students)
                    .responseMessage(ResponseMessage.SUCCESS)
                    .responseCode(ResponseMessage.SUCCESS.getCode())
                    .build();
            return response;
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @Override
    @Transactional
    public GenericResponse<Student> updateStudent(Student student) {
        try {
            this.hibernateTemplate.update(student);
            List<Student> students =  new ArrayList<Student>();
            students.add(student);
            GenericResponse<Student> response= new GenericResponse.Builder<Student>()
                    .data(students)
                    .responseMessage(ResponseMessage.SUCCESS)
                    .responseCode(ResponseMessage.SUCCESS.getCode())
                    .build();
            return response;
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @Override
    @Transactional
    public GenericResponse<Course> deleteStudentById(Long id) {
        try {
            Student student = this.hibernateTemplate.get(Student.class, id);
            if (student == null)
                throw new NotFoundException("No Students Found with this ID");
            else {
                this.hibernateTemplate.delete(student);
                GenericResponse<Course> response= new GenericResponse.Builder<Course>()
                        .data(null)
                        .responseMessage(ResponseMessage.SUCCESS)
                        .responseCode(ResponseMessage.SUCCESS.getCode())
                        .build();
                return response;
            }

        } catch (Exception e) {
            return handleException(e);
        }
    }

    private <T> GenericResponse<T> handleException(Exception e) {
    	GenericResponse<T> response= new GenericResponse.Builder<T>()
                .data(null)
                .responseMessage(ResponseMessage.FAIL)
                .responseCode(ResponseMessage.FAIL.getCode())
                .errorMessage(ErrorMessage.INVALID_CREDENTIALS.getMessage())
                .build();
    	return response;
    }
}
