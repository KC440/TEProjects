
INSERT INTO users (username,password_hash,role) VALUES ('final_capstone_appuser', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');

INSERT INTO flashcard VALUES (default, '2+2', '4');
INSERT INTO flashcard VALUES (default, '5+1', '6');

INSERT INTO flashcard_keyword VALUES (1, 'Math');
INSERT INTO flashcard_keyword VALUES (1, 'Addition');

INSERT INTO flashcard_keyword VALUES (2, 'Math');
INSERT INTO flashcard_keyword VALUES (2, 'Addition');

INSERT INTO deck VALUES (default, 'Addition Problems');



INSERT INTO flashcard_deck VALUES (1, 1);
INSERT INTO flashcard_deck VALUES (1, 2);

INSERT INTO deck_user VALUES (1, 1);

INSERT INTO flashcard_user VALUES (1, 1);
INSERT INTO flashcard_user VALUES (1, 2);

ALTER TABLE deck ADD  description varchar(500);


SELECT * FROM deck;

SELECT f.flashcard_id, f.question, f.answer FROM flashcard f  
                JOIN flashcard_keyword ck ON f.flashcard_id = ck.flashcard_id 
                JOIN flashcard_user fu ON f.flashcard_id = fu.flashcard_id 
                WHERE ck.keyword  LIKE  '%null%' AND fu.user_id = 1;