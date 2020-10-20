DROP TABLE IF EXISTS pb_comments;
DROP TABLE IF EXISTS pb_article;
DROP TABLE IF EXISTS pb_user;

CREATE TABLE pb_user(
    userId INT (10)NOT NULL AUTO_INCREMENT PRIMARY KEY ,
    username VARCHAR(32) UNIQUE,
    fname VARCHAR (32) NOT NULL ,
    lname VARCHAR (32) NOT NULL ,
    dob DATE,
    passHashBase64 VARCHAR(128),
    saltBase64 VARCHAR(128),
    description TEXT DEFAULT NULL,
    imageFilename VARCHAR (128)
);
CREATE TABLE pb_article(
    articleId INT(10) NOT NULL AUTO_INCREMENT PRIMARY KEY ,
    title VARCHAR (150) NOT NULL,
    content TEXT NOT NULL ,
    dateCreated DATE DEFAULT NOW(),
    imageFileName VARCHAR (128),
    authorId INT (10),
    FOREIGN KEY (authorId) REFERENCES pb_user(userId)
);
CREATE TABLE pb_comments(
    commentId INT(10) NOT NULL AUTO_INCREMENT PRIMARY KEY ,
    content TEXT NOT NULL ,
    dateCreated DATE DEFAULT NOW(),
    userId INT(10) NOT NULL,
    articleId INT(10) NOT NULL,
    FOREIGN KEY (userId) REFERENCES pb_user(userId),
    FOREIGN KEY (articleId) REFERENCES pb_article(articleId)
);

INSERT INTO pb_user(username, fname, lname, dob, passHashBase64, saltBase64, description, imageFilename) VALUES
('Alice123', 'Alice', 'Lee', '1993-01-20', 'hash base generated', 'salt base generated', 'A description about Alice', '/images/Dragonite.png');
INSERT INTO pb_article(title, content, dateCreated, imageFileName, authorId) VALUES
('How to groom your Pokemon', 'An article about how to groom your pokemon', default, '/image/Dragonite.png',1);
INSERT INTO pb_comments(content, dateCreated, userId, articleId) VALUES
('This article is really interesting', default,1,1);