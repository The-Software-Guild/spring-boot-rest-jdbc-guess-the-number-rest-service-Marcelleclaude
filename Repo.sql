DROP DATABASE IF EXISTS guessGame;
CREATE DATABASE guessGame;
USE guessGame;
CREATE TABLE Games(
    id int PRIMARY KEY AUTO_INCREMENT,
    answer varchar(25) NOT NULL,
    isFinished BOOLEAN NOT NULL DEFAULT 0
);
CREATE TABLE Rounds (
    id int PRIMARY KEY AUTO_INCREMENT,
    gameID int,
    Timestamp timeStamp,
    guess varchar(10),
    guessResult VarcHAR(8)
);
ALTER TABLE Rounds
    ADD CONSTRAINT fk_round_game
        FOREIGN KEY (gameID)
        REFERENCES Game(id);
DROP DATABASE IF EXISTS guessGame_tests;
CREATE DATABASE guessGame_tests;
USE guessGame_tests;
CREATE TABLE Games(
    id int PRIMARY KEY AUTO_INCREMENT,
    answer varchar(25) NOT NULL,
    isFinished BOOLEAN NOT NULL DEFAULT 0
);
CREATE TABLE Rounds(
    id int PRIMARY KEY AUTO_INCREMENT,
    gameID int,
    Timestamp timeStamp,
    guess varchar(10),
    guessResult VarcHAR(8)
);
ALTER TABLE Rounds
    ADD CONSTRAINT fk_round_game
        FOREIGN KEY (gameID)
        REFERENCES Game(id)
;