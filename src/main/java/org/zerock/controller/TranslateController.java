package org.zerock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.zerock.service.TranslateServiceImpl;


//���̹� Papago NMT API ����

@Controller
@RequestMapping("/translate/*")
public class TranslateController {
	@Autowired 
	private TranslateServiceImpl service;
	@RequestMapping(value="/Bot", method=RequestMethod.GET)
	public String Bot() {
		return "/translate/Bot";
	}
	
	
	
	//produces = {"application/json;charset=utf-8"}
	@RequestMapping(value="/Bot", method=RequestMethod.POST,
			produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public String Bot(@RequestParam("messageInput") String messageInput,
					@RequestParam("lang") String lang
					) {
		
		System.out.println("messageInput :" + messageInput);
		System.out.println("lang : " + lang);
		
		String responseBody = service.TranslateService(messageInput, lang);
		System.out.println("cont responseBody :" + responseBody);
		
		return responseBody;
		
	}
	
}
