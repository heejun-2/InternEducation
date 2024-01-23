CREATE TABLE board(
                      boardId int AUTO_INCREMENT primary key,
                      title varchar(30) not null,
                      content varchar(200) not null,
                      name varchar(30) not null,
                      visitCount int,
                      uploadFileName varchar(100),
                      storeFileName varchar(100)
);

CREATE TABLE member(
                       memberId int auto_increment,
                       id varchar(30) not null,
                       originalPassword varchar(100) not null,
                       password varchar(1000),
                       name varchar(30) not null,
                       email varchar(200) not null,
                       phoneNumber varchar(40) not null,
                       role varchar(20) not null,
                       primary key(memberId)
);

INSERT INTO board(title, content, name, visitCount) VALUES ('title1', 'content1', 'name1', 0);
INSERT INTO board(title, content, name, visitCount) VALUES ('title2', 'content2', 'name2', 0);
INSERT INTO board(title, content, name, visitCount) VALUES ('title3', 'content3', 'name3', 0);
INSERT INTO board(title, content, name, visitCount) VALUES ('title4', 'content4', 'name4', 0);
INSERT INTO board(title, content, name, visitCount) VALUES ('title5', 'content5', 'name5', 0);
INSERT INTO board(title, content, name, visitCount) VALUES ('title6', 'content6', 'name6', 0);
INSERT INTO board(title, content, name, visitCount) VALUES ('title7', 'content7', 'name7', 0);
INSERT INTO board(title, content, name, visitCount) VALUES ('title8', 'content8', 'name8', 0);
INSERT INTO board(title, content, name, visitCount) VALUES ('title9', 'content9', 'name9', 0);
INSERT INTO board(title, content, name, visitCount) VALUES ('title10', 'content10', 'name10', 0);

INSERT INTO member(id, originalPassword, name, email, phoneNumber, role) VALUES ('admin123', 'admin123@', '관리자', 'admin@naver.com', '010-1234-5678', 'ADMIN');
ALTER TABLE board ADD COLUMN memberId int