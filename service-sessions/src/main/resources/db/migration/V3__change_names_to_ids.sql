-- Migration to change from names to IDs for better data integrity

-- Change student_name to student_id in booking table
ALTER TABLE booking DROP COLUMN student_name;
ALTER TABLE booking ADD COLUMN student_id BIGINT NOT NULL;

-- Change tutor_name to tutor_id in session table
ALTER TABLE session DROP COLUMN tutor_name;
ALTER TABLE session ADD COLUMN tutor_id BIGINT NOT NULL;
