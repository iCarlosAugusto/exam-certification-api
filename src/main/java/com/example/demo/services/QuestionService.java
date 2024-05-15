package com.example.demo.services;

import com.example.demo.entities.Question;
import com.example.demo.repositories.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final ModelMapper mapper;
    private final QuestionRepository questionRepository;

    Question createQuestion(Question question) {
       return questionRepository.save(question);
    }

    List<Question> getQuestions(UUID courseId) {
        return questionRepository.findQuestionByCourseId(courseId);
    }
}
