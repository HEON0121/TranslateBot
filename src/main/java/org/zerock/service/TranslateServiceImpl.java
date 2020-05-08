package org.zerock.service;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class TranslateServiceImpl implements TranslateService {

	@Override
	public String TranslateService(String messageInput, String lang) {
		 	String clientId = "J3PzIdeuuc81xaMOtxQH";//���ø����̼� Ŭ���̾�Ʈ ���̵�";
	        String clientSecret = "Zn4GnR7791";//���ø����̼� Ŭ���̾�Ʈ ��ũ����";

	        String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
	        String text;
	        
	        try {
	        	System.out.println(messageInput);
	            text = URLEncoder.encode(messageInput, "UTF-8");
	        } catch (UnsupportedEncodingException e) {
	            throw new RuntimeException("���ڵ� ����", e);
	        }

	        Map<String, String> requestHeaders = new HashMap<>();
	        requestHeaders.put("X-Naver-Client-Id", clientId);
	        requestHeaders.put("X-Naver-Client-Secret", clientSecret);

	        String responseBody = post(apiURL, lang, requestHeaders, text);

	        System.out.println(responseBody);
	        
			return responseBody;
	    }

	   private static String post(String apiUrl, String lang,
				Map<String, String> requestHeaders, 
				String text){
		  
HttpURLConnection con = connect(apiUrl);
String postParams = "source=ko&target="+lang+"&text="+text; //원본언어: 한국어 (ko) -> 목적언어: 영어 (en)
System.out.println("postParams : "+ postParams);

try {
con.setRequestMethod("POST");
for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
   con.setRequestProperty(header.getKey(), header.getValue());
}

con.setDoOutput(true);
try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
   wr.write(postParams.getBytes());
   wr.flush();
}

int responseCode = con.getResponseCode();
if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 응답
   return readBody(con.getInputStream());
} else {  // 에러 응답
   return readBody(con.getErrorStream());
}
} catch (IOException e) {
throw new RuntimeException("API 요청과 응답 실패", e);
} finally {
con.disconnect();
}
}

private static HttpURLConnection connect(String apiUrl){
try {
URL url = new URL(apiUrl);
return (HttpURLConnection)url.openConnection();
} catch (MalformedURLException e) {
throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
} catch (IOException e) {
throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
}
}

private static String readBody(InputStream body){
InputStreamReader streamReader;
StringBuilder responseBody = null;
try {
streamReader = new InputStreamReader(body,"UTF-8");
try (BufferedReader lineReader = new BufferedReader(streamReader)) {
responseBody = new StringBuilder();

String line;
while ((line = lineReader.readLine()) != null) {
   responseBody.append(line);
}

} catch (IOException e) {
throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
}
} catch (UnsupportedEncodingException e1) {
// TODO Auto-generated catch block
e1.printStackTrace();
}  return responseBody.toString();


}
}
