package com.example.demo.services;

import com.example.demo.controllers.request.ReplyQuestionRequest;
import com.example.demo.controllers.response.QuestionResponse;
import com.example.demo.entities.Alternative;
import com.example.demo.entities.Question;
import com.example.demo.entities.RepliedUserQuestion;
import com.example.demo.entities.User;
import com.example.demo.entities.enums.QuestionType;
import com.example.demo.repositories.QuestionRepository;
import com.example.demo.repositories.RepliedUserQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final ModelMapper mapper;
    private final QuestionRepository questionRepository;
    private final RepliedUserQuestionRepository repliedUserQuestionRepository;
    private final UserService userService;

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

    public Page<Question> getQuestions(UUID courseId, Pageable page) {
        return questionRepository.findQuestionByCourseId(courseId, page);
    }

    public Optional<Question> getQuestionById(UUID questionId) {
        return questionRepository.findById(questionId);
    }

    public RepliedUserQuestion replyQuestion(UUID questionId, ReplyQuestionRequest replyQuestionRequest) throws Exception {
        Question question = getQuestionById(questionId).orElseThrow(() -> new Exception("Id do curso não existe"));
        User user = userService.getUserById(replyQuestionRequest.getUserId()).orElseThrow(() -> new Exception("Id do usuário não existe"));
        List<Alternative> correctAlternatives = question.getAlternatives().stream().filter(Alternative::getIsCorrect).toList();
        boolean isCorrect = correctAlternatives.stream().anyMatch(el -> Objects.equals(el.getId(), replyQuestionRequest.getAlternativeId()));
        RepliedUserQuestion repliedUserQuestion = new RepliedUserQuestion();
        repliedUserQuestion.setQuestion(question);
        repliedUserQuestion.setUser(user);
        repliedUserQuestion.setCorrect(isCorrect);
        return repliedUserQuestionRepository.save(repliedUserQuestion);
    }
}
