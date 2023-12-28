package com.sms.studentSystem.service.impl;

import com.sms.studentSystem.dto.GenericResponse;
import com.sms.studentSystem.dto.TeacherDto;
import com.sms.studentSystem.exception.NotFoundException;
import com.sms.studentSystem.model.Teacher;
import com.sms.studentSystem.service.TeacherService;
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
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public GenericResponse<?> getAllTeachers() {
        try {
            List<Teacher> teachers = this.hibernateTemplate.loadAll(Teacher.class);
            return GenericResponse.builder()
                    .data(teachers.stream().map(teacher -> ModelMapperUtil.MAPPER().map(teacher, TeacherDto.class)).collect(Collectors.toList()))
                    .responseMessage(ResponseMessage.SUCCESS)
                    .responseCode(ResponseMessage.SUCCESS.getCode())
                    .build();
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @Override
    public GenericResponse<?> getTeacherById(Long id) {
        try {
            Teacher teacher = this.hibernateTemplate.get(Teacher.class, id);
            if (teacher == null)
                throw new NotFoundException("No Teachers Found with this ID");
            return GenericResponse.builder()
                    .data(ModelMapperUtil.MAPPER().map(teacher, TeacherDto.class))
                    .responseMessage(ResponseMessage.SUCCESS)
                    .responseCode(ResponseMessage.SUCCESS.getCode())
                    .build();

        } catch (Exception e) {
            return handleException(e);
        }
    }

    @Override
    @Transactional
    public GenericResponse<?> addTeacher(TeacherDto teacherDto) {
        try {
            Teacher teacher = ModelMapperUtil.MAPPER().map(teacherDto, Teacher.class);
            this.hibernateTemplate.save(teacher);
            return GenericResponse.builder()
                    .data(teacher)
                    .responseMessage(ResponseMessage.SUCCESS)
                    .responseCode(ResponseMessage.SUCCESS.getCode())
                    .build();
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @Override
    @Transactional
    public GenericResponse<?> updateTeacher(TeacherDto teacherDto) {
        try {
            Teacher teacher = ModelMapperUtil.MAPPER().map(teacherDto, Teacher.class);
            this.hibernateTemplate.update(teacher);
            return GenericResponse.builder()
                    .data(teacher)
                    .responseMessage(ResponseMessage.SUCCESS)
                    .responseCode(ResponseMessage.SUCCESS.getCode())
                    .build();
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @Override
    @Transactional
    public GenericResponse<?> deleteTeacher(Long id) {
        try {
            Teacher teacher = this.hibernateTemplate.get(Teacher.class, id);
            if (teacher == null)
                throw new NotFoundException("No Teachers Found with this ID");
            else {
                this.hibernateTemplate.delete(teacher);
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
