use taleitdb;

drop table IF EXISTS PARAGRAPH;

create table PARAGRAPH (
	ID varchar(100) primary key not null,
	FATHER varchar(100),
	AUTHOR_ID varchar(100) not null,
	TEXT text,
	TITLE varchar(100) not null
);

ALTER TABLE PARAGRAPH ADD INDEX `ID` (`ID`);
ALTER TABLE PARAGRAPH ADD INDEX `FATHER` (`FATHER`);
ALTER TABLE PARAGRAPH ADD INDEX `AUTHOR_ID` (`AUTHOR_ID`);
ALTER TABLE PARAGRAPH ADD INDEX `TITLE` (`TITLE`);

drop table IF EXISTS STORY;

create table STORY (
	ID integer primary key not null,
	TITLE varchar(100) not null,
	ROOT_ID varchar(100) not null,
	CATEGORY varchar(100) not null,
	PARAGRAPH_TITLE varchar(100) not null
);

ALTER TABLE STORY ADD INDEX `ID` (`ID`);
ALTER TABLE STORY ADD INDEX `TITLE` (`TITLE`);
ALTER TABLE STORY ADD INDEX `CATEGORY` (`CATEGORY`);
ALTER TABLE STORY ADD INDEX `PARAGRAPH_TITLE` (`PARAGRAPH_TITLE`);

drop table IF EXISTS AUTHOR;

create table AUTHOR (
	USERNAME varchar(100) primary key not null,
	PASSWORDHASH varchar(256) not null,
	SALT varchar(256) not null,
	COOKIE varchar(100),
	FACEBOOK_ID varchar(100),
	FACEBOOK_ACCESS_TOKEN varchar(256),
	NAME varchar(100)
);

ALTER TABLE AUTHOR ADD INDEX `USERNAME` (`USERNAME`);
ALTER TABLE AUTHOR ADD INDEX `COOKIE` (`COOKIE`);
ALTER TABLE AUTHOR ADD INDEX `FACEBOOK_ID` (`FACEBOOK_ID`);
ALTER TABLE AUTHOR ADD INDEX `FACEBOOK_ACCESS_TOKEN` (`FACEBOOK_ACCESS_TOKEN`);
ALTER TABLE AUTHOR ADD INDEX `NAME` (`NAME`);



