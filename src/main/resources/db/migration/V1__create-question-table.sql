CREATE TABLE tb_courses (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT
);

CREATE TABLE tb_questions (
    id UUID PRIMARY KEY,
    text TEXT NOT NULL,
    course_id UUID,
    FOREIGN KEY (course_id) REFERENCES tb_courses(id)
);

CREATE TABLE tb_questions_alternatives (
    question_id UUID,
    alternative_id TEXT,
    text_alternative TEXT,
    is_correct BOOLEAN,
    PRIMARY KEY (question_id, text_alternative),
    FOREIGN KEY (question_id) REFERENCES tb_questions(id)
);