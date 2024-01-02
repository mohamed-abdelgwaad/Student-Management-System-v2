package com.sms.studentSystem.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sms.studentSystem.dto.GenericResponse;
import com.sms.studentSystem.dto.TeacherDto;
import com.sms.studentSystem.exception.NotFoundException;
import com.sms.studentSystem.model.Teacher;
import com.sms.studentSystem.service.TeacherService;
import com.sms.studentSystem.util.ModelMapperUtil;
import com.sms.studentSystem.util.enums.ErrorMessage;
import com.sms.studentSystem.util.enums.ResponseMessage;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public GenericResponse<TeacherDto> getAllTeachers() {
        try {
            List<Teacher> teachers = this.hibernateTemplate.loadAll(Teacher.class);
            List<TeacherDto> teacherDtos =teachers.stream().map(teacher -> ModelMapperUtil.MAPPER().map(teacher, TeacherDto.class)).collect(Collectors.toList());
            GenericResponse<TeacherDto> response= new  GenericResponse.Builder<TeacherDto>()
                    .data(teacherDtos)
                    .responseMessage(ResponseMessage.SUCCESS)
                    .responseCode(ResponseMessage.SUCCESS.getCode())
                    .build();
            return response;
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @Override
    public GenericResponse<TeacherDto> getTeacherById(Long id) {
        try {
            Teacher teacher = this.hibernateTemplate.get(Teacher.class, id);
            if (teacher == null)
                throw new NotFoundException("No Teachers Found with this ID");
            List<TeacherDto> teacherDtos =  new ArrayList<TeacherDto>();
            TeacherDto teacherDto=ModelMapperUtil.MAPPER().map(teacher, TeacherDto.class);
            teacherDtos.add(teacherDto);
            GenericResponse<TeacherDto> response= new GenericResponse.Builder<TeacherDto>()
                    .data(teacherDtos)
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
    public GenericResponse<Teacher> addTeacher(TeacherDto teacherDto) {
        try {
            Teacher teacher = ModelMapperUtil.MAPPER().map(teacherDto, Teacher.class);
            List<Teacher> teachers =  new ArrayList<Teacher>();
            teachers.add(teacher);
            this.hibernateTemplate.save(teacher);
            GenericResponse<Teacher> response= new GenericResponse.Builder<Teacher>()
                    .data(teachers)
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
    public GenericResponse<Teacher> updateTeacher(TeacherDto teacherDto) {
        try {
            Teacher teacher = ModelMapperUtil.MAPPER().map(teacherDto, Teacher.class);
            List<Teacher> teachers =  new ArrayList<Teacher>();
            teachers.add(teacher);
            this.hibernateTemplate.update(teacher);
            GenericResponse<Teacher> response= new GenericResponse.Builder<Teacher>()
                    .data(teachers)
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
    public GenericResponse<Teacher> deleteTeacher(Long id) {
        try {
            Teacher teacher = this.hibernateTemplate.get(Teacher.class, id);
            if (teacher == null)
                throw new NotFoundException("No Teachers Found with this ID");
            else {
                this.hibernateTemplate.delete(teacher);
                GenericResponse<Teacher> response= new GenericResponse.Builder<Teacher>()
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
