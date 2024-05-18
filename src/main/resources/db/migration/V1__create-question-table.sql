CREATE TABLE tb_courses (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT
);

CREATE TABLE tb_questions (
    id UUID PRIMARY KEY,
    question_type TEXT,
    text TEXT NOT NULL,
    course_id UUID,
    FOREIGN KEY (course_id) REFERENCES tb_courses(id)
);

CREATE TABLE tb_questions_alternatives (
    question_id UUID,
    alternative_id UUID,
    text_alternative TEXT,
    is_correct BOOLEAN,
    PRIMARY KEY (question_id, text_alternative),
    FOREIGN KEY (question_id) REFERENCES tb_questions(id)
);

CREATE TABLE tb_users (
    id UUID PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255)
);


CREATE TABLE tb_replied_user_question (
    id UUID PRIMARY KEY,
    user_id UUID,
    question_id UUID,
    is_correct BOOLEAN,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES tb_users(id),
    CONSTRAINT fk_question FOREIGN KEY (question_id) REFERENCES tb_questions(id)
);
