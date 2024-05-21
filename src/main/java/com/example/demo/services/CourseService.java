package com.example.demo.services;

import com.example.demo.controllers.request.CourseRequest;
import com.example.demo.controllers.request.QuestionRequest;
import com.example.demo.controllers.response.CourseResponse;
import com.example.demo.controllers.response.QuestionResponse;
import com.example.demo.entities.Course;
import com.example.demo.entities.Question;
import com.example.demo.repositories.CoursesRepository;
import com.example.demo.repositories.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CoursesRepository coursesRepository;
    private final QuestionRepository questionRepository;
    private final QuestionService questionService;
    private final ModelMapper mapper;
    public CourseResponse createCourse(CourseRequest courseRequest) {
        Course course = mapper.map(courseRequest, Course.class);
        Course newCourse = coursesRepository.save(course);
        return mapper.map(newCourse, CourseResponse.class);
    }

    public List<CourseResponse> listCourses() {
        List<Course> courses = coursesRepository.findAll();

                //.toList();
        return courses.stream()
                .map(el -> mapper.map(el, CourseResponse.class))
                .toList();
    }

    public Optional<Course> findCourseById(UUID id) {
        return coursesRepository.findById(id);
    }

    public QuestionResponse createQuestion(UUID courseId, QuestionRequest questionRequest) throws Exception {
        Course course = this.findCourseById(courseId).orElseThrow(() -> new Exception("Id do curso não existe"));
        Question question = mapper.map(questionRequest, Question.class);
        boolean isValidQuestion = questionService.validateQuestionIsValid(question);
        if(!isValidQuestion) {
            throw new Exception("Questão inválida");
        }
        question.setCourse(course);
        Question createdQuestion = questionService.createQuestion(question);
        return mapper.map(createdQuestion, QuestionResponse.class);
    }

    public Page<QuestionResponse> getQuestions(UUID courseId, int page) throws Exception {
        this.findCourseById(courseId).orElseThrow(() -> new Exception("Id do curso não existe"));
        Pageable pageable = PageRequest.of(page, 10);
        return questionRepository.findQuestionByCourseId(courseId, pageable).map(el -> mapper.map(el, QuestionResponse.class));
    }
}
