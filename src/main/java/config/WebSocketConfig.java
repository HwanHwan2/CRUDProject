package config;

import javax.websocket.server.ServerEndpointConfig.Configurator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import websocket.EchoHandler;

@Configuration
@EnableWebSocket
public class WebSocketConfig extends Configurator implements WebSocketConfigurer{
	
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(new EchoHandler(), "EchoHandler.do")
		.addInterceptors(new HttpSessionHandshakeInterceptor())  //HttpSession 정보를 가져오는 메소드.
		.setAllowedOrigins("*"); // 모든 브라우저 허용
	}
	
	@Bean
	public WebSocketHandler EchoHandler() {
		return new EchoHandler();
	}
}
