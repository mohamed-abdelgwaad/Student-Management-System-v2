package com.sms.studentSystem.service.impl;

import com.sms.studentSystem.dto.GenericResponse;
import com.sms.studentSystem.dto.QuizDto;
import com.sms.studentSystem.exception.NotFoundException;
import com.sms.studentSystem.model.Quiz;
import com.sms.studentSystem.service.QuizService;
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
public class QuizServiceImpl implements QuizService {

    @Autowired
    private HibernateTemplate hibernateTemplate;


    @Override
    public GenericResponse<?> getAllQuizzes() {
        try {
            List<Quiz> quizzes = this.hibernateTemplate.loadAll(Quiz.class);
            return GenericResponse.builder()
                    .data(quizzes.stream().map(quiz -> ModelMapperUtil.MAPPER().map(quiz, QuizDto.class)).collect(Collectors.toList()))
                    .responseMessage(ResponseMessage.SUCCESS)
                    .responseCode(ResponseMessage.SUCCESS.getCode())
                    .build();
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @Override
    public GenericResponse<?> getQuizById(Long id) {
        try {
            Quiz quiz = this.hibernateTemplate.get(Quiz.class, id);
            if (quiz == null)
                throw new NotFoundException("No Quizzes Found with this ID");
            return GenericResponse.builder()
                    .data(ModelMapperUtil.MAPPER().map(quiz, QuizDto.class))
                    .responseMessage(ResponseMessage.SUCCESS)
                    .responseCode(ResponseMessage.SUCCESS.getCode())
                    .build();

        } catch (Exception e) {
            return handleException(e);
        }
    }

    @Override
    @Transactional
    public GenericResponse<?> addQuiz(QuizDto quizDto) {
        try {
            Quiz quiz = ModelMapperUtil.MAPPER().map(quizDto, Quiz.class);
            this.hibernateTemplate.save(quiz);
            return GenericResponse.builder()
                    .data(quiz)
                    .responseMessage(ResponseMessage.SUCCESS)
                    .responseCode(ResponseMessage.SUCCESS.getCode())
                    .build();
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @Override
    @Transactional
    public GenericResponse<?> updateQuiz(QuizDto quizDto) {
        try {
            Quiz quiz = ModelMapperUtil.MAPPER().map(quizDto, Quiz.class);
            this.hibernateTemplate.update(quiz);
            return GenericResponse.builder()
                    .data(quiz)
                    .responseMessage(ResponseMessage.SUCCESS)
                    .responseCode(ResponseMessage.SUCCESS.getCode())
                    .build();
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @Override
    @Transactional
    public GenericResponse<?> deleteQuiz(Long id) {
        try {
            Quiz quiz = this.hibernateTemplate.get(Quiz.class, id);
            if (quiz == null)
                throw new NotFoundException("No Quizzes Found with this ID");
            else {
                this.hibernateTemplate.delete(quiz);
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
