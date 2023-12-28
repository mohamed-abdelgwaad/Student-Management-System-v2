package com.sms.studentSystem.controller;

import com.sms.studentSystem.dto.GenericResponse;
import com.sms.studentSystem.dto.TeacherDto;
import com.sms.studentSystem.service.TeacherService;
import com.sms.studentSystem.service.impl.TeacherServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private TeacherService teacherService;

    public void setTeacherService(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @RequestMapping()
    public ResponseEntity<GenericResponse<?>> getAllTeachers() {
        return ResponseEntity.ok(this.teacherService.getAllTeachers());
    }

    @RequestMapping("/{id}")
    public ResponseEntity<GenericResponse<?>> getTeacherById(@PathVariable Long id) {
        return ResponseEntity.ok(this.teacherService.getTeacherById(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<GenericResponse<?>> addTeacher(@RequestBody TeacherDto teacherDto) {
        return ResponseEntity.status(HttpStatus.OK).body(this.teacherService.addTeacher(teacherDto));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<GenericResponse<?>> updateTeacher(@RequestBody TeacherDto teacher) {
        return ResponseEntity.ok(this.teacherService.updateTeacher(teacher));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<GenericResponse<?>> deleteTeacher(@PathVariable Long id) {
        return ResponseEntity.ok(this.teacherService.deleteTeacher(id));
    }
}
