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

## **<2021-11-15>**   
백신 부작용으로 엄청 고생해서 늦어졌다...    

- Modal창 속 회원가입은 BindingResult를 사용하기엔 어렵다고 생각되서 javscript로 일일이 검증작업을 했다. 보안은 좀 취약할듯..?   
      ㄴ 에러사항(공백, 아이디와 닉네임 중복(ajax로 검증))   

- 드디어 BindingResult 사용법을 익혀서 로그인 쪽 View에 BindingResult를 사용해 유효성 검증을 하도록 했다. 하나 배웠다!   
      ㄴ 에러사항(아이디와 비밀번호 공백, 아이디 비밀번호 틀림 or 없음)   

- User 비밀번호 암호화 과정 완료. javax.crypto.Cipher 이용. 이론은 너무 어려워서 흐름만 알고 패스. 나중에 공부해보기로
![1](https://user-images.githubusercontent.com/82797084/141738503-afb6e525-d020-4bb0-bd13-b06eefaec5aa.PNG)
![2](https://user-images.githubusercontent.com/82797084/141738511-85251716-e98d-4c97-b730-73a7a11fe48e.PNG)


## **<2021-11-22>**   
- 아이디/비밀번호 찾기 완료.
      ㄴ 나중에 이메일 인증으로 비밀번호 찾을 수 있게 해볼 예정. 
      
## **<2021-11-25>**
- 게시물 리스트 완성했다. 심플하게 뽑아냈다. 아직 페이징은 안한 상태... 곧 할 예정입니다.   
      ㄴ 리스트 날짜 로직 변경. 오늘 쓴 글일 경우 시간, 오늘 전에 쓴 글일 경우 날짜.   
![리스트](https://user-images.githubusercontent.com/82797084/143399652-2616ea7b-e860-4abb-8f5b-a20675e92d08.PNG)   
(작성자 작성일 빈 곳은 에러 봐가면서 만들었다는 증거임 ㅎㅎ.>)  

- 게시물 글쓰기 덜완성. CKEditor5로 파일 업로드까지 할 예정.   
![글쓰기](https://user-images.githubusercontent.com/82797084/143399912-7bdd04e4-490c-4b54-afc1-033b12a3f77c.PNG)   

여기까지!  

## **<2021-11-29>**
- 게시물 페이징 + 특정 게시물 검색 완료..   
      ㄴ 너무 어려움 완전 복잡함..   
  ![게시판 페이징](https://user-images.githubusercontent.com/82797084/143825924-c358189b-6978-43cd-a9ab-17152a82e52d.PNG)   
  ![검색](https://user-images.githubusercontent.com/82797084/143825942-b996f9b3-4e5a-4297-9f8f-dc9d2d8b7ba4.PNG)   

- 게시글 상세보기 디자인 살짝 변경 + 내 글일 경우 수정/삭제 가능하게   
      ㄴ (네이버 카페 참고했다..)
![캡차 222](https://user-images.githubusercontent.com/82797084/143826284-2a1eb3d3-038b-464f-a343-093766e601b3.PNG)

다음에 할 일 : 게시글 댓글. 게시글 수정/삭제   
프로젝트 약 80% 완료?   
++ 정보처리산업기사 한번에 붙음!   
![aaaa](https://user-images.githubusercontent.com/82797084/143826812-a0e53633-8033-4cd8-b8b1-2264283be853.jpg)
    
 ## **<2021-12-07>**   
 - 게시글 수정/삭제 완료.    
      ㄴ 수정 할 때 게시물 내용 가져오는 부분에서 좀 헤맸다.
 다음에 할 일 : 게시글 댓글.   
 
 
 ## **<2021-12-13>** 
 - 댓글 작성/삭제 완료.   
![11](https://user-images.githubusercontent.com/82797084/145769678-6c0852b5-1c71-4ef3-afb5-49aeca751644.PNG)   


## **<2021-12-14>**
- 댓글 작성을 했을 때 댓글 no가 증가하지 않는 오류 발견.   
      ㄴ 해결방법 : 댓글 있는지 없는지 구분을 maxno로 하지 않고 댓글count로 했음. 해결 완료    
- 게시글 조회수를 안넣었었다. 재빠르게 조회수 넣었다.   
- 내 정보 페이지 만들기 완료   
      ㄴ 내 개인정보 + 내 게시물 표시되게 함.
 ![캡처](https://user-images.githubusercontent.com/82797084/145980395-d95e5f99-a8a3-430a-a3d1-ea8dbf0da082.PNG)

다음에 할 일 : 게시글 리스트에 댓글 수 추가하기. 유저 사진 추가하기. 게시글 첨부파일.
 
 
## **<2021-12-15>**
- 회원탈퇴 완료.   
      ㄴ modal창을 이용해서 회원탈퇴 완료.
   ![탈퇴](https://user-images.githubusercontent.com/82797084/146167589-4b230f93-ab2f-4051-bafc-494f26be0133.PNG)
- 비밀번호 변경이 안됨.. 원인도 모르겠음...    
      ㄴ 일단 코드 첨부.    
      ㄴ 사용할 비밀번호가 일치한다고 치고 현재 비밀번호를 잘못 입력하면 data가 0으로 넘어와서    
         alert("비밀번호가 일치하지 않습니다.") 로 알림을 띄우고 return false를 해주고 싶은데   
         알림을 뜨는데 return false가 작동이 안되고 submit이 되서 비밀번호가 바꿔짐. 원인을 모르겠다.
       ![ㅋㅋㅋㅋ](https://user-images.githubusercontent.com/82797084/146168011-21c20352-71ba-4ad4-af1b-3fc77ac8009f.PNG)
  
다음에 할 일 : 비밀번호 변경 끝내기. 유저 사진 추가하기. 게시글 첨부파일
