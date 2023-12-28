package com.sms.studentSystem.controller;

import com.sms.studentSystem.dto.GenericResponse;
import com.sms.studentSystem.dto.QuizDto;
import com.sms.studentSystem.service.QuizService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quizzes")
public class QuizController {

    private QuizService quizService;

    public void setQuizService(QuizService quizService) {
        this.quizService = quizService;
    }

    @RequestMapping()
    public ResponseEntity<GenericResponse<?>> getAllQuizzes() {
        return ResponseEntity.ok(this.quizService.getAllQuizzes());
    }

    @RequestMapping("/{id}")
    public ResponseEntity<GenericResponse<?>> getQuizById(@PathVariable Long id) {
        return ResponseEntity.ok(this.quizService.getQuizById(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<GenericResponse<?>> addQuiz(@RequestBody QuizDto quizDto) {
        return ResponseEntity.status(HttpStatus.OK).body(this.quizService.addQuiz(quizDto));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<GenericResponse<?>> updateQuiz(@RequestBody QuizDto quizDto) {
        return ResponseEntity.ok(this.quizService.updateQuiz(quizDto));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<GenericResponse<?>> deleteQuiz(@PathVariable Long id) {
        return ResponseEntity.ok(this.quizService.deleteQuiz(id));
    }
}
