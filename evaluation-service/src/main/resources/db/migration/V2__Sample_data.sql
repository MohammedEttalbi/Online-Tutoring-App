-- Sample data for evaluation-service
-- V2__Sample_data.sql

-- Insert Reviews (references studentId and tutorId from usersService)
INSERT INTO reviews (id, student_id, tutor_id, comment, created_at) VALUES
(1, 1, 1, 'Excellent tutor! Very patient and explains concepts clearly.', '2025-01-15 10:30:00'),
(2, 2, 1, 'Good session, learned a lot about Java programming.', '2025-01-16 14:00:00'),
(3, 1, 2, 'Great teaching style, very engaging lessons.', '2025-01-17 09:00:00'),
(4, 3, 2, 'Helped me understand complex mathematics problems.', '2025-01-18 11:00:00'),
(5, 2, 3, 'Fantastic English tutor, improved my writing skills.', '2025-01-19 15:00:00');

-- Insert Ratings (linked to reviews)
INSERT INTO ratings (id, stars, review_id) VALUES
(1, 5, 1),
(2, 4, 2),
(3, 5, 3),
(4, 4, 4),
(5, 5, 5);

-- Insert Progress records
INSERT INTO progress (id, student_id, tutor_id, score, level, updated_at) VALUES
(1, 1, 1, 85, 'Intermediate', '2025-01-20 10:00:00'),
(2, 2, 1, 72, 'Intermediate', '2025-01-20 11:00:00'),
(3, 1, 2, 90, 'Advanced', '2025-01-20 12:00:00'),
(4, 3, 2, 65, 'Beginner', '2025-01-20 13:00:00'),
(5, 2, 3, 78, 'Intermediate', '2025-01-20 14:00:00');

-- Insert Feedbacks (linked to reviews)
INSERT INTO feedbacks (id, message, created_at, review_id) VALUES
(1, 'Thank you for your kind words! Looking forward to our next session.', '2025-01-15 12:00:00', 1),
(2, 'Glad you enjoyed the Java lesson. Keep practicing!', '2025-01-16 16:00:00', 2),
(3, 'Thank you! Let me know if you need more help.', '2025-01-17 11:00:00', 3),
(4, 'Great progress! Mathematics requires patience.', '2025-01-18 13:00:00', 4),
(5, 'Thank you for the feedback! Your writing has improved significantly.', '2025-01-19 17:00:00', 5);
