package com.yuhan.helloanimal.api;

import java.net.URLEncoder;
import java.util.Map;

//Api호출을 위해 필요한 url을 만드는 메서드 클래스
public class ApiUrl {

	// API호출을 하기위한 url을 생성하고 반환하는 static메서드
	public static String makeAPIUri(String urlAddr, Map<String, String> urlMap, String serviceKey) {
		StringBuilder urlBuilder = null;
		try {
			urlBuilder = new StringBuilder("http://api.visitkorea.or.kr/openapi/service/rest/KorService/" + urlAddr); /* URL */
			urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + serviceKey); /* Service Key */
			urlBuilder.append("&" + URLEncoder.encode("ServiceKey", "UTF-8") + "="
					+ URLEncoder.encode(serviceKey, "UTF-8")); /* 공공데이터포털에서 */
			urlBuilder.append("&" + URLEncoder.encode("MobileOS", "UTF-8") + "="
					+ URLEncoder.encode("ETC", "UTF-8")); /* IOS(아이폰),AND(안드로이드),WIN(원도우폰),ETC */
			urlBuilder.append("&" + URLEncoder.encode("MobileApp", "UTF-8") + "="
					+ URLEncoder.encode("AppTest", "UTF-8")); /* 서비스명=어플명 */
			for (Map.Entry<String, String> entry : urlMap.entrySet()) {
				urlBuilder.append("&" + URLEncoder.encode(entry.getKey(), "UTF-8") + "="
						+ URLEncoder.encode(entry.getValue(), "UTF-8"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return urlBuilder.toString();
	}

}



















