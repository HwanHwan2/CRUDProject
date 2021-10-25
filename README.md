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
![ex_screenshot](.img/capture1.png)
