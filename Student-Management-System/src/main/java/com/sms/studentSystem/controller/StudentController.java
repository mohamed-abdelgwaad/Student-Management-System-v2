package com.sms.studentSystem.controller;

import com.sms.studentSystem.dto.GenericResponse;
import com.sms.studentSystem.dto.StudentDto;
import com.sms.studentSystem.model.Student;
import com.sms.studentSystem.service.StudentService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {

    private StudentService studentService;

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<GenericResponse<?>> getAllStudents() {
        return ResponseEntity.ok(this.studentService.getAllStudents());
    }

    @RequestMapping(path = "/{studentId}")
    public ResponseEntity<GenericResponse<?>> getStudentById(@PathVariable(value = "studentId") Long id) {
        return ResponseEntity.ok(this.studentService.getStudentById(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<GenericResponse<?>> addStudent(@RequestBody StudentDto student) {
        return ResponseEntity.status(HttpStatus.OK).body(this.studentService.addStudent(student));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<GenericResponse<?>> updateStudent(@RequestBody Student student) {
        return ResponseEntity.ok(this.studentService.updateStudent(student));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<GenericResponse<?>> deleteStudent(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(this.studentService.deleteStudentById(id));
    }

}
