package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
@Table(name="tb_courses")
@Entity
public class Course {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private String description;
}
