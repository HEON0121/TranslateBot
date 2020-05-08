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
// "/echo" ��� url ��û�� ���� �����Ͽ� ���ڴٴ� ������̼��Դϴ�.
@ServerEndpoint(value="/echo")
public class WebSocketChatController {
	private static final List<Session> sessionList = 
			new ArrayList<Session>();
	
	public WebSocketChatController() {
		System.out.println("�� ���� ��ü ���� : �ڵ� ���� ���� �õ� ");
		
	}
	@RequestMapping("/chat")
	public ModelAndView getChatViewPage(ModelAndView mv) {
		mv.setViewName("chat");
		return mv;
	}
	//Ŭ���̾�Ʈ�� �����Ͽ� ������ ������ �ƹ��� �������� �������� �����ϴ� �޼ҵ�
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
	//Ŭ���̾�Ʈ���� �޼����� ������ �� ����
	@OnMessage
	public void onMessage(String message, Session session) {
		System.out.println("���� ID : "+ session.getId() + ", ���� : "+ message);
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
		System.out.println("Session " + session.getId()+ " �� ���� �Ǿ���");
		sessionList.remove(session);
	}
	/* ��� ����ڿ��� �޼��� ���� 
	 * @param self
	 * @param message*/
	private void sendAllSessionToMessage(Session self, String message) {
		try {
			for(Session session : WebSocketChatController.sessionList) {
				if(!self.getId().equals(session.getId())) {
					session.getBasicRemote().sendText("��ü ���� : " + message);
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
