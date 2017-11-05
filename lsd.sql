use lsd;

DROP TABLE IF EXISTS Comments;
DROP TABLE IF EXISTS Stories;
DROP TABLE IF EXISTS Users;

CREATE TABLE Users(
user_id int(11) NOT NULL AUTO_INCREMENT,
user_name varchar(60) NOT NULL,
user_pw varchar(60) NOT NULL,
email varchar(60),
PRIMARY KEY(user_id));

CREATE TABLE Stories(
story_id int(11) NOT NULL AUTO_INCREMENT,
story_title varchar(60) NOT NULL,
story_link varchar(60) NOT NULL,
story_type varchar(60) NOT NULL,
user_id int(11) NOT NULL,
PRIMARY KEY(story_id),
FOREIGN KEY(user_id) REFERENCES Users(user_id));


CREATE TABLE Comments(
comment_id int(11) NOT NULL AUTO_INCREMENT,
content varchar(60),
user_id int(11) NOT NULL,
story_id int(11) NOT NULL,
PRIMARY KEY(comment_id),
FOREIGN KEY(user_id) REFERENCES Users(user_id),
FOREIGN KEY(story_id) REFERENCES Stories(story_id));

INSERT INTO Users
VALUES(NULL,"John", "hej", "whatever@whatever.com");
INSERT INTO Users
VALUES(NULL,"Per", "hej2", "whatever2@whatever.com");
INSERT INTO Users
VALUES(NULL,"Karsten", "hej3", "whatever3@whatever.com");
INSERT INTO Users
VALUES(NULL,"Mette", "hej4", "whatever4@whatever.com");


INSERT INTO Stories
VALUES(NULL,"test", "www.whatever.whatever", "-1", 1);
INSERT INTO Stories
VALUES(NULL,"test2", "www.whatever.whatever", "-1", 2);
INSERT INTO Stories
VALUES(NULL,"test3", "www.whatever.whatever", "-1", 2);
INSERT INTO Stories
VALUES(NULL,"test4", "www.whatever.whatever", "-1", 3);


INSERT INTO Comments
VALUES(NULL,"test 421", 1, 2);
INSERT INTO Comments
VALUES(NULL,"test2 420", 2, 1);
INSERT INTO Comments
VALUES(NULL,"test3 419", 3, 1);
INSERT INTO Comments
VALUES(NULL,"test4 418", 4, 3);
