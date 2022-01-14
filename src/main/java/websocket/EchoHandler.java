package websocket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import dto.User;

@Component
@Repository
public class EchoHandler extends TextWebSocketHandler{

	List<WebSocketSession> sessions = new ArrayList<WebSocketSession>(); //로그인 중인 전체 유저
	Map<String, WebSocketSession> userSessionsMap = new HashMap<String, WebSocketSession>(); //로그인중인 개별유저
	
	private String UserNickname; //접속한 유저를 저장하는 변수
	
	//연결 완료
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		sessions.add(session);
		UserNickname = getUserId(session);
		System.out.println(UserNickname + " = " + session.getId());
		userSessionsMap.put(UserNickname, session);
		System.out.println("접속중인 유저 : " + userSessionsMap);
	}
	
	//메시지 옴
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception{
		String msg = message.getPayload(); //클라이언트에서 보낸 메세지를 msg에 저장

		if(!StringUtils.isEmpty(msg)) { //msg 값이 !Empty인 경우
			String strs[] = msg.split(",");
			if(strs != null && strs.length == 4) { //쪽지 보낼 때 알람.
				String sentid = strs[0];
				String recvid = strs[1]; 
				String content = strs[2];
				String today = strs[3];
		 		
		 		//받는 사람이 로그인 되어 있을 경우
		 		WebSocketSession userSession = userSessionsMap.get(recvid);
		 		if(userSession != null) { //받는 사람이 세션에 있는 경우. 로그인 되어 있는 경우.
		 			TextMessage tMessage = new TextMessage(sentid + "님이 게시글에 댓글을 남겼습니다.," + "'" + content + "'");
	 				userSession.sendMessage(tMessage);
		 		} else {
		 			System.out.println("받는 사람이 로그인을 안했네요");
		 		}
			}
		}
	}
	
	//연결 종료
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println("연결 끊김 : " + UserNickname);
		userSessionsMap.remove(session.getId());
		sessions.remove(session);
	}
	
	//==============================웹소켓에서 쓰일 유저의 닉네임을 가져오기=======================
	private String getUserId(WebSocketSession session) {
		Map<String, Object> httpSession = new HashMap<>();
		httpSession = session.getAttributes();	
		User loginUser = (User)httpSession.get("login");
		if(loginUser == null) {
			return session.getId();
		} else {
			return loginUser.getNickname();
		}
	}
	
}
