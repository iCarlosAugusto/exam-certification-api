package com.example.demo.services;

import com.example.demo.controllers.request.CourseRequest;
import com.example.demo.controllers.request.QuestionRequest;
import com.example.demo.controllers.response.CourseResponse;
import com.example.demo.controllers.response.QuestionResponse;
import com.example.demo.entities.Course;
import com.example.demo.entities.Question;
import com.example.demo.repositories.CoursesRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CoursesRepository coursesRepository;
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
        question.setCourse(course);
        Question createdQuestion = questionService.createQuestion(question);
        return mapper.map(createdQuestion, QuestionResponse.class);
    }

    public List<QuestionResponse> getQuestions(UUID courseId) throws Exception {
        this.findCourseById(courseId).orElseThrow(() -> new Exception("Id do curso não existe"));
        List<Question> questions = questionService.getQuestions(courseId);
        return questions.stream().map(el -> mapper.map(el, QuestionResponse.class)).toList();
    }
}
