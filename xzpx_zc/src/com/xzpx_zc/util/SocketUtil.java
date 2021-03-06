package com.xzpx_zc.util;

import java.lang.reflect.Type;




import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.HttpEntity;   
import org.apache.http.NameValuePair;  //**************  涓嶈瀵煎叆閿檍ar鍖�  涓嶆槸httpClient
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;




/**
 * httpClient
 * 璁块棶缃戠粶璧勬簮
 * @author zhangchao
 *
 */
public class SocketUtil {
	public static void main(String[] args) {
		try {
			//System.out.println(getRequest("http://127.0.0.1/estore/indexAction.action"));
			
//			Map<String, String> map = new HashMap<String, String>();
//			map.put("userName", "xu");
//			Type MAP_TYPE = new TypeToken<Map<String, Object>>() {}.getType();
//			Gson gson = new GsonBuilder().serializeNulls().create();
//			postJSON("http://127.0.0.1/estore/adminLogin.action", gson.toJson(map, MAP_TYPE));
//			String s = 
//						"[{\"userName\": \"寮犺秴1\", \"mobile\":\"15062259016\", \"products\":[{\"name\": \"鍝囧搱鍝圽"}]},"
//						+ "{\"userName\": \"寮犺秴2\", \"mobile\":\"15062259016\",\"products\":[{\"name\": \"鎬曟�曠儷\"}]}]";
//			//s = "<?xml version=\"1.0\" standalone=\"yes\"?><product id=\"2\"><area>寰愬窞</area><name>鐤媯Java璁蹭箟</name></product>";  
//			//String o = postJSON("http://127.0.0.1:8080/springDemo/select1.action", s);
//			//	String result = new String(o.getBytes("ISO8859-1"), "UTF-8");
//			Map<String, String> map = new HashMap<String, String>();
//			map.put("products[1].name", "濮嬩簬鍒濊锛屾浜庣粓鑰�");
//			String o = postRequest("http://127.0.0.1:8080/springDemo/select1.action", map);
//			System.out.println(o);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static String getRequest(String url) throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response = httpClient.execute(httpGet);
		if(HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
			HttpEntity entity = response.getEntity();
			return EntityUtils.toString(entity).trim();
		}
		response.close();
		httpClient.close();
		return null;
	}
	public static String postRequest(String url, Map<String, String> map) throws Exception {
	  try {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		//鍒涘缓鍙傛暟闃熷垪
		List<NameValuePair> formParams = new ArrayList<NameValuePair>();
		Iterator<String> iterator =  map.keySet().iterator();
		while(iterator.hasNext()) {
			String key = iterator.next();
			String value = map.get(key);
			formParams.add(new BasicNameValuePair(key, value));
		}
		UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(formParams, "UTF-8");
		httpPost.setEntity(uefEntity);
		CloseableHttpResponse response = httpclient.execute(httpPost);
		
		if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			HttpEntity entiry = response.getEntity();
			return EntityUtils.toString(entiry).trim();
		}
	   } finally {
	   }
		return null;
	}
	/**
	 * 鎸囧畾鍙傛暟鍚嶅彂閫亁ml
	 * @param url
	 * @param xml
	 * @return
	 */
	public static String postXML(String url, String xml, String paramName) throws Exception{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		List<NameValuePair> formParams = new ArrayList<NameValuePair>();
		formParams.add(new BasicNameValuePair(paramName, xml));
		UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(formParams, "UTF-8");
		httpPost.setEntity(uefEntity);
		CloseableHttpResponse response = httpClient.execute(httpPost);
		if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			HttpEntity entity = response.getEntity();
			return EntityUtils.toString(entity).trim();
		}
		response.close();
		httpClient.close();
		return null;
	}
	/**
	 * 涓嶆寚瀹氬弬鏁板彂閫亁ml
	 */
	public static String postXML(String url, String xml) throws Exception{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		StringEntity myEntity = new StringEntity(xml, "UTF-8"); 
		httpPost.addHeader("Content-Type", "application/xml"); 
		httpPost.setEntity(myEntity); 
         CloseableHttpResponse response = httpClient.execute(httpPost); 
         if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
 			HttpEntity entity = response.getEntity();
 			return EntityUtils.toString(entity).trim();
 		}
        response.close();
 		httpClient.close();
		return null;
	}
	/**
	 * 涓嶆寚瀹氬弬鏁板彂閫乯son
	 */
	public static String postJSON(String url, String json) throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		StringEntity myEntity = new StringEntity(json, "UTF-8"); 
		myEntity.setContentEncoding("UTF-8");    
		httpPost.addHeader("Content-type", "application/json"); 
		httpPost.setEntity(myEntity); 
         CloseableHttpResponse response = httpClient.execute(httpPost); 
         if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
 			HttpEntity entity = response.getEntity();
 			//System.out.println(EntityUtils.toString(entity).trim());
 			//EntityUtils.toString鏂规硶鍦ㄨ皟鐢ㄧ殑鏂规硶涓彧鑳借浣跨敤涓�涓嬶紝鍙嶄箣鍒欐姏鍑篒OException
 			return EntityUtils.toString(entity).trim();
 		}
         
		return null;
	}
	/**
	 * 涓婁紶鏂囦欢
	 */
}

