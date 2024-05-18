package com.example.demo.controllers.request;

import com.example.demo.entities.Alternative;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReplyQuestionRequest {

    private UUID userId;
    private UUID alternativeId;
}
