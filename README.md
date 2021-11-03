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

## **<2021-11-03>**    
- 로그인 폼 완성했다! CSS 활용을 잘 못해서 시간이 좀 걸렸지만 내 손으로 직접 해서 뿌듯하다!
      심플한걸 좋아해서 심플하게 했는데 너무 허허벌판인가..
      아니 근데 springframework <form:form> 태그로 input password 입력하는 곳의 value값을 건드린 적이 없는데 자꾸 기본값이 0이 된다. 이건 어떻게 해결하지
![캡처](https://user-images.githubusercontent.com/82797084/140049757-fdd01558-bc46-4924-aeb0-2546d443a3ca.PNG)


- 앞서 말한 ModelAttribute 문제는 해결했다.    
      logout.do 링크가 layout에 적용된 것이 문제였다. layout엔 ModelAttribute를 생성 못하기 때문에 로그아웃 할 때마다 막히는 거였다.   
      logout.do 링크를 layout에 적용 안되도록 하니 로그아웃이 잘된다.   
         
- 로그인과 회원가입에 Validation을 이용한 검증처리를 하려고 하는데 너무 어려워서 시간이 오래걸릴 것 같다....   
      회원가입을 Modal창으로 연결해놔서 BindingResult를 사용하는데 어려움이 있는 것 같다.   
      Modal창으로 회원가입을 꼭 하고 싶은데 계속 파도 안되면 회원가입 페이지를 따로 만들어서 할 수밖에 없을 것 같다....(검증 하는데 많은 시간이 걸릴 예정ㅠ)

