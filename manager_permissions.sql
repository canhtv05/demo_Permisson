CREATE DATABASE testPermission;
GO
USE testPermission;
GO

CREATE TABLE tb_Login
(
    username VARCHAR(20) PRIMARY KEY,
    password VARCHAR(20) NOT NULL,
    role BIT NOT NULL
);

CREATE TABLE tb_Admin
(
    id INT IDENTITY(1, 1) PRIMARY KEY,
    username VARCHAR(20) NOT NULL,
    FOREIGN KEY(username) REFERENCES tb_Login(username)
);

CREATE TABLE tb_User
(
    id INT IDENTITY(1, 1) PRIMARY KEY,
    username VARCHAR(20) NOT NULL,
    FOREIGN KEY(username) REFERENCES tb_Login(username)
);

CREATE TABLE tb_Permissions
(
    id INT IDENTITY(1, 1) PRIMARY KEY,
    user_id INT NOT NULL,
    can_add BIT NOT NULL DEFAULT 0,
    can_remove BIT NOT NULL DEFAULT 0,
    can_update BIT NOT NULL DEFAULT 0,
    FOREIGN KEY(user_id) REFERENCES tb_User(id)
);

INSERT INTO tb_Login (username, password, role)
VALUES ('admin', 'adminpass', 1);

INSERT INTO tb_Login (username, password, role)
VALUES ('user1', 'user1pass', 0);

INSERT INTO tb_Login (username, password, role)
VALUES ('user2', 'user2pass', 0);

INSERT INTO tb_Admin (username)
VALUES ('admin'); 

INSERT INTO tb_User (username)
VALUES ('user1'), ('user2');

DECLARE @user1_id INT, @user2_id INT;
SELECT @user1_id = id FROM tb_User WHERE username = 'user1';
SELECT @user2_id = id FROM tb_User WHERE username = 'user2';

INSERT INTO tb_Permissions (user_id, can_add)
VALUES (@user1_id, 1);

INSERT INTO tb_Permissions (user_id, can_remove)
VALUES (@user2_id, 1);

SELECT * FROM dbo.tb_Admin
SELECT * FROM dbo.tb_Login
SELECT * FROM dbo.tb_Permissions
SELECT * FROM dbo.tb_User

SELECT username, password, role FROM dbo.tb_Login

SELECT can_add, can_remove, can_update FROM dbo.tb_Permissions
WHERE user_id IN (SELECT id FROM dbo.tb_User WHERE username = '?')

SELECT id, username FROM dbo.tb_User
SELECT username, password FROM dbo.tb_Login WHERE role = 0

UPDATE dbo.tb_Permissions
SET can_add = 1, can_remove = 1, can_update = 1
WHERE USER_ID IN (SELECT id FROM dbo.tb_User WHERE username = ?)

INSERT INTO tb_Login (username, password, role) VALUES (?, ?, 0);
INSERT INTO tb_User (username) VALUES (?);
DECLARE @P3INSERT INT;
SELECT @P3INSERT = id FROM dbo.tb_User WHERE username = ?
INSERT INTO tb_Permissions (user_id, can_add, can_remove, can_update)
VALUES (@P3INSERT, ?, ?, ?);

UPDATE dbo.tb_Login
SET username = ' ', password = ' ', role = 0 WHERE username = ?;

DELETE FROM dbo.tb_Permissions
WHERE user_id IN (SELECT id FROM dbo.tb_User WHERE username = 'user3');
DELETE FROM dbo.tb_User
WHERE username = 'user3';
DELETE FROM dbo.tb_Login
WHERE username = 'user3';
