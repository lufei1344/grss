package com.grss.jlsx.core.utils;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;

public class XmlUtil {
	private static final String LINE_FEED = "\r\n";
	public static String toXml(Map<String, String> aliasFields, Object o){
		XStream xStream = new XStream();
		if(aliasFields != null){
			for(String alias : aliasFields.keySet()){
				xStream.aliasField(alias, o.getClass(), aliasFields.get(alias));
			}
		}
		xStream.alias("xml", o.getClass());
		return xStream.toXML(o).replaceAll("__", "_");
	}
	/**
	 * map转换XML
	 * @param params
	 * @return
	 */
	public static  String mapToXml(Map<String, String> params){
		StringBuilder builder = new StringBuilder("<xml>" + LINE_FEED);
		for(String key : params.keySet()){
			builder.append("<" + key + ">");
			builder.append(params.get(key));
			builder.append("</" + key + ">" + LINE_FEED);
		}
		builder.append("</xml>");
		return builder.toString();
	}
	
	
	public static Map<String,String> parseXmlToMap(String xml){
		Document doc = null;
		try {
			Map<String, String> result = new HashMap<>();
			doc = DocumentHelper.parseText(xml);
			Element rootElt = doc.getRootElement();
			List<?> elements = rootElt.elements();
			for (Object ele : elements) {
				 Element recordEle = (Element) ele;
				 if("prepay_id".equals(recordEle.getName())){
					 result.put("prepayid", recordEle.getTextTrim());
				 }
			}
			return result;
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Map<String,String> parseXml2Map(String xml){
		try {
			Document doc = null;
			Map<String, String> result = new HashMap<>();
			doc = DocumentHelper.parseText(xml);
			Element rootElt = doc.getRootElement();
			List<?> elements = rootElt.elements();
			for (Object ele : elements) {
				 Element recordEle = (Element) ele;
				 result.put(recordEle.getName(), recordEle.getTextTrim());
			}
			return result;
		} catch (DocumentException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Map<String, String> parseXmlToMap(InputStream is){
		try {
			Map<String, String> result = new HashMap<>();
			Document doc = null; 
			SAXReader reader = new SAXReader();
			doc = reader.read(is);
			Element rootElt = doc.getRootElement();
			List<?> elements = rootElt.elements();
			for (Object ele : elements) {
				 Element recordEle = (Element) ele;
				 result.put(recordEle.getName(), recordEle.getTextTrim());
			}
			return result;
		} catch (DocumentException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<>();
		map.put("a", "test");
		map.put("b", "zhangsan");
		char ch = 'a';
		int h = 0;
		System.out.println((h = "aascfcvv6658667反反复复反反复复凤飞飞555555555555".hashCode()) ^ (h >>> 16));
		System.out.println(6 << 5);
	}
}
