CREATE TABLE booking
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    date_time  datetime              NOT NULL,
    session_id BIGINT                NOT NULL,
    CONSTRAINT pk_booking PRIMARY KEY (id)
);

CREATE TABLE material
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(50)           NOT NULL,
    type VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_material PRIMARY KEY (id)
);

CREATE TABLE schedule
(
    id        BIGINT AUTO_INCREMENT NOT NULL,
    date_time datetime              NOT NULL,
    CONSTRAINT pk_schedule PRIMARY KEY (id)
);

CREATE TABLE session
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    name        VARCHAR(50)           NOT NULL,
    duration    DOUBLE                NOT NULL,
    schedule_id BIGINT                NOT NULL,
    CONSTRAINT pk_session PRIMARY KEY (id)
);

CREATE TABLE session_materials
(
    material_id BIGINT NOT NULL,
    session_id  BIGINT NOT NULL
);

ALTER TABLE material
    ADD CONSTRAINT uc_material_name UNIQUE (name);

ALTER TABLE session
    ADD CONSTRAINT uc_session_name UNIQUE (name);

ALTER TABLE session
    ADD CONSTRAINT uc_session_schedule UNIQUE (schedule_id);

ALTER TABLE booking
    ADD CONSTRAINT FK_BOOKING_ON_SESSION FOREIGN KEY (session_id) REFERENCES session (id);

ALTER TABLE session
    ADD CONSTRAINT FK_SESSION_ON_SCHEDULE FOREIGN KEY (schedule_id) REFERENCES schedule (id);

ALTER TABLE session_materials
    ADD CONSTRAINT fk_sesmat_on_material FOREIGN KEY (material_id) REFERENCES material (id);

ALTER TABLE session_materials
    ADD CONSTRAINT fk_sesmat_on_session FOREIGN KEY (session_id) REFERENCES session (id);