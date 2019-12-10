package com.yuhan.helloanimal.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Api {
	
	private String resultXmlStr ="";
	
	public String getResultXmlStr() {
		return this.resultXmlStr;
	}
	
	public Api(String pageNo) throws IOException {
		StringBuilder urlBuilder = new StringBuilder("http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/abandonmentPublic");
        urlBuilder.append("?" + URLEncoder.encode("bgnde","UTF-8") + "=" + URLEncoder.encode("20190101","UTF-8")); 
        urlBuilder.append("&" + URLEncoder.encode("endde","UTF-8") + "=" + URLEncoder.encode("20191130","UTF-8")); 
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode(pageNo,"UTF-8")); 
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("9","UTF-8")); 
        urlBuilder.append("&" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + 
        					"5GNVseCsSklXvjRoIA81IY%2Bvu94C4OoBIeWM%2FQNuOUOKytgK6GWGx1IkOSY9gJHvZQ7yWBNy5fWFTiACpGYtVw%3D%3D"); 
        System.out.println("요청 url: "+urlBuilder.toString());
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/xml;charset=UTF-8");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(),"utf-8"));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        resultXmlStr=sb.toString();
	}
	
	
	
}
