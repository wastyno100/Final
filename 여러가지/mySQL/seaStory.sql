create database seaStory;
use seaStory;

create table user(
	userNo INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(20) NOT NULL,
    id VARCHAR(20) NOT NULL,
    pw VARCHAR(20) NOT NULL,
    email VARCHAR(30),
    phone CHAR(13) NOT NULL,
    point int(10) NOT NULL,
    joinDate DATETIME
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO user (name, id, pw, email, phone, point, joinDate) VALUES 
('홍길동','admin', '12345678', 'admin@gmail.com', '01045854684', '0', '2024-02-06');

SELECT * FROM user;