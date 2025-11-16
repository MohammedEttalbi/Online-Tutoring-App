CREATE TABLE feedback
(
    id        BIGINT AUTO_INCREMENT NOT NULL,
    comment   VARCHAR(255) NULL,
    date      date NULL,
    review_id BIGINT NULL,
    CONSTRAINT pk_feedback PRIMARY KEY (id)
);

CREATE TABLE progress
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    goal       VARCHAR(255) NULL,
    status     VARCHAR(255) NULL,
    updated_at date NULL,
    review_id  BIGINT NULL,
    CONSTRAINT pk_progress PRIMARY KEY (id)
);

CREATE TABLE rating
(
    id        BIGINT AUTO_INCREMENT NOT NULL,
    score     INT NOT NULL,
    criteria  VARCHAR(255) NULL,
    review_id BIGINT NULL,
    CONSTRAINT pk_rating PRIMARY KEY (id)
);

CREATE TABLE review
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    reviewer_name VARCHAR(255) NULL,
    subject       VARCHAR(255) NULL,
    `description` VARCHAR(255) NULL,
    review_date   date NULL,
    CONSTRAINT pk_review PRIMARY KEY (id)
);

ALTER TABLE rating
    ADD CONSTRAINT uc_rating_review UNIQUE (review_id);

ALTER TABLE feedback
    ADD CONSTRAINT FK_FEEDBACK_ON_REVIEW FOREIGN KEY (review_id) REFERENCES review (id);

ALTER TABLE progress
    ADD CONSTRAINT FK_PROGRESS_ON_REVIEW FOREIGN KEY (review_id) REFERENCES review (id);

ALTER TABLE rating
    ADD CONSTRAINT FK_RATING_ON_REVIEW FOREIGN KEY (review_id) REFERENCES review (id);