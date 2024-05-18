package com.example.demo.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@Embeddable
public class Alternative {

    @Column(name = "alternative_id")
    private UUID id;

    private String textAlternative;
    private Boolean isCorrect;

    public Alternative() {
        this.id = UUID.randomUUID();
    }
}
