package com.example.demo.controllers.response;

import com.example.demo.entities.Alternative;
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
        private List<Alternative> alternatives;
}
