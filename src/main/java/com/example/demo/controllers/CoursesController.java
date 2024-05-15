package com.example.demo.controllers;

import com.example.demo.controllers.request.CourseRequest;
import com.example.demo.controllers.request.QuestionRequest;
import com.example.demo.controllers.response.CourseResponse;
import com.example.demo.controllers.response.QuestionResponse;
import com.example.demo.entities.Course;
import com.example.demo.entities.Question;
import com.example.demo.services.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@RestController()
@RequestMapping("/courses")
public class CoursesController {

    private final CourseService courseService;
    private final ModelMapper mapper;
    @PostMapping()
    CourseResponse createCourse(@RequestBody CourseRequest courseRequest) {
        return courseService.createCourse(courseRequest);
    }

    @GetMapping()
    List<CourseResponse> listCourses() {
        return courseService.listCourses();
    }

    @PostMapping("/{courseId}/questions")
    QuestionResponse createQuestion(
            @PathVariable UUID courseId,
            @RequestBody @Valid QuestionRequest question
    ) throws Exception {
        return courseService.createQuestion(courseId, question);
    }

    @GetMapping("/{courseId}/questions")
    List<QuestionResponse> getQuestions(@PathVariable UUID courseId) throws Exception {
        return courseService.getQuestions(courseId);
    }
}
