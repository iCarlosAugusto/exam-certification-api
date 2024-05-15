package com.example.demo.controllers.response;

import com.example.demo.entities.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponse {
    private UUID id;
    private String name;
    private String description;
    private List<Question> questions;
}
