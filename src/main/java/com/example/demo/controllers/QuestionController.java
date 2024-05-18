package com.example.demo.controllers;

import com.example.demo.controllers.request.ReplyQuestionRequest;
import com.example.demo.entities.Alternative;
import com.example.demo.entities.Question;
import com.example.demo.entities.RepliedUserQuestion;
import com.example.demo.services.QuestionService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping("{questionId}/reply")
    RepliedUserQuestion replyQuestion(
            @PathVariable UUID questionId,
            @RequestBody ReplyQuestionRequest replyQuestionRequest
    ) throws Exception {
        return questionService.replyQuestion(questionId, replyQuestionRequest);
    }
}
