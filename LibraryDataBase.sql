/* 
     도서관 관리 프로그램 DB
     
     * TABLE 목록
     
     - admins                 관리자 직원 정보
     - members                회원 정보
     - books                  도서정보
     - locations              도서 위치 정보
     - check_out_info         대출 정보
     - readingroom            열람실 정보
     - seat_use_details       열람실 사용내역
     - image_information      프로그램에 사용되는 이미지 정보
     
     * SEQUENCE
          
     - admin_num_seq          관리자 번호
     - mem_num_seq            회원 번호
     - book_id_seq            등록 번호
     - check_out_id_seq       대출내역 아이디 
     - user_detail_id_seq     열람실 좌석 사용내역 아이디
     
*/
SELECT * FROM tabs;
SELECT * FROM user_sequences;

-- 관리자 직원 정보 테이블
CREATE TABLE admins(
     admin_num      NUMBER(11)  CONSTRAINT ad_ad_num_pk  PRIMARY KEY,      -- 사원 번호  (PK)
     admin_name     VARCHAR2(255)  CONSTRAINT ad_ad_name_nn NOT NULL,      -- 사원 이름
     admin_pw       VARCHAR2(255)  CONSTRAINT ad_ad_pw_nn NOT NULL,        -- 사원 비밀번호
     admin_phone    VARCHAR2(255)  CONSTRAINT ad_ad_phone_uk UNIQUE,       -- 사원 연락처 (UK)
     admin_email    VARCHAR2(255)  CONSTRAINT ad_ad_email_nn NOT NULL,     -- 사원 이메일
     admin_address  VARCHAR2(255)  CONSTRAINT ad_ad_address_nn NOT NULL,   -- 사원 주소
     admin_registrationdate  DATE  CONSTRAINT ad_ad_reg_date_nn NOT NULL,  -- 사원 등록일 
     admin_note     VARCHAR2(255)                                          -- 비고
);

-- 회원 정보 테이블
CREATE TABLE members (
     mem_num        NUMBER(11) CONSTRAINT m_m_num_pk  PRIMARY KEY,    -- 회원 번호  (PK)
     mem_name       VARCHAR2(255) CONSTRAINT m_m_name_nn NOT NULL,    -- 회원 이름
     mem_id         VARCHAR2(255) CONSTRAINT m_m_id_uk UNIQUE,        -- 회원 아이디 (UK)
     mem_pw         VARCHAR2(255) CONSTRAINT m_m_pw_nn NOT NULL,      -- 회원 비밀번호
     mem_birthday   CHAR(6) CONSTRAINT m_m_birthday_nn NOT NULL,      -- 회원 생년월일
     mem_sex        CHAR(1) CONSTRAINT m_m_sex_boolean CHECK(mem_sex IN ('0', '1')), -- 회원 성별 (0,1 로 구분)
     mem_phone      VARCHAR2(255) CONSTRAINT m_m_phone_uk UNIQUE,     -- 회원 연락처 (UK)
     mem_email      VARCHAR2(255) CONSTRAINT m_m_email_nn NOT NULL,   -- 회원 이메일
     mem_address    VARCHAR2(255) CONSTRAINT m_m_address_nn NOT NULL, -- 회원 주소
     mem_registrationdate DATE CONSTRAINT m_m_reg_date_nn NOT NULL,   -- 회원 등록 번호
     mem_note       VARCHAR2(255)                                     -- 비고
);

-- 도서 정보 테이블
CREATE TABLE books(
     book_id        VARCHAR2(255)  CONSTRAINT b_b_id_pk  PRIMARY KEY,      -- 도서 등록번호 (PK)
     book_title     VARCHAR2(255)  CONSTRAINT b_b_title_nn NOT NULL,       -- 도서 제목
     book_author    VARCHAR2(255)  CONSTRAINT b_b_author_nn NOT NULL,      -- 도서 저자
     book_publisher VARCHAR2(255)  CONSTRAINT b_b_publisher_nn NOT NULL,   -- 도서 출판사
     book_isbn  VARCHAR2(255) CONSTRAINT b_b_isbn_uk UNIQUE,               -- 도서 ISBN 번호  (UK)
     book_bias      NUMBER(11) DEFAULT 1,                                  -- 도서 편권차 (기본값 1)
     book_duplicates NUMBER(11) DEFAULT 1,                                 -- 도서 복권수 (기본값 1)
     book_registration_date   DATE CONSTRAINT b_b_reg_nn NOT NULL,         -- 도서 등록일
     book_price     NUMBER(11),                                            -- 도서 가격
     location_id    CHAR(1) CONSTRAINT b_loc_id_fk REFERENCES locations(location_id), -- 도서 위치 (FK)
     book_note      VARCHAR2(255)                                          -- 비고
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
     location_id    CHAR(1) CONSTRAINT l_l_id_pk  PRIMARY KEY,        -- 도서 위치 아이디   (PK)
     location_name  VARCHAR2(255) CONSTRAINT l_l_name_nn NOT NULL     -- 도서 위치 이름
);

-- 대출 내역 테이블
CREATE TABLE check_out_info(
     check_out_id   NUMBER(11) CONSTRAINT coi_coi_id_pk PRIMARY KEY,                 -- 대여 아이디 (PK)
     book_id        VARCHAR2(255) CONSTRAINT coi_b_id_fk REFERENCES books(book_id),  -- 대여 도서   (FK)
     mem_num        NUMBER(11) CONSTRAINT coi_m_num_fk REFERENCES members(mem_num),  -- 회원 번호   (FK)
     check_out_date DATE CONSTRAINT coi_c_o_date_nn NOT NULL,                        -- 대여 날짜
     expect_return_date DATE,                                                        -- 반납 예정 날짜
     check_in_date  DATE                                                             -- 반납 날짜
);

-- 열람실 정보 테이블
CREATE TABLE readingroom(
     seat_num       NUMBER(11) CONSTRAINT r_s_num_pk PRIMARY KEY,     -- 좌석 번호  (PK)
     table_divider  CHAR(1),                                          -- 칸막이 여부
     seat_sex       CHAR(1)                                           -- 좌석 성별
);

-- 좌석 이용 정보 테이블
CREATE TABLE seat_use_details(
     use_id    NUMBER(11) CONSTRAINT sud_u_id_pk PRIMARY KEY,                        -- 열람실 사용내역 아이디     (PK)
     mem_num   NUMBER(11) CONSTRAINT sud_m_num_fk REFERENCES members(mem_num),       -- 회원정보   (FK)
     seat_num  NUMBER(11) CONSTRAINT sud_s_num_fk REFERENCES readingroom(seat_num),  -- 좌석 번호  (FK)
     start_time DATE CONSTRAINT sud_s_time_nn NOT NULL,                              -- 사용 시작 시간
     end_time DATE                                                                   -- 사용 종료 시간
);

-- 이미지 정보 테이블
CREATE TABLE IMAGE_INFORMATION(
     image_id  NUMBER(11) CONSTRAINT i_i_id_pk PRIMARY KEY,                          -- 이미지 아이디     (PK)
     image_name     VARCHAR2(255) CONSTRAINT i_i_name_nn NOT NULL,                   -- 이미지 이름
     image_byte_info      BLOB CONSTRAINT i_i_b_info_nn NOT NULL                     -- 이미지 byte 정보
);

-- 회원 번호 시퀀스
CREATE SEQUENCE mem_num_seq
     INCREMENT BY 1
     START WITH 1
     MINVALUE 1
     MAXVALUE 99999999
     CYCLE
     NOCACHE;
     
-- 사원 번호 시퀀스
CREATE SEQUENCE admin_num_seq
     INCREMENT BY 1
     START WITH 1
     MINVALUE 1
     MAXVALUE 99999999
     CYCLE
     NOCACHE;

-- 도서 등록번호 시퀀스
CREATE SEQUENCE book_id_seq
     INCREMENT BY 1
     START WITH 1
     MINVALUE 1
     MAXVALUE 999999999
     CYCLE
     NOCACHE;

-- 열람실 사용 내역 아이디 시퀀스
CREATE SEQUENCE user_detail_id_seq
     INCREMENT BY 1
     START WITH 1
     MINVALUE 1
     MAXVALUE 99999999
     CYCLE
     NOCACHE;

-- 대여 아이디 시퀀스
CREATE SEQUENCE check_out_id_seq
     INCREMENT BY 1
     START WITH 1
     MINVALUE 1
     MAXVALUE 99999999
     CYCLE
     NOCACHE;

SELECT * FROM tabs;
