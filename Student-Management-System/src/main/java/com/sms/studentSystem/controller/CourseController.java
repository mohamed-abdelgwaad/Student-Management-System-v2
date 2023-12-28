package com.sms.studentSystem.controller;

import com.sms.studentSystem.dto.CourseDto;
import com.sms.studentSystem.dto.GenericResponse;
import com.sms.studentSystem.service.CourseService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private CourseService courseService;

    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    @RequestMapping()
    public ResponseEntity<GenericResponse<?>> getAllCourses() {
        return ResponseEntity.ok(this.courseService.getAllCourses());
    }

    @RequestMapping("/{id}")
    public ResponseEntity<GenericResponse<?>> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(this.courseService.getCourseById(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<GenericResponse<?>> addCourse(@RequestBody CourseDto courseDto) {
        return ResponseEntity.status(HttpStatus.OK).body(this.courseService.addCourse(courseDto));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<GenericResponse<?>> updateCourse(@RequestBody CourseDto courseDto) {
        return ResponseEntity.ok(this.courseService.updateCourse(courseDto));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<GenericResponse<?>> deleteCourse(@PathVariable Long id) {
        return ResponseEntity.ok(this.courseService.deleteCourse(id));
    }
}
