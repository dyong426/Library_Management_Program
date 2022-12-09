-- 도서관 관리 프로그램 DB

-- 관리자 정보 테이블
CREATE TABLE admins(
     admin_num      NUMBER(11)  CONSTRAINT ad_ad_num_pk  PRIMARY KEY,
     admin_name     VARCHAR2(255)  CONSTRAINT ad_ad_name_nn NOT NULL,
     admin_pw       VARCHAR2(255)  CONSTRAINT ad_ad_pw_nn NOT NULL,
     admin_phone    VARCHAR2(255)  CONSTRAINT ad_ad_phone_uk UNIQUE,
     admin_email    VARCHAR2(255)  CONSTRAINT ad_ad_email_nn NOT NULL,
     admin_address  VARCHAR2(255)  CONSTRAINT ad_ad_address_nn NOT NULL,
     admin_registrationdate  DATE  CONSTRAINT ad_ad_reg_date_nn NOT NULL,
     admin_note     VARCHAR2(255)
);

-- 회원 정보 테이블
CREATE TABLE members (
     mem_num        NUMBER(11) CONSTRAINT m_m_num_pk  PRIMARY KEY,
     mem_name       VARCHAR2(255) CONSTRAINT m_m_name_nn NOT NULL,
     mem_id         VARCHAR2(255) CONSTRAINT m_m_id_uk UNIQUE,
     mem_pw         VARCHAR2(255) CONSTRAINT m_m_pw_nn NOT NULL,
     mem_birthday   CHAR(6) CONSTRAINT m_m_birthday_nn NOT NULL,
     mem_sex        CHAR(1) CONSTRAINT m_m_sex_boolean CHECK(mem_sex IN ('0', '1')),
     mem_phone      VARCHAR2(255) CONSTRAINT m_m_phone_uk UNIQUE,
     mem_email      VARCHAR2(255) CONSTRAINT m_m_email_nn NOT NULL,
     mem_address    VARCHAR2(255) CONSTRAINT m_m_address_nn NOT NULL,
     mem_registrationdate DATE CONSTRAINT m_m_reg_date_nn NOT NULL,
     mem_note       VARCHAR2(255)     
);


SELECT * FROM members;

-- 도서 정보 테이블
CREATE TABLE books(
     book_id        VARCHAR2(255)  CONSTRAINT b_b_id_pk  PRIMARY KEY,
     book_title     VARCHAR2(255)  CONSTRAINT b_b_title_nn NOT NULL,
     book_author    VARCHAR2(255)  CONSTRAINT b_b_author_nn NOT NULL,
     book_publisher VARCHAR2(255)  CONSTRAINT b_b_publisher_nn NOT NULL,
     book_isbn  VARCHAR2(255) CONSTRAINT b_b_isbn_uk UNIQUE,
     book_bias      NUMBER(11) DEFAULT 1,
     book_duplicates NUMBER(11) DEFAULT 1,
     book_registration_date   DATE CONSTRAINT b_b_reg_nn NOT NULL,
     book_price     NUMBER(11),
     location_id    CHAR(1) CONSTRAINT b_loc_id_fk REFERENCES locations(location_id),
     book_note      VARCHAR2(255)
);

--CREATE TABLE callsign(
--     book_callsign  VARCHAR2(255)  CONSTRAINT c_b_cs_pk  PRIMARY KEY,
--     cs_classification_code VARCHAR2(255),
--     cs_sign           VARCHAR2(255),
--     cs_bias           NUMBER(11),
--     cs_duplicates     NUMBER(11)
--);

-- 도서 분류 / 위치 테이블
CREATE TABLE locations(
     location_id    CHAR(1) CONSTRAINT l_l_id_pk  PRIMARY KEY,
     location_name  VARCHAR2(255) CONSTRAINT l_l_name_nn NOT NULL
);

-- 대출 정보 테이블
CREATE TABLE check_out_info(
     check_out_id   NUMBER(11) CONSTRAINT coi_coi_id_pk PRIMARY KEY,
     book_id        VARCHAR2(255) CONSTRAINT coi_b_id_fk REFERENCES books(book_id),
     mem_num        NUMBER(11) CONSTRAINT coi_m_num_fk REFERENCES members(mem_num),
     check_out_date DATE CONSTRAINT coi_c_o_date_nn NOT NULL,
     expect_return_date DATE,
     check_in_date  DATE
);

-- 열람실 정보 테이블
CREATE TABLE readingroom(
     seat_num       NUMBER(11) CONSTRAINT r_s_num_pk PRIMARY KEY,
     table_divider  CHAR(1),
     seat_sex       CHAR(1)
);

-- 좌석 이용 정보 테이블
CREATE TABLE seat_use_details(
     use_id    NUMBER(11) CONSTRAINT sud_u_id_pk PRIMARY KEY,
     mem_num   NUMBER(11) CONSTRAINT sud_m_num_fk REFERENCES members(mem_num),
     seat_num  NUMBER(11) CONSTRAINT sud_s_num_fk REFERENCES readingroom(seat_num),
     start_time DATE CONSTRAINT sud_s_time_nn NOT NULL,
     end_time DATE
);

-- 이미지 정보 테이블
CREATE TABLE IMAGE_INFORMATION(
     image_id  NUMBER(11) CONSTRAINT i_i_id_pk PRIMARY KEY,
     image_name     VARCHAR2(255) CONSTRAINT i_i_name_nn NOT NULL, 
     image_byte_info      BLOB CONSTRAINT i_i_b_info_nn NOT NULL
);

CREATE SEQUENCE mem_num_seq
     INCREMENT BY 1
     START WITH 1
     MINVALUE 1
     MAXVALUE 99999999
     CYCLE
     NOCACHE;
     
CREATE SEQUENCE admin_num_seq
     INCREMENT BY 1
     START WITH 1
     MINVALUE 1
     MAXVALUE 99999999
     CYCLE
     NOCACHE;

CREATE SEQUENCE book_id_seq
     INCREMENT BY 1
     START WITH 1
     MINVALUE 1
     MAXVALUE 999999999
     CYCLE
     NOCACHE;

CREATE SEQUENCE user_detail_id_seq
     INCREMENT BY 1
     START WITH 1
     MINVALUE 1
     MAXVALUE 99999999
     CYCLE
     NOCACHE;

CREATE SEQUENCE check_out_id_seq
     INCREMENT BY 1
     START WITH 1
     MINVALUE 1
     MAXVALUE 99999999
     CYCLE
     NOCACHE;

SELECT * FROM tabs;
