package com.sms.studentSystem.service;

import com.sms.studentSystem.dto.GenericResponse;
import com.sms.studentSystem.dto.QuizDto;

public interface QuizService {

    GenericResponse<?> getAllQuizzes();

    GenericResponse<?> getQuizById(Long id);

    GenericResponse<?> addQuiz(QuizDto quizDto);

    GenericResponse<?> updateQuiz(QuizDto quizDto);

    GenericResponse<?> deleteQuiz(Long id);
}
