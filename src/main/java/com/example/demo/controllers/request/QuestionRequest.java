package com.example.demo.controllers.request;

import com.example.demo.entities.Alternative;
import com.example.demo.entities.enums.QuestionType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionRequest {

    @NotEmpty
    private String text;

    private QuestionType questionType;

    @NotEmpty
    private List<Alternative> alternatives;

}
