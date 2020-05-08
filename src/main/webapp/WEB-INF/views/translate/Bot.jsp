<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="ko" data-placeholders-focus="false">
<head>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<!-- css 적용  -->
<link href="/resources/css/bot.css" rel="stylesheet" type="text/css">
<!-- Custom Fonts -->
<link href="/resources/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="container">
<br><br>
<h3 class=" text-center">Translate Bot</h3>
<br><br>
<div class="messaging">
      <div class="inbox_msg">
        <div class="inbox_people">
          <div class="headind_srch">
            <div class="recent_heading">
              <h4>Recent</h4>
            </div>
            <div class="srch_bar">
              <div class="stylish-input-group">
                <input type="text" class="search-bar"  placeholder="Search" >
                <span class="input-group-addon">
                <button type="button"> <i class="fa fa-search" aria-hidden="true"></i> </button>
                </span> </div>
            </div>
          </div>
          <div class="inbox_chat">
            <div class="chat_list">
              <div class="chat_people">
                <div class="chat_img"> <img src="https://ptetutorials.com/images/user-profile.png" alt="sunil"> </div>
                <div class="chat_ib">
                  <h5><a class="lang" href='#' onclick="javascript:choose('ja')">일어 번역봇 <span class="time_date">Dec 25</span></a></h5>
                  <p>Test, which is a new approach to have all solutions 
                    astrology under one roof.</p>
                </div>
              </div>
            </div>
            <div class="chat_list">
              <div class="chat_people">
                <div class="chat_img"> <img src="https://ptetutorials.com/images/user-profile.png" alt="sunil"> </div>
                <div class="chat_ib">   
                  <h5><a class="lang"  href='#' onclick="javascript:choose('zh-CN');">중국어(간체)번역봇 <span class="time_date">Dec 25</span></a></h5>
                  <p>Test, which is a new approach to have all solutions 
                    astrology under one roof.</p>
                </div>
              </div>
            </div>
            <div class="chat_list">
              <div class="chat_people">
                <div class="chat_img"> <img src="https://ptetutorials.com/images/user-profile.png" alt="sunil"> </div>
                <div class="chat_ib">
                  <h5><a class="lang" href='#' id='zh-TW' onclick="javascript:choose('zh-TW');">중국어(번체)번역봇 <span class="time_date">Dec 25</span></a></h5>
                  <p>Test, which is a new approach to have all solutions 
                    astrology under one roof.</p>
                </div>
              </div>
            </div>
            <div class="chat_list">
              <div class="chat_people">
                <div class="chat_img"> <img src="https://ptetutorials.com/images/user-profile.png" alt="sunil"> </div>
                <div class="chat_ib">
                  <h5><a class="lang" href='#' id='en' onclick="javascript:choose('en');">영어 번역봇 <span class="time_date">Dec 25</span></a></h5>
                  <p>Test, which is a new approach to have all solutions 
                    astrology under one roof.</p>
                </div>
              </div>
            </div>
            <div class="chat_list">
              <div class="chat_people">
                <div class="chat_img"> <img src="https://ptetutorials.com/images/user-profile.png" alt="sunil"> </div>
                <div class="chat_ib">
                 <h5><a class= "lang" href='#'>번역봇 <span class="time_date">Dec 25</span></a></h5>
                  <p>Test, which is a new approach to have all solutions 
                    astrology under one roof.</p>
                </div>
              </div>
            </div>
            
          </div>
          <!-- /* Inbox_chat  -->
        </div>
       
        <div class="mesgs" >
          <div class="msg_history">  

            <div class="outgoing_msg">
              <div class="sent_msg">
                <p id = "sentMsg">Translate this text</p>
                <span class="time_time"> 11:01 AM </span>        <span class="time_date"> June 9</span> </div>
            </div>
            
            <div class="incoming_msg">
              <div class="incoming_msg_img"> <img src="https://ptetutorials.com/images/user-profile.png" alt="sunil"> </div>
              <div class="received_msg">
                <div class="received_withd_msg">
                  <p id = "resultMsg">reply from ajax
                    </p>
                  <span class="time_time"> 11:01 AM </span>       <span class="time_date"> June 9</span></div>
              </div>
            </div>

          </div>
          <div class="type_msg">
            <div class="input_msg_write">
              <input type="text" class="write_msg" id="messageInput" placeholder="Type a message" />
              <button class="msg_send_btn" type="button" id="send"><i class="fa fa-paper-plane-o" aria-hidden="true"></i>translate</button>
            </div>
          </div>
        </div>
      </div>
      <p class="text-center top_spac"> Made by <a target="_blank" href="#">Kim SeHeon</a></p>
      <p class="text-center top_spac"> Design by <a target="_blank" href="#">Sunil Rajput</a></p>
      
      
    </div></div>
    <script>
  
  		
 
	// 바꾸고자 하는 언어 
	var lang = "";
 	// 입력하는 텍스트
 	var messageInput = $("#messageInput").val();
	// 번역할 메세지 
 	var sentMsg = $("#sentMsg").val();
	// 번역 회신 메세지
	var resultMsg = $("#resultMsg").val();
	// 날짜 시간 객체
	var d = new Date();
	
	
	 
	
	
	//번역봇 선택시  
  	function choose(language){
			
  			
  			lang = language;
  		
  			// 각 번역봇 클릭시
  		
  			
  			
  			if(language == 'en'){
  				alert('한국어 => 영어');
  				
  				return;
  			}
  			if(language == 'ja'){
  				alert('한국어 => 일어');
  				
  				return;
  			}
  			if(language == 'zh-CN'){
  				alert('한국어 => 중국어(간체)');
  				return;
  			}
  			if(language == 'zh-TW'){
  				alert('한국어 => 중국어(번체)');
  				return;
  			}
  			
  			
  	};
  	
  	
  	

		
		//엔터키 눌렀을 때 
		$('#messageInput').keydown(function(key){
			
			if(event.keyCode == 13){
				if(messageInput!=null){
				
					var text = {
							 "messageInput" : $("#messageInput").val(),	
							 "lang" : lang
					 };
					//json 
					 jsonSend(text);
				}else{
					alert('번역할 글을 써주세요.');
				}
			}
		});
		
		//전송 버튼 눌렀을 때 
		$('#send').click(function()
		{
		//alert('전송 lang:'+ lang); 	
		 //번역할 object 생성 + 언어 선택 
		var text = {
				
				 "messageInput" : $("#messageInput").val(),
				 "lang" : lang
		 };
		//json 
		 jsonSend(text);
	 });
	
 	
 	
 	function jsonSend(text){
 	
 		$.ajax({
 			type : "post",
			url : "/translate/Bot",
			data : text, 
			success : function(data){
				// String 값 -> Object 값 전환
				var result_obj = JSON.parse(JSON.stringify(data));
				
				//입력값 내 채팅창에 인풋
				$('#sentMsg').text($('#messageInput').val());
				// 시간 / 날짜 정보 인풋
				$('.time_date').text((d.getMonth()+1)+" / "+d.getDate());
				$('.time_time').text(d.getHours()+" : "+ d.getMinutes());
				//결과값을 상대 채팅 창에 인풋
				$("#resultMsg").text(result_obj.message.result.translatedText);
				
			}, 
			error : function(e){
				console.log(e);
				alert('번역 실패');
			}
 		});
 	}

    </script>
    </body>
</html>