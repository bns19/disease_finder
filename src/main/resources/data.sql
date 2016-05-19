#show tables;
INSERT INTO user (username, password, confirm_password, email, enabled, authority, created_at) VALUES('krishna', '21a4ed0a0cf607e77e93bf7604e2bb1ad07757c5', '21a4ed0a0cf607e77e93bf7604e2bb1ad07757c5', 'krishna@hotmail.com', true, 'USER','2016-05-04 14:48:54');
INSERT INTO user (username, password, confirm_password, email, enabled, authority, created_at) VALUES('jan', 'dsdas123123132131231321ad', 'dsdas123123132131231321ad', 'jan@hotmail.com', true, 'USER','2016-09-04 14:54:54');
INSERT INTO user (username, password, confirm_password, email, enabled, authority, created_at) VALUES('Hans', '$2a$10$cKPLlSJdwnzWP..Hd/hNSOn.kf7EqIUBUKtsYkjq/eIuvbCEuJWLK', '$2a$10$cKPLlSJdwnzWP..Hd/hNSOn.kf7EqIUBUKtsYkjq/eIuvbCEuJWLK', 'hansdevries@hotmail.com', true, 'USER','2016-09-04 18:24:34');
INSERT INTO user (username, password, confirm_password, email, enabled, authority, created_at) VALUES('henri', '$2a$10$cKPLlSJdwnzWP..Hd/hNSOn.kf7EqIUBUKtsYkjq/eIuvbCEuJWLK', '$2a$10$cKPLlSJdwnzWP..Hd/hNSOn.kf7EqIUBUKtsYkjq/eIuvbCEuJWLK', 'henri@gmail.com', true, 'USER','2016-09-04 18:24:34');
INSERT INTO user (username, password, confirm_password, email, enabled, authority, created_at) VALUES('klaas', '$2a$10$cKPLlSJdwnzWP..Hd/hNSOn.kf7EqIUBUKtsYkjq/eIuvbCEuJWLK', '$2a$10$cKPLlSJdwnzWP..Hd/hNSOn.kf7EqIUBUKtsYkjq/eIuvbCEuJWLK', 'klaas@gmail.com', false, 'USER','2016-03-04 09:24:33');
INSERT INTO user (username, password, confirm_password, email, enabled, authority, created_at) VALUES('maxer', '$2a$10$iRjo5.oM8b5s6Q8gE8VRueDo66ioMevI1xIDq0MfpIHpCfQCxvSp.', '$2a$10$iRjo5.oM8b5s6Q8gE8VRueDo66ioMevI1xIDq0MfpIHpCfQCxvSp.', 'MaxMer@yahoo.com', true, 'USER','2015-03-10 13:24:34');

INSERT INTO search_history (created_at, query, user_id) VALUES('2015-03-10 13:54:32', 'pain', 1);
INSERT INTO search_history (created_at, query, user_id) VALUES('2015-03-10 07:26:31', 'pain', 4);
INSERT INTO search_history (created_at, query, user_id) VALUES('2015-03-10 23:31:32', 'pain', 3);
INSERT INTO search_history (created_at, query, user_id) VALUES('2015-03-10 08:24:44', 'pain', 4);
INSERT INTO search_history (created_at, query, user_id) VALUES('2015-03-10 05:24:56', 'pain', 2);
INSERT INTO search_history (created_at, query, user_id) VALUES('2015-03-10 21:04:26', 'pain', 3);