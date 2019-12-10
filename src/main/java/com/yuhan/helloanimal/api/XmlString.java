package com.yuhan.helloanimal.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

//made by seunghyun

/*
 * Xml문자열 관련 클래스입니다.
 */
public class XmlString {

	private String xmlStr;

	public XmlString() {
		this.xmlStr = null;
	}

	public XmlString(String xmlStr) {
		this.xmlStr = xmlStr;
	}

	public String getXmlStr() {
		return xmlStr;
	}

	public void setXmlStr(String xmlStr) {
		this.xmlStr = xmlStr;
	}

	// <item>태그 갯수를 세어 api호출결과값의 갯수를 추출
	public int extractNumOfRows() {
		String str = this.xmlStr;
		int resultRows = 0;

		while(true) {
			int startIdx = str.indexOf("<item>");
			if(startIdx == -1)
				break;
			int lastIdx = str.indexOf("</item>");
			int criteriaIdx = lastIdx+"</item>".length();
			str = str.substring(criteriaIdx);
			resultRows++;
		}
		return resultRows;
		
	}

	// 전달한 태그(태그포함)에 해당하는 값을 반환한다.(태그값이 존재하지 않을 시 noTag라는 문자열을 반환)
	public String extractXmlValue(String tag) {
		if (xmlStr.contains(tag)) {
			String lastTagName = tag.replace("<", "</");
			int startIdx = xmlStr.indexOf(tag);
			int lastIdx = xmlStr.indexOf(lastTagName);
			startIdx += tag.length();
			String resultValue = xmlStr.substring(startIdx, lastIdx);
			return resultValue;
		}
		return "noTag";
	}
	
	//전달한 특정xml문자열에서 전달한 태그(태그포함)에 해당하는 값을 반환한다.(태그값이 존재하지 않을 시 noTag라는 문자열을 반환)
	public static String extractXmlValue(String tag, String specificXmlStr) {
		if (specificXmlStr.contains(tag)) {
			String lastTagName = tag.replace("<", "</");
			int startIdx = specificXmlStr.indexOf(tag);
			int lastIdx = specificXmlStr.indexOf(lastTagName);
			startIdx += tag.length();
			String resultValue = specificXmlStr.substring(startIdx, lastIdx);
			return resultValue;
		}
		return "noTag";
	}
	

	// api호출을 위한 url 문자열을 받아 api호출을하고 각 xml에서 <item>node값과 그 <item>node값에 해당하는 value값을 map형식으로 담아 반환하는 메서드(api호출과 추출 2가지를 처리)
	// (divisionStr인자는 map에 저장하는 과정이 달라 "imageInfo"혹은 "detailInfo"를 전달받아 구분하기위함)
	public Map<String, String> extractXmlNodeAndValue(String urlStrToCallApi, String divisionStr) {

		Map<String, String> extractedXmlNodeAndValue = new HashMap<>();
		BufferedReader br = null;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder;
		Document doc = null;
		XmlString xmlStr = new XmlString();

		try {
			String urlstr = urlStrToCallApi;
			URL url = new URL(urlstr);
			HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();

			br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));
			String result = "";
			String line;
			while ((line = br.readLine()) != null) {
				result = result + line.trim();
			}

			
			xmlStr.setXmlStr(result);
			String resultCode = xmlStr.extractXmlValue("<resultCode>");
			if (!resultCode.equals("0000")) {
//				if (resultCode.equals("0022"))
//					throw new LimitedRequestException();
//				throw new NotOkResponseException();
			}

			InputSource is = new InputSource(new StringReader(result));
			builder = factory.newDocumentBuilder();
			doc = builder.parse(is);
			XPathFactory xpathFactory = XPathFactory.newInstance();
			XPath xpath = xpathFactory.newXPath();
			XPathExpression expr = xpath.compile("//items/item");
			NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
			for (int i = 0; i < nodeList.getLength(); i++) {
				NodeList child = nodeList.item(i).getChildNodes();
				for (int j = 0; j < child.getLength(); j++) {
					Node node = child.item(j);
					if (!node.getTextContent().isEmpty() && !node.getTextContent().equals("")) {
						if (divisionStr.equals("imageInfo")) { //추가이미지인 경우(이미지정보)
							extractedXmlNodeAndValue.put(node.getNodeName() + "_" + i + "_" + j, node.getTextContent());
						} else if (divisionStr.equals("detailInfo")) { //범용(공통정보, 세부정보, 지역정보)
							extractedXmlNodeAndValue.put(node.getNodeName(), node.getTextContent());
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return extractedXmlNodeAndValue;
	}
	
	//xml문자열중에  전달한 태그명<태그면></태그명> 사이에 존재하는 문자열을 추출해내는 메서드 
	public List<String> extractSpecificXmlStr(String tagName){
		List<String> xmlList = new ArrayList<>();
		
		String targetStr=null;
		int startIdx=0;
		int lastIdx=0;
		int criteriaIdx=0;
		
		for(int i=0; i<extractNumOfRows(); i++) {
			targetStr = xmlStr.substring(criteriaIdx);
			if(targetStr.contains("<"+tagName+">")) {  
				startIdx = targetStr.indexOf("<"+tagName+">");
				startIdx += "<".length() + tagName.length() + ">".length();
				lastIdx = targetStr.indexOf("</"+tagName+">");
				criteriaIdx += lastIdx + "</".length() + tagName.length() + ">".length();
				String tempStr = targetStr.substring(startIdx, lastIdx);
				xmlList.add(tempStr);
			}else {
				return null;
			}
		}
		return xmlList;
	}

}























