package com.example.demo.controllers.response;

import com.example.demo.entities.Alternative;
import com.example.demo.entities.enums.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionResponse {

        private UUID id;
        private String text;
        private QuestionType questionType;
        private List<Alternative> alternatives;
}
