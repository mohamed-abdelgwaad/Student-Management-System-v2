 package com.sms.studentSystem.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sms.studentSystem.dto.GenericResponse;
import com.sms.studentSystem.dto.QuizDto;
import com.sms.studentSystem.exception.NotFoundException;
import com.sms.studentSystem.model.Quiz;
import com.sms.studentSystem.service.QuizService;
import com.sms.studentSystem.util.ModelMapperUtil;
import com.sms.studentSystem.util.enums.ErrorMessage;
import com.sms.studentSystem.util.enums.ResponseMessage;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private HibernateTemplate hibernateTemplate;


    @Override
    public GenericResponse<QuizDto> getAllQuizzes() {
        try {
            List<Quiz> quizzes = this.hibernateTemplate.loadAll(Quiz.class);
            
            List<QuizDto> QuizDtos = quizzes.stream().map(quiz -> ModelMapperUtil.MAPPER().map(quiz, QuizDto.class)).collect(Collectors.toList());
            GenericResponse<QuizDto> response= new   GenericResponse.Builder<QuizDto>()
                    .data(QuizDtos)
                    .responseMessage(ResponseMessage.SUCCESS)
                    .responseCode(ResponseMessage.SUCCESS.getCode())
                    .build();
            return response;
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @Override
    public GenericResponse<QuizDto> getQuizById(Long id) {
        try {
            Quiz quiz = this.hibernateTemplate.get(Quiz.class, id);
            if (quiz == null)
                throw new NotFoundException("No Quizzes Found with this ID");
            List<QuizDto> quizDtos =  new ArrayList<QuizDto>();
            QuizDto quizDto=ModelMapperUtil.MAPPER().map(quiz, QuizDto.class);
            quizDtos.add(quizDto);
            GenericResponse<QuizDto> response= new GenericResponse.Builder<QuizDto>()
                    .data(quizDtos)
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
    public GenericResponse<Quiz> addQuiz(QuizDto quizDto) {
        try {
            Quiz quiz = ModelMapperUtil.MAPPER().map(quizDto, Quiz.class);
            List<Quiz> quizs =  new ArrayList<Quiz>();
            quizs.add(quiz);
            this.hibernateTemplate.save(quiz);
            GenericResponse<Quiz> response= new GenericResponse.Builder<Quiz>()
                    .data(quizs)
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
    public GenericResponse<Quiz> updateQuiz(QuizDto quizDto) {
        try {
            Quiz quiz = ModelMapperUtil.MAPPER().map(quizDto, Quiz.class);
            this.hibernateTemplate.update(quiz);
            List<Quiz> quizs =  new ArrayList<Quiz>();
            quizs.add(quiz);
            GenericResponse<Quiz> response= new GenericResponse.Builder<Quiz>()
                    .data(quizs)
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
    public GenericResponse<Quiz> deleteQuiz(Long id) {
        try {
            Quiz quiz = this.hibernateTemplate.get(Quiz.class, id);
            if (quiz == null)
                throw new NotFoundException("No Quizzes Found with this ID");
            else {
                this.hibernateTemplate.delete(quiz);
                GenericResponse<Quiz> response= new GenericResponse.Builder<Quiz>()
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
