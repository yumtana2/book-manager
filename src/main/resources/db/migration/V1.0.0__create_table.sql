CREATE DATABASE IF NOT EXISTS `book`;

USE `book`;

CREATE TABLE IF NOT EXISTS `author`
(
    `id`         int NOT NULL AUTO_INCREMENT,
    `first_name` varchar(255) DEFAULT NULL,
    `last_name`  varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `book`
(
    `id`        int NOT NULL AUTO_INCREMENT,
    `author_id` int NOT NULL,
    `tile`      varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT author_id FOREIGN KEY (`author_id`) REFERENCES author (`id`)
);

INSERT INTO book.author (id, first_name, last_name) VALUES (1, '佐久間', '大介');
INSERT INTO book.author (id, first_name, last_name) VALUES (2, '目黒', '蓮');
INSERT INTO book.author (id, first_name, last_name) VALUES (3, '岸', '優太');
INSERT INTO book.author (id, first_name, last_name) VALUES (4, '高橋', '海人');
INSERT INTO book.author (id, first_name, last_name) VALUES (5, '平野', '紫耀');
INSERT INTO book.book (id, author_id, tile) VALUES (1, 1, '人生がときめく片づけの魔法');
INSERT INTO book.book (id, author_id, tile) VALUES (2, 2, 'お金2.0');
INSERT INTO book.book (id, author_id, tile) VALUES (3, 2, 'ヒトはなぜ考え、感じ、行動するのか');
INSERT INTO book.book (id, author_id, tile) VALUES (4, 3, '人生の勝算');
INSERT INTO book.book (id, author_id, tile) VALUES (5, 3, '食べて、祈って、恋をして');
INSERT INTO book.book (id, author_id, tile) VALUES (6, 3, 'インフルエンサーの罠');
INSERT INTO book.book (id, author_id, tile) VALUES (7, 4, '自分を操る超集中力');
INSERT INTO book.book (id, author_id, tile) VALUES (8, 4, 'すごい人はみんな「感情的に正しい」');
INSERT INTO book.book (id, author_id, tile) VALUES (9, 5, 'イノベーションのジレンマ');
INSERT INTO book.book (id, author_id, tile) VALUES (10, 5, 'メンタリストが教える最強の説得術');
