CREATE DATABASE IF NOT EXISTS `book`;

USE `book`;

CREATE TABLE IF NOT EXISTS `author`
(
    `id`         int NOT NULL AUTO_INCREMENT,
    `name` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `book`
(
    `id`        int NOT NULL AUTO_INCREMENT,
    `author_id` int NOT NULL,
    `title`      varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT author_id FOREIGN KEY (`author_id`) REFERENCES author (`id`)
);

INSERT INTO book.author (id, name) VALUES (1, '田中雄二');
INSERT INTO book.author (id, name) VALUES (2, '山本秀五郎');
INSERT INTO book.author (id, name) VALUES (3, '鈴木清子');
INSERT INTO book.author (id, name) VALUES (4, '佐藤真由美');
INSERT INTO book.author (id, name) VALUES (5, '加藤誠');
INSERT INTO book.book (id, author_id, title) VALUES (1, 1, '人生がときめく片づけの魔法');
INSERT INTO book.book (id, author_id, title) VALUES (2, 2, 'お金2.0');
INSERT INTO book.book (id, author_id, title) VALUES (3, 2, 'ヒトはなぜ考え、感じ、行動するのか');
INSERT INTO book.book (id, author_id, title) VALUES (4, 3, '人生の勝算');
INSERT INTO book.book (id, author_id, title) VALUES (5, 3, '食べて、祈って、恋をして');
INSERT INTO book.book (id, author_id, title) VALUES (6, 3, 'インフルエンサーの罠');
INSERT INTO book.book (id, author_id, title) VALUES (7, 4, '自分を操る超集中力');
INSERT INTO book.book (id, author_id, title) VALUES (8, 4, 'すごい人はみんな「感情的に正しい」');
INSERT INTO book.book (id, author_id, title) VALUES (9, 5, 'イノベーションのジレンマ');
INSERT INTO book.book (id, author_id, title) VALUES (10, 5, 'メンタリストが教える最強の説得術');
