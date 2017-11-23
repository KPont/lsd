use lsd;

DROP TABLE IF EXISTS Comments;
DROP TABLE IF EXISTS Stories;
DROP TABLE IF EXISTS Users;

CREATE TABLE Users(
user_id int(11) NOT NULL AUTO_INCREMENT,
user_name varchar(60) NOT NULL,
user_pwd varchar(60) NOT NULL,
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
