package com.example.demo.controllers.request;

import com.example.demo.entities.Alternative;
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

    @NotEmpty
    private List<Alternative> alternatives;

}
