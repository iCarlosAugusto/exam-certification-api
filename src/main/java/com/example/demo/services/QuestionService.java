package com.example.demo.services;

import com.example.demo.controllers.response.QuestionResponse;
import com.example.demo.entities.Alternative;
import com.example.demo.entities.Question;
import com.example.demo.entities.enums.QuestionType;
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

    boolean validateQuestionIsValid(Question question){
        if(question.getQuestionType() == QuestionType.multipleChoices) {
            return question.getAlternatives().stream().filter(Alternative::getIsCorrect).toList().size() == 2;
        }
        if(question.getQuestionType() == QuestionType.singleChoice) {
            return question.getAlternatives().stream().filter(Alternative::getIsCorrect).toList().size() == 1;
        }

        return false;
    }

    List<Question> getQuestions(UUID courseId) {
        return questionRepository.findQuestionByCourseId(courseId);
    }
}
