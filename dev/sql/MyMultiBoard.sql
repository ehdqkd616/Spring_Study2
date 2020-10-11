---------------------------------------------
------------------ SYSTEM -------------------
---------------------------------------------

CREATE USER myMultiBoard IDENTIFIED BY myMultiBoard;
GRANT RESOURCE, CONNECT, DBA TO myMultiBoard;
ALTER USER myMultiBoard default tablespace users quota unlimited on users;

---------------------------------------------

CREATE TABLE MEMBER
(
	USERID VARCHAR2(40) PRIMARY KEY,
	PASSWORD VARCHAR2(255) NOT NULL, 
    USERNAME VARCHAR2(20) NOT NULL,
	EMAIL VARCHAR2(200) NOT NULL UNIQUE,
	PHONE_NUMBER VARCHAR2(40) NOT NULL UNIQUE, 
    NICKNAME VARCHAR2(50) NOT NULL UNIQUE, 
    ENABLED CHAR(1) DEFAULT 1, 
    REPORT_COUNT NUMBER DEFAULT 0,
    PROFILE_PIC BLOB NULL,
    JOIN_DATE DATE NOT NULL,
    AUTHORITY NUMBER NOT NULL,
    
    CONSTRAINT AUTH_FK FOREIGN KEY (AUTHORITY) REFERENCES AUTHORITIES (AUTHORITY)
);


CREATE TABLE BOARD
(
    BOARD_NUMBER NUMBER PRIMARY KEY,
    BOARD_TITLE VARCHAR2(200) NOT NULL,
    BOARD_CONTENT CLOB NOT NULL,
    READ_COUNT NUMBER DEFAULT 0,
    LIKE_COUNT NUMBER DEFAULT 0,
    DISLIKE_COUNT NUMBER DEFAULT 0, 
    REPORT_COUNT NUMBER DEFAULT 0,
    WRITE_DATE TIMESTAMP NOT NULL, 
    USERID VARCHAR2(40) NOT NULL, 
    
    CONSTRAINT WRITER_FK FOREIGN KEY (USERID) REFERENCES MEMBER (USERID) ON DELETE CASCADE
);

CREATE TABLE BOARD_FILE
(
    BOARD_NUMBER NUMBER NOT NULL,
    FILE_NUMBER NUMBER PRIMARY KEY,
    FILE_NAME VARCHAR2(250) NOT NULL,
    FILE_SIZE VARCHAR2(50) NOT NULL,
    FILE_TYPE VARCHAR2(50) NOT NULL,
    FILE_UPLOAD_DATE TIMESTAMP NOT NULL,
    FILE_DATA BLOB NOT NULL,
    USERID VARCHAR2(40) NOT NULL,
    
    CONSTRAINT BOARD_FK FOREIGN KEY (BOARD_NUMBER) REFERENCES BOARD (BOARD_NUMBER) ON DELETE CASCADE
);

CREATE TABLE BOARD_COMMENT 
(
	BOARD_NUMBER NUMBER	NOT NULL,
    COMMENT_NUMBER NUMBER NOT NULL,
	COMMENT_CONTENT VARCHAR2(1000) NOT NULL,
    LIKE_COUNT NUMBER DEFAULT 0,
    DISLIKE_COUNT NUMBER DEFAULT 0,
    WRITE_DATE TIMESTAMP NOT NULL,
    PARENT_NUMBER NUMBER ,
    STEP_NUMBER NUMBER DEFAULT 1,
    USERID VARCHAR2(40) NOT NULL, 
    
    CONSTRAINT COMMENT_PK PRIMARY KEY(BOARD_NUMBER, COMMENT_NUMBER),
    CONSTRAINT COMMENT_FK FOREIGN KEY (BOARD_NUMBER) REFERENCES BOARD (BOARD_NUMBER) ON DELETE CASCADE
);

CREATE TABLE AUTHORITIES
(
    AUTHORITY NUMBER PRIMARY KEY,
    AUTHORITY_NAME VARCHAR2(100)
);

COMMIT;

select count(*) from member where USERID='z';

select m.userid, password, username, email, phone_number, nickname, enabled, report_count, profile_pic, au.authority, au.authority_name 
from member m 
join authorities au 
on m.authority=au.authority
where m.userid='user01';

select rnum, userid, username, email, phone_number, nickname, enabled, report_count, profile_pic, join_date, authority_name 
	from (select rownum rnum, userid, username, email, phone_number, nickname, enabled, report_count, profile_pic, join_date, authority_name 
		from (select userid, username, email, phone_number, nickname, enabled, report_count, profile_pic, join_date, authority_name 
			from member m join authorities a on m.authority=a.authority order by m.userid));

