use lsd;

DROP TABLE IF EXISTS Comments;
DROP TABLE IF EXISTS Stories;
DROP TABLE IF EXISTS Users;

CREATE TABLE Users(
user_id int(11) NOT NULL,
user_name varchar(60) NOT NULL,
user_pw varchar(60) NOT NULL,
email varchar(60),
PRIMARY KEY(user_id));

CREATE TABLE Stories(
story_id int(11) NOT NULL,
story_title varchar(60) NOT NULL,
story_link varchar(60) NOT NULL,
story_type varchar(60) NOT NULL,
user_id int(11) NOT NULL,
PRIMARY KEY(story_id),
FOREIGN KEY(user_id) REFERENCES Users(user_id));

CREATE TABLE Comments(
comment_id int(11) NOT NULL,
content varchar(60),
story_id int(11) NOT NULL,
PRIMARY KEY(comment_id),
FOREIGN KEY(story_id) REFERENCES Stories(story_id));

INSERT INTO Users
VALUES(13533433, "John", "hej", "whatever@whatever.com");
INSERT INTO Users
VALUES(13533533, "Per", "hej2", "whatever2@whatever.com");
INSERT INTO Users
VALUES(13533633, "Karsten", "hej3", "whatever3@whatever.com");
INSERT INTO Users
VALUES(13533733, "Mette", "hej4", "whatever4@whatever.com");


INSERT INTO Stories
VALUES(19999999, "test", "www.whatever.whatever", "-1", 13533433);
INSERT INTO Stories
VALUES(19999998, "test2", "www.whatever.whatever", "-1", 13533533);
INSERT INTO Stories
VALUES(19999997, "test3", "www.whatever.whatever", "-1", 13533633);
INSERT INTO Stories
VALUES(19999996, "test4", "www.whatever.whatever", "-1", 13533733);

INSERT INTO Comments
VALUES(99999991, "test 421", 19999999);
INSERT INTO Comments
VALUES(99999992, "test2 420", 19999998);
INSERT INTO Comments
VALUES(99999993, "test3 419", 19999997);
INSERT INTO Comments
VALUES(99999994, "test4 418", 19999996);

INSERT INTO Comments
VALUES(99999995, "test 418", 19999999);


SELECT * FROM Users;

SELECT story_id FROM Stories WHERE story_title = "testAdd2";

SELECT * FROM Users WHERE Users.user_name = "Anton-Arne";

SELECT user_name FROM Users WHERE user_id = (SELECT Stories.user_id FROM Stories WHERE Stories.story_id = (SELECT Comments.story_id FROM Comments WHERE Comments.content = "test 421"));

SELECT * FROM Comments WHERE Comments.story_id = (SELECT Stories.story_id FROM Stories WHERE Stories.story_title = "test");

