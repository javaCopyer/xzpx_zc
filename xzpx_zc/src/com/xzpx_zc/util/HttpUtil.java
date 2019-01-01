package com.xzpx_zc.util;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

public class HttpUtil {
	
	
	
	/**
	 * 设置请求时间
	 * @param connectTime
	 * @param connectRequest
	 * @param socketTime
	 * @return
	 * @author sky
	 * @date 2018-2-19 下午5:00:28
	 */
	public static RequestConfig requestConfig(int connectTime, int connectRequest, int socketTime) {
		 RequestConfig requestConfig = RequestConfig.custom()
	                .setConnectTimeout(connectTime)   //设置连接超时时间
	                .setConnectionRequestTimeout(connectRequest) 
	                .setSocketTimeout(socketTime)//// 设置请求超时时间
	                //.setRedirectsEnabled(true)//默认允许自动重定向
	                .build();
		 return requestConfig;
	}
	
	
	/**
	 * 忽略服务器证书的客户端
	 * @return
	 * @author sky
	 * @date 2018-2-19 下午4:13:02
	 */
	@SuppressWarnings("deprecation")
	public static CloseableHttpClient createNewClientDefault(){
		  try {
//			  		KeyStore keyStore = KeyStore.getInstance("PKCS12");
//		        	keyStore.load(new FileInputStream(new File("C:\\Users\\Administrator\\Desktop\\jiaoyiping.p12")), "123456".toCharArray());   
					SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
		            	// 全部信任 不做身份鉴定
		                   public boolean isTrusted(X509Certificate[] chain,
		                                   String authType) throws CertificateException {
		                       return true;
		                   }
		               })
		               //加载服务端提供的truststore(如果服务器提供truststore的话就不用忽略对服务器端证书的校验了)
		               //.loadTrustMaterial(new File("D:\\truststore.jks"), "123456".toCharArray(),
                //        new TrustSelfSignedStrategy())
		              /**加载客户端证书**/ 
		               //.loadKeyMaterial(keyStore, "cmcc".toCharArray())
		               .build();
		               SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
		               
		               Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
		                       .register("http", new PlainConnectionSocketFactory())
		                       .register("https", sslsf)
		                       .build();
		               PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
		               cm.setMaxTotal(200);//max connection
		               return HttpClients.custom().setSSLSocketFactory(sslsf).setConnectionManager(cm).build();
		           } catch (KeyManagementException e) {
		               e.printStackTrace();
		           } catch (NoSuchAlgorithmException e) {
		               e.printStackTrace();
		           } catch (KeyStoreException e) {
		               e.printStackTrace();
		           }
		           return  HttpClients.createDefault();
		  }

	/**
	 * 忽略验证服务器证书的https  get请求
	 * @param url
	 * @param charset
	 * @return
	 * @throws Exception
	 * @author sky
	 * @date 2018-2-19 下午4:10:56
	 */
	public static String httpsGet(String url, String charset) throws Exception{
		CloseableHttpClient httpclient = createNewClientDefault();
		String result = "";
		try {
			HttpGet httpGet = new HttpGet(url);
			httpGet.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
			httpGet.setConfig(requestConfig(5000, 5000, 5000));
			 CloseableHttpResponse response = httpclient.execute(httpGet);
			 if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				 result = EntityUtils.toString(response.getEntity(), charset);	 
			 }
			 
		} catch(Exception e) {
			throw e;
		} finally {
			try {
				if(httpclient != null)
					httpclient.close();
			} catch(Exception e) {
				throw e;
			}
			
		}
		return result;
	}
	
	/**
	 * 忽略验证服务器证书的https post请求
	 * @param url
	 * @param params
	 * @param charset
	 * @return
	 * @throws Exception
	 * @author sky
	 * @date 2018-2-19 下午4:12:28
	 */
	public static String httpsPost(String url, Map<String, String> params, String charset) throws Exception {
		CloseableHttpClient httpclient = createNewClientDefault();
		String result = "";
		try {
			 HttpPost httppost = new HttpPost(url);
			 httppost.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
			 List<NameValuePair> list = new ArrayList<NameValuePair>(); 
			 for(Map.Entry<String, String> entry : params.entrySet()) {
				  list.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));  //请求参数
			 }
			 UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,"UTF-8"); 
			 httppost.setEntity(entity);
			 CloseableHttpResponse response = httpclient.execute(httppost);
			 if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				 result = EntityUtils.toString(response.getEntity(), charset);	 
			 }
			 
		} catch(Exception e) {
			throw e;
		} finally {
			try {
				if(httpclient != null)
					httpclient.close();
			} catch(Exception e) {
				throw e;
			}
			
		}
		return result;
	}
	
	
	/**
	 * 
	 * @param url
	 * @param charset 返回数据的编码格式
	 * @return
	 * @throws Exception
	 * @author sky
	 * @date 2018-2-19 上午11:24:00
	 */
	public static String get(String url, String charset) throws Exception{
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String result = "";
		try {
			HttpGet httpGet = new HttpGet(url);
			httpGet.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
			httpGet.setConfig(requestConfig(5000, 5000, 5000));
			 CloseableHttpResponse response = httpclient.execute(httpGet);
			 if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				 result = EntityUtils.toString(response.getEntity(), charset);	 
			 }
			 
		} catch(Exception e) {
			throw e;
		} finally {
			try {
				if(httpclient != null)
					httpclient.close();
			} catch(Exception e) {
				throw e;
			}
			
		}
		return result;
	}
	
	/**
	 * 
	 * @param url
	 * @param params
	 * @param charset
	 * @return
	 * @throws Exception
	 * @author sky
	 * @date 2018-2-19 下午4:11:50
	 */
	public static String post(String url, Map<String, String> params, String charset) throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String result = "";
		try {
			 HttpPost httppost = new HttpPost(url);
			 httppost.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
			 List<NameValuePair> list = new ArrayList<NameValuePair>(); 
			 for(Map.Entry<String, String> entry : params.entrySet()) {
				  list.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));  //请求参数
			 }
			 UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,"UTF-8"); 
			 httppost.setEntity(entity);
			 CloseableHttpResponse response = httpclient.execute(httppost);
			 if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				 result = EntityUtils.toString(response.getEntity(), charset);	 
			 }
			 
		} catch(Exception e) {
			throw e;
		} finally {
			try {
				if(httpclient != null)
					httpclient.close();
			} catch(Exception e) {
				throw e;
			}
			
		}
		return result;
	}
	
	
	public static String post(String url, JSONObject params, String charset) throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String result = "";
		try {
			 HttpPost httppost = new HttpPost(url);
			 StringEntity entity = new StringEntity(params.toJSONString(),"utf-8");//解决中文乱码问题 
			 httppost.setHeader("Content-Type", "application/json; charset=utf-8");
			 httppost.setEntity(entity);
			 CloseableHttpResponse response = httpclient.execute(httppost);
			 if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				 result = EntityUtils.toString(response.getEntity(), charset);	 
			 }
			 System.out.println("随银付请求报文:" + params.toJSONString());
			 System.out.println("随银付响应报文：" + result);
		} catch(Exception e) {
			throw e;
		} finally {
			try {
				if(httpclient != null)
					httpclient.close();
			} catch(Exception e) {
				throw e;
			}
			
		}
		return result;
	}
	
	public static void main(String[] args) throws Exception {
		JSONObject map = new JSONObject();
		map.put("bank_branch", "宿迁宿豫支行营业室");
		String result = post("http://127.0.0.1:8081/cash/cash_swiftpayCash.action", map, "utf-8");
		System.out.println(result);
	}
}
