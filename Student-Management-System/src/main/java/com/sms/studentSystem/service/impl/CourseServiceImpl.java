package com.sms.studentSystem.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sms.studentSystem.dto.CourseDto;
import com.sms.studentSystem.dto.GenericResponse;
import com.sms.studentSystem.exception.NotFoundException;
import com.sms.studentSystem.model.Course;
import com.sms.studentSystem.service.CourseService;
import com.sms.studentSystem.util.ModelMapperUtil;
import com.sms.studentSystem.util.enums.ErrorMessage;
import com.sms.studentSystem.util.enums.ResponseMessage;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public GenericResponse<CourseDto> getAllCourses() {
        try {
            List<Course> courses = this.hibernateTemplate.loadAll(Course.class);
            List<CourseDto> courseDtos = courses.stream().map(course -> ModelMapperUtil.MAPPER().map(course, CourseDto.class)).collect(Collectors.toList());
           GenericResponse<CourseDto> response= new  GenericResponse.Builder<CourseDto>()
                    .data(courseDtos)
                    .responseMessage(ResponseMessage.SUCCESS)
                    .responseCode(ResponseMessage.SUCCESS.getCode())
                    .build();
           return response;
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @Override
    public GenericResponse<CourseDto> getCourseById(Long id) {
        try {
            Course course = this.hibernateTemplate.get(Course.class, id);
            if (course == null)
                throw new NotFoundException("No Courses Found with this ID");
            List<CourseDto> courseDtos =  new ArrayList<CourseDto>();
            CourseDto courseDto=ModelMapperUtil.MAPPER().map(course, CourseDto.class);
            courseDtos.add(courseDto);
            GenericResponse<CourseDto> response= new GenericResponse.Builder<CourseDto>()
                    .data(courseDtos)
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
    public GenericResponse<Course> addCourse(CourseDto courseDto) {
        try {
            Course course = ModelMapperUtil.MAPPER().map(courseDto, Course.class);
            List<Course> courses =  new ArrayList<Course>();
            courses.add(course);
            this.hibernateTemplate.save(course);
            GenericResponse<Course> response= new GenericResponse.Builder<Course>()
                    .data(courses)
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
    public GenericResponse<Course> updateCourse(CourseDto courseDto) {
        try {
            Course course = ModelMapperUtil.MAPPER().map(courseDto, Course.class);
            this.hibernateTemplate.update(course);
            List<Course> courses =  new ArrayList<Course>();
            courses.add(course);
            GenericResponse<Course> response= new GenericResponse.Builder<Course>()
                    .data(courses)
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
    public GenericResponse<Course> deleteCourse(Long id) {
        try {
            Course course = this.hibernateTemplate.get(Course.class, id);
            if (course == null)
                throw new NotFoundException("No Courses Found with this ID");
            else {
                this.hibernateTemplate.delete(course);
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
