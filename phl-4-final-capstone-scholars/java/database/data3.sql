         
               
DROP TABLE IF EXISTS flashcard;
DROP TABLE IF EXISTS deck;
DROP TABLE IF EXISTS flashcard_deck;
DROP TABLE IF EXISTS deck_user;
DROP TABLE IF EXISTS flashcard_user;
DROP TABLE IF EXISTS flashcard_keyword;
//DROP TABLE IF EXISTS users;

CREATE TABLE flashcard (
        flashcard_id serial,
        question varchar(100) NOT NULL,
        answer varchar(100) NOT NULL,
        CONSTRAINT pk_flashcard PRIMARY KEY (flashcard_id)
);

CREATE TABLE flashcard_keyword (
        flashcard_id serial,
        keyword varchar(50) NOT NULL,
        CONSTRAINT pk_flashcard_keyword PRIMARY KEY (flashcard_id, keyword),
        CONSTRAINT fk_flashcard_id FOREIGN KEY (flashcard_id) REFERENCES flashcard(flashcard_id)
);


CREATE TABLE flashcard_user (
        user_id serial,
        flashcard_id serial,
        CONSTRAINT pk_flashcard_user PRIMARY KEY (user_id, flashcard_id),
        CONSTRAINT fk_flashcard_user_user_id FOREIGN KEY (user_id) REFERENCES users(user_id),
        CONSTRAINT fk_flashcard_user FOREIGN KEY (flashcard_id) REFERENCES flashcard(flashcard_id)
);

CREATE TABLE deck (
        deck_id serial,
        deck_name varchar(50),
        CONSTRAINT pk_deck PRIMARY KEY (deck_id)       
);

CREATE TABLE deck_user (
        user_id serial,
        deck_id serial,
        CONSTRAINT pk_deck_user PRIMARY KEY (user_id, deck_id),
        CONSTRAINT fk_deck_user_user_id FOREIGN KEY (user_id) REFERENCES users(user_id),
        CONSTRAINT fk_deck_user FOREIGN KEY (deck_id) REFERENCES deck(deck_id)
);

CREATE TABLE flashcard_deck (
        deck_id serial,
        flashcard_id serial,
        CONSTRAINT pk_flashcard_deck PRIMARY KEY (deck_id, flashcard_id),
        CONSTRAINT fk_flashcard_deck_deck_id FOREIGN KEY (deck_id) REFERENCES deck(deck_id),
        CONSTRAINT fk_flashcard_deck_flashcard_id FOREIGN KEY (flashcard_id) REFERENCES flashcard(flashcard_id) 
);

// below script is error free.

/*CREATE TABLE sessions(
        session_id serial,
        deck_id Integer,
        user_id Integer,
        attempted_quiz  Integer,
        mark_right Integer,
        mark_wrong Integer,
        total_right Integer,
        session_date Date,
       
        
        CONSTRAINT pk_session_id PRIMARY KEY (session_id),
        CONSTRAINT fk_deck_id FOREIGN KEY (deck_id) REFERENCES deck(deck_id),
        CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(user_id)
);*/