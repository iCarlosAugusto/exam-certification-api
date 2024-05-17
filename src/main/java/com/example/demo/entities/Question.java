package com.example.demo.entities;

import com.example.demo.entities.enums.QuestionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="tb_questions")
@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private QuestionType questionType;

    private String text;

    @ManyToOne
    private Course course;

    @ElementCollection(targetClass = Alternative.class, fetch = FetchType.EAGER)
    @CollectionTable(
            name = "tb_questions_alternatives",
            joinColumns = {@JoinColumn(name = "question_id")}
    )
    private List<Alternative> alternatives;
}
