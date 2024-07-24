drop table ARTICLE if exists;
drop table AUTHOR if exists;

create table AUTHOR (
    ID UUID PRIMARY KEY,
    NAME VARCHAR(255) NOT NULL,
    SURNAME VARCHAR(255) NOT NULL
);

create table ARTICLE (
    ID UUID PRIMARY KEY,
    TITLE TEXT NOT NULL,
    TEXT TEXT NOT NULL,
    CREATED TIMESTAMP WITH TIME ZONE,
    UPDATED TIMESTAMP WITH TIME ZONE,
    AUTHOR_ID UUID NOT NULL,
    CONSTRAINT FK_ARTICLE_AUTHOR FOREIGN KEY (AUTHOR_ID) REFERENCES AUTHOR(ID)
);