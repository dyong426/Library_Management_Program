# Library-Mangement-Program
>![도서관리프로그램_홈](https://user-images.githubusercontent.com/119422179/228705031-2ae3633d-f858-4a08-bfa4-4066fc3ea62d.png)

## 프로젝트 주제

> 이 프로그램은 도서관의 자원을 효율적으로 관리하고, 이용자들이 도서를 더욱 편리하게 이용할 수 있도록 다양한 기능을 제공합니다.
>
>이용자 모드에서는 도서검색 기능을 통해 원하는 책을 쉽게 찾을 수 있으며, 열람실 사용 과 이용 가능 여부도 확인할 수 있습니다. 회원 정보 관리와 회원 가입 기능을 통해 회원들은 자신의 정보를 관리하고, 새로운 회원으로 가입할 수 있습니다.
>
>관리자 모드에서는 도서관리 기능을 통해 도서의 정보를 등록하고, 대출 및 반납 상황을 확인할 수 있습니다. 또한 열람실 현황을 모니터링하고, 강제 퇴실 기능을 통해 열람실 이용 규칙을 준수하지 않는 이용자를 강제로 퇴실시킬 수 있습니다. 회원과 직원의 정보를 관리할 수 있습니다.
>
>이 프로그램을 통해 도서관 운영의 효율성을 높일 수 있고, 이용자들이 좀 더 편리하게 도서관을 이용할 수 있도록 지원할 수 있습니다.

## 개발 기간

>- 22.12.05 ~ 22.12.26 

## 개발 환경

>- JDK : 17.0.4.1 
>
>- IDE : eclipse 2022-09  
>
>- Database : Oracle DB(11g) 
>

## Library Management Program 기능 설명

### 주요 기능
---
#### 담당 파트
##### [관리자 파트](#admin)
>- ##### [ 도서 관리 ](#도서-관리)
>- ##### [ 대출반납 관리 ](#대출반납-관리)
##### [회원 파트](#member)
>- ##### [ 도서 검색 ](#도서-검색)
---

#### [Mode] 선택
>
>- admin  -> (도서,대출,열람실,회원,직원 관리 기능) 
>    
>- member -> (도서 검색, 열람실 입퇴실, 회원정보 관리) 
>   
-------------------------------------------------------
#### [admin]
>
>- #### 도서 관리  
  >   - 도서 검색 
  >   ![도서관리프로그램_도서검색](https://user-images.githubusercontent.com/119422179/228706040-2f85b131-4d2a-46d1-b88f-264f563663eb.png)   
  >   - 도서 등록
  >   ![도서관리프로그램_도서등록](https://user-images.githubusercontent.com/119422179/228705489-ca668713-4de7-4036-9889-68df93a7b478.png)
  >   - 도서 수정  
  >   ![도서관리프로그램_도서수정](https://user-images.githubusercontent.com/119422179/228705640-d8f4cc9d-a70e-40db-aa55-9fda7dce5be1.png)
  >   - 도서 삭제  
  
>- #### 대출반납 관리  
  >   ![도서관리프로그램_대출반납](https://user-images.githubusercontent.com/119422179/228705798-ded56387-2dcd-4180-895f-418702096461.png)
  >   - 회원검색  
  >   - 대출도서 검색   
  >   - 회원 대출 도서 반납  
  
>- #### 열람실 관리 
  >   ![도서관리프로그램_열람실](https://user-images.githubusercontent.com/119422179/228706161-603a61c9-b9e7-408a-abf5-b94530060750.png)
  >   - 이용 회원 목록 조회 
  >   - 이용자 강제퇴실  
  
>- #### 회원 관리 
  >   ![도서관리프로그램_회원검색](https://user-images.githubusercontent.com/119422179/228706264-cf3c7003-a3a2-4799-b994-3cee6882ecb0.png)
  >   ![도서관리프로그램_회원수정](https://user-images.githubusercontent.com/119422179/228706349-bb3a92d4-df5a-4c2b-a8d4-1d42a71d1bfe.png)
  >    - 회원 정보 검색 
  >    - 회원 정보 조회
  >    - 회원 정보 수정
  >   - 비밀번호 초기화  
  
>- #### 직원 관리
  >   ![도서관리프로그램_직원수정](https://user-images.githubusercontent.com/119422179/228706499-c5bbbc0d-1fbd-43ce-974c-5025106eea19.png)
  >    - 직원 조회 
  >    - 직원 등록
  >    - 직원 수정
  >    - 직원 삭제
-------------------------------------------------------
#### [member]
>
>- #### 도서 검색
  >   ![도서관리프로그램_유저홈](https://user-images.githubusercontent.com/119422179/228707055-c1f1bfbf-fa01-4c8f-b4c1-bbdf712fd28e.png)  
  
>- #### 열람실
  >   ![도서관리프로그램_유저홈](https://user-images.githubusercontent.com/119422179/228707144-dd25827f-afa4-4dbf-abbd-a2fa157015be.png) 
  >   - 입실
  >   - 퇴실  
  
>- #### 회원관리
  >   ![도서관리프로그램_유저관리](https://user-images.githubusercontent.com/119422179/228707215-dc1675b4-be36-4dac-b0f7-5e3227d26807.png)
  >   - 회원 등록
  >   - 수정
  >   - 탈퇴
  >   - 조회
-------------------------------------------------------  
