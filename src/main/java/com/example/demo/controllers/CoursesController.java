package com.example.demo.controllers;

import com.example.demo.controllers.request.CourseRequest;
import com.example.demo.controllers.request.QuestionRequest;
import com.example.demo.controllers.response.CourseResponse;
import com.example.demo.controllers.response.QuestionResponse;

import com.example.demo.repositories.QuestionRepository;
import com.example.demo.repositories.RepliedUserQuestionRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Page<QuestionResponse>> getQuestions(
            @PathVariable UUID courseId,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page
    ) throws Exception {
        return ResponseEntity.ok(courseService.getQuestions(courseId, page));
    }
}
