INSERT INTO AUTHOR (ID, NAME, SURNAME) 
VALUES ('21d0e992-64ee-4f74-a7c4-d1ca024c8cfe', 'John','Doe');
INSERT INTO AUTHOR (ID, NAME, SURNAME) 
VALUES ('f0837c50-7d5a-4e03-8fa0-449fb3e72779', 'Tomas', 'Brown');
INSERT INTO AUTHOR (ID, NAME, SURNAME) 
VALUES ('03863f4e-051f-4e3d-bdb4-e8ebc293d197', 'Anna', 'Smith');

INSERT INTO ARTICLE (ID, TITLE, TEXT, CREATED, AUTHOR_ID)
VALUES (
    '3bd07f99-53ac-492b-8e7c-e7fbf8fc899f', 
    'Some article', 
    'Some moke text here', 
    '2024-07-07 10:32:01.21', 
    '21d0e992-64ee-4f74-a7c4-d1ca024c8cfe');

INSERT INTO ARTICLE (ID, TITLE, TEXT, CREATED, AUTHOR_ID)
VALUES (
    '31b8b49c-d706-4dda-8ff2-e6c5fadb566a', 
    'Yet another article', 
    'Some moke text here', 
    '2024-07-08 11:01:37.01', 
    '21d0e992-64ee-4f74-a7c4-d1ca024c8cfe');    

INSERT INTO ARTICLE (ID, TITLE, TEXT, CREATED, AUTHOR_ID)
VALUES (
    '8bc07a42-1417-4851-8c5c-d258d3c48fcc', 
    'Test article', 
    'Some moke text here', 
    '2024-07-09 10:08:31.59', 
    '21d0e992-64ee-4f74-a7c4-d1ca024c8cfe');        


INSERT INTO ARTICLE (ID, TITLE, TEXT, CREATED, AUTHOR_ID)
VALUES (
    '85bad94d-1a56-4b6a-b08b-272aa5f8c72c', 
    'Moke article', 
    'Some moke text here', 
    '2024-07-09 10:08:31.59', 
    'f0837c50-7d5a-4e03-8fa0-449fb3e72779');        

INSERT INTO ARTICLE (ID, TITLE, TEXT, CREATED, AUTHOR_ID)
VALUES (
    '63c089f4-0580-486b-8a74-5009f8861159', 
    'Interesting article', 
    'Some moke text here', 
    '2024-07-21 12:55:39.43', 
    '03863f4e-051f-4e3d-bdb4-e8ebc293d197');     

INSERT INTO ARTICLE (ID, TITLE, TEXT, CREATED, AUTHOR_ID)
VALUES (
    'af192b5e-ae88-4bf3-ad53-b2665539ede7', 
    'Fascinating article', 
    'Some moke text here', 
    '2024-07-21 12:01:17.41', 
    '03863f4e-051f-4e3d-bdb4-e8ebc293d197'); 
