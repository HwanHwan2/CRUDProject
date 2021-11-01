# CRUDProject (2021-10-25 ~ )

Spring을 이용하여 기본적인(CRUD) 웹사이트 만들기.
잠시 까먹었던 웹사이트 코딩 기억을 되돌리고자 만들기 시작.

목표 - CRUD가 가능한 웹사이트 만들기. ( + 다른 특별한 기능을 곁들인)

사용 툴 - Eclpise, MariaDB   
언어 - Java, Sql, HTML, CSS, Javascript, JSP, XML   
기술 - JQuery, JSON, Ajax, WebSocket, toastr   
프레임워크 - Spring, MyBatis   

## **<2021-10-25>**
===============MariaDB 테이블 생성 완료=============

- 테이블 생성(User, Board, Comment)
      ㄴ User(id, pw, name, nickname, birth, email)
      ㄴ Board(no, title, content, b_date, hits, files)
      ㄴ Comment(no, id, c_date)
 - 테스트용 데이터 user1, user2 넣음.

================프로젝트 기본 설정 완료=============

- 프로젝트에 사용되는 dependency 작성
- web.xml 작성
- 패키지 생성(config, controller, dao, dao.mapper, logic, dto)
      ㄴ config - DBConfig, MvcConfig
      ㄴ controller - UserController
      ㄴ dao - UserDao
      ㄴ dao.mapper - UserMapper
      ㄴ logic - CRUDService
      ㄴ dto - User
      
===============sitemesh 설정 완료===================

- [w3school](https://www.w3schools.com/w3css/w3css_templates.asp) 템플릿 중 하나 사용

===================================================   
DB와 연동이 잘 됐는지 확인을 위해 유저 정보(id, pw) 가져오기 성공
![capture1](https://user-images.githubusercontent.com/82797084/138723003-ba43dcfc-df55-44dc-9e25-71cfc9c5c48a.PNG)

## **<2021-11-01>**
-부트스트랩의 모달창을 이용하여 회원가입 폼 완성. 정상적으로 회원가입이 됨.
![c1](https://user-images.githubusercontent.com/82797084/139650758-5035625c-1972-439d-8024-d1d249bb27f9.PNG)
![c2](https://user-images.githubusercontent.com/82797084/139650772-d37228e7-57f9-45a7-a9f4-9ae78b21deca.PNG)   
로그아웃 하는 작업은 아직 안했다. layout에 회원가입 modal을 달았는데 ModelAttribute 생성 문제로 살짝 꼬임.    
내일 페이지 정리 좀 다시하고 로그인에 관한 것들 유효성 검증과 에러메시지 출력 할 예정...



