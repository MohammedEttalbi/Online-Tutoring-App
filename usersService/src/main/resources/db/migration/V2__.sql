CREATE TABLE qualification
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    title       VARCHAR(180)          NOT NULL,
    institution VARCHAR(180)          NULL,
    tutor_id    BIGINT                NOT NULL,
    CONSTRAINT pk_qualification PRIMARY KEY (id)
);

CREATE TABLE subject
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    name          VARCHAR(120)          NOT NULL,
    `description` VARCHAR(1000)         NULL,
    CONSTRAINT pk_subject PRIMARY KEY (id)
);

CREATE TABLE user
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    type       VARCHAR(4)            NULL,
    first_name VARCHAR(80)           NOT NULL,
    last_name  VARCHAR(80)           NOT NULL,
    email      VARCHAR(180)          NOT NULL,
    `role`     VARCHAR(255)          NULL,
    level      VARCHAR(60)           NULL,
    bio        VARCHAR(2000)         NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

CREATE TABLE user_subject
(
    subject_id BIGINT NOT NULL,
    user_id    BIGINT NOT NULL,
    CONSTRAINT pk_user_subject PRIMARY KEY (subject_id, user_id)
);

ALTER TABLE qualification
    ADD CONSTRAINT FK_QUALIFICATION_ON_TUTOR FOREIGN KEY (tutor_id) REFERENCES user (id);

ALTER TABLE user_subject
    ADD CONSTRAINT fk_usesub_on_subject FOREIGN KEY (subject_id) REFERENCES subject (id);

ALTER TABLE user_subject
    ADD CONSTRAINT fk_usesub_on_user FOREIGN KEY (user_id) REFERENCES user (id);