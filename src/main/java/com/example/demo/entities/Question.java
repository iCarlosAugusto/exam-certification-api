package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Table(name="tb_questions")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

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
