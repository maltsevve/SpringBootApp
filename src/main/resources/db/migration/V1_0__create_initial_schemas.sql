CREATE TABLE IF NOT EXISTS users
(
    id int AUTO_INCREMENT NOT NULL PRIMARY KEY,
    name varchar(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS files
(
    id int AUTO_INCREMENT NOT NULL PRIMARY KEY,
    filename varchar(255)
);

CREATE TABLE IF NOT EXISTS events
(
    id int AUTO_INCREMENT NOT NULL PRIMARY KEY,
    time timestamp NOT NULL,
    status int NOT NULL,
    fileid int NOT NULL,
    userid int NOT NULL,
    FOREIGN KEY (userid) references users(id)
)
