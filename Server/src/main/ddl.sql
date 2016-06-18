create table PARAGRAPH (
	ID integer primary key not null,
	STORY integer not null,
	STORY_TITLE varchar(100) not null,
	ROOT_PARAGRAPH integer not null,
	FATHER integer not null,
	AUTHOR_ID integer not null,
	TITLE varchar(100) not null,
	TIME integer not null,
	TEXT text
);

ALTER TABLE PARAGRAPH ADD INDEX `ID` (`ID`);
ALTER TABLE PARAGRAPH ADD INDEX `FATHER` (`FATHER`);
ALTER TABLE PARAGRAPH ADD INDEX `AUTHOR_ID` (`AUTHOR_ID`);
ALTER TABLE PARAGRAPH ADD INDEX `TITLE` (`TITLE`);
ALTER TABLE PARAGRAPH ADD INDEX `STORY` (`STORY`);
ALTER TABLE PARAGRAPH ADD INDEX `STORY_TITLE` (`STORY_TITLE`);
ALTER TABLE PARAGRAPH ADD INDEX `ROOT_PARAGRAPH` (`ROOT_PARAGRAPH`);
ALTER TABLE PARAGRAPH ADD INDEX `TIME` (`TIME`);

create table STORY (
	ID integer primary key not null,
	TITLE varchar(100) not null,
	TIME integer not null,
	AUTHOR_ID integer not null,
	PARAGRAPH_TITLE varchar(100) not null,
	PARAGRAPH_TEXT text
);

ALTER TABLE STORY ADD INDEX `ID` (`ID`);
ALTER TABLE STORY ADD INDEX `TITLE` (`TITLE`);
ALTER TABLE STORY ADD INDEX `AUTHOR_ID` (`AUTHOR_ID`);
ALTER TABLE STORY ADD INDEX `PARAGRAPH_TITLE` (`PARAGRAPH_TITLE`);
ALTER TABLE STORY ADD INDEX `TIME` (`TIME`);

create table AUTHOR (
	ID integer primary key not null,
	NAME varchar(100) not null,
	PASSWORD varchar(256) not null,
	SALT varchar(256)
);

ALTER TABLE AUTHOR ADD INDEX `ID` (`ID`);
ALTER TABLE AUTHOR ADD INDEX `NAME` (`NAME`);

