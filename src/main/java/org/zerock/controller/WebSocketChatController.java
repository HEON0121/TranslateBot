package org.zerock.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import lombok.extern.log4j.Log4j;


@Controller
// "/echo" 라는 url 요청을 통해 웹소켓에 들어가겠다는 어노테이션입니다.
@ServerEndpoint(value="/echo")
public class WebSocketChatController {
	private static final List<Session> sessionList = 
			new ArrayList<Session>();
	
	public WebSocketChatController() {
		System.out.println("웹 소켓 객체 생성 : 자동 서버 연결 시도 ");
		
	}
	@RequestMapping("/chat")
	public ModelAndView getChatViewPage(ModelAndView mv) {
		mv.setViewName("chat");
		return mv;
	}
	//클라이언트가 웹소켓에 들어오고 서버에 아무런 문제없이 들어왔을때 실행하는 메소드
	@OnOpen
	public void onOpen(Session session) {
		System.out.println("Open session id : " + session.getId());
		try {
			final Basic basic = session.getBasicRemote();
			basic.sendText("Connection Established");
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		sessionList.add(session);
	}
	//클라이언트에게 메세지가 들어왔을 때 실행
	@OnMessage
	public void onMessage(String message, Session session) {
		System.out.println("세션 ID : "+ session.getId() + ", 내용 : "+ message);
		System.out.println("Message From "+ message.split(",")[1] + ": "+ message.split(", ")[0]);
		try {
			final Basic basic = session.getBasicRemote();
			basic.sendText("to : " + message);
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		sendAllSessionToMessage(session, message);
	}
	@OnError
	public void onError(Throwable e, Session session) {}
	@OnClose
	public void onClose(Session session) {
		System.out.println("Session " + session.getId()+ " 가 종료 되었음");
		sessionList.remove(session);
	}
	/* 모든 사용자에게 메세지 전달 
	 * @param self
	 * @param message*/
	private void sendAllSessionToMessage(Session self, String message) {
		try {
			for(Session session : WebSocketChatController.sessionList) {
				if(!self.getId().equals(session.getId())) {
					session.getBasicRemote().sendText("전체 응답 : " + message);
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
