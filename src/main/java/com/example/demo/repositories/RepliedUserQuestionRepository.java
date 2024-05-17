package com.example.demo.repositories;

import com.example.demo.entities.RepliedUserQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepliedUserQuestionRepository extends JpaRepository<RepliedUserQuestion, Long> {
}
