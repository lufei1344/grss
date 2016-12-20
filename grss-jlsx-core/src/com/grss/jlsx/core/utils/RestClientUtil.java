package com.grss.jlsx.core.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.grss.jlsx.core.enums.HTTPMethod;
import com.grss.jlsx.core.prove.Credential;
import com.grss.jlsx.core.prove.Token;
import com.grss.jlsx.core.wx.constant.WXConstant;

public class RestClientUtil {
	private static final String CHARSET = "UTF-8";
	
	/**
	 * get请求
	 * @param pathUrl
	 * @return
	 */
	public static String get(String pathUrl) {
		return request("GET", pathUrl,null);
	}
	
	/**
	 * post请求
	 * @param pathUrl
	 * @param xmlData
	 * @return
	 */
	public static String post(String pathUrl,String xmlData) {
		return request("POST", pathUrl,xmlData);
	}
	
	private static String request(String method, String pathUrl,String xmlData) {
		try {
			final URL url = new URL(pathUrl);
			HttpURLConnection connection = null;
			if(pathUrl.contains("https")){
				connection = (HttpsURLConnection) url.openConnection();
			}else{
				connection = (HttpURLConnection) url.openConnection();
			}
			connection.setRequestMethod(method);
			connection.setRequestProperty("Content-type", "application/xml");
			connection.setRequestProperty("Accept-Charset", "utf-8");
			connection.setRequestProperty("contentType", "utf-8");
			connection.setDoOutput(true);
			if (xmlData != null) {
				final OutputStream outputStream = connection.getOutputStream();
				writeOutput(outputStream, xmlData);
				outputStream.close();
			}
			System.out.println("responseCode =" + connection.getResponseCode());
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				return readInput(connection.getInputStream());
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static void writeOutput(final OutputStream outputStream, final String xmlData) throws Exception {
		ByteArrayInputStream inputStram = new ByteArrayInputStream(xmlData.getBytes(CHARSET));

		final byte[] buffer = new byte[1024];
		int length = 0;
		while ((length = inputStram.read(buffer)) != -1) {
			outputStream.write(buffer, 0, length);
		}
	}
	
	public static String readInput(final InputStream is) {

		BufferedReader reader = null;
		StringBuilder content = new StringBuilder();
		try {
			reader = new BufferedReader(new InputStreamReader(is,CHARSET));
			String line = "";
			while ((line = reader.readLine()) != null) {
				content.append(line);
				content.append("\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
					reader = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return content.toString();
	}
	/**
	 * 
	 * @comment httpclient post请求
	 * @param url
	 * @param params
	 * @return
	 * 2015年12月22日
	 * 下午6:27:11
	 */
	@SuppressWarnings("deprecation")
	public static String post(String url, Map<String, String> params) {  
		HttpClient httpclient = getClient(false); 
        String body = null;  
          
        HttpPost post = postForm(url, params);  
          
        body = invoke(httpclient, post);  
          
        httpclient.getConnectionManager().shutdown();  
          
        return body;  
    }
	
	public static String postFile(String url, Map<String, Object> params){
		HttpClient httpclient = getClient(false); 
        String body = null;  
        HttpPost post = postFileForm(url, params);  
        body = invoke(httpclient, post);  
        httpclient.getConnectionManager().shutdown();  
        return body;  
	}
	/**
	 * 
	 * @comment 调用执行发送
	 * @param httpclient
	 * @param httpost
	 * @return
	 * 2015年12月24日
	 * 上午9:04:58
	 */
	private static String invoke(HttpClient httpclient,  
            HttpUriRequest httpost) {  
          
        HttpResponse response = sendRequest(httpclient, httpost);  
        String body = paseResponse(response);  
          
        return body;  
    }
	/**
	 * 
	 * @comment 解析返回信息
	 * @param response
	 * @return
	 * 2015年12月24日
	 * 上午9:04:24
	 */
	private static String paseResponse(HttpResponse response) {  
        HttpEntity entity = response.getEntity();  
          
          
        String body = null;  
        try {  
            body = EntityUtils.toString(entity,"UTF-8");  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
          
        return body;  
    }
	/**
	 * 
	 * @comment 执行发送请求
	 * @param httpclient
	 * @param httpost
	 * @return
	 * 2015年12月24日
	 * 上午9:03:13
	 */
	private static HttpResponse sendRequest(HttpClient httpclient,  
            HttpUriRequest httpost) {  
        HttpResponse response = null;  
          
        try {  
            response = httpclient.execute(httpost);  
        } catch (ClientProtocolException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return response;  
    }
	/**
	 * 
	 * @comment 装载请求信息
	 * @param url
	 * @param params
	 * @return
	 * 2015年12月24日
	 * 上午9:03:28
	 */
	private static HttpPost postForm(String url, Map<String, String> params){  
        
        HttpPost httpost = new HttpPost(url);  
        List<NameValuePair> nvps = new ArrayList <NameValuePair>();  
          
        Set<String> keySet = params.keySet();  
        for(String key : keySet) {  
            nvps.add(new BasicNameValuePair(key, params.get(key)));  
        }  
          
        try {  
            httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
          
        return httpost;  
    } 
	
	@SuppressWarnings({ "unused", "deprecation" })
	private static HttpPost postFileForm(String url,Map<String, Object> params) {
		HttpPost httpost = new HttpPost(url);
		MultipartEntity entity = new MultipartEntity();
		Set<String> keySet = params.keySet();
		for (String key : keySet) {
			Object obj = params.get(key);
			if(obj instanceof File){
				entity.addPart(key, new FileBody((File)obj));
			}else if(obj instanceof String){
				try {
					entity.addPart(key, new StringBody(obj.toString(), Charset.defaultCharset()));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			
		}
		httpost.setEntity(entity);

		return httpost;
	}
	private static void postFileForm2(String url,Map<String, String> params){
		PostMethod filePost = new PostMethod(url);
		try {
			Part[] parts = {new FilePart("images", new File("D:/var/static/1.jpg")),new FilePart("images", new File("D:/var/static/4.jpg")),new FilePart("images", new File("D:/var/static/3.jpg")),new FilePart("images", new File("D:/var/static/2.jpg"))}; 
			int i = parts.length;
			Part[] parts2 = new Part[params.size() + parts.length];
			for(int j = 0; j < parts.length; j++){
				parts2[j] = parts[j];
			}
		   for(String key : params.keySet()){
			   parts2[i] = new StringPart(key,StringUtil.encodingToUTF8(params.get(key)));
				i++;
			}

			filePost.setRequestEntity(new MultipartRequestEntity(parts2,filePost.getParams()));
			 org.apache.commons.httpclient.HttpClient client = new org.apache.commons.httpclient.HttpClient();
			 client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
			 int status = client.executeMethod(filePost);
			 if (status == HttpStatus.SC_OK){
				 System.out.println("上传成功");
			 }else{
				 System.out.println("上传失败");
			 }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static HttpClient getClient(boolean isSSL) {

		HttpClient httpClient = new DefaultHttpClient();
		if (isSSL) {
			X509TrustManager xtm = new X509TrustManager() {
				public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				}

				public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				}

				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			};

			try {
				SSLContext ctx = SSLContext.getInstance("TLS");

				ctx.init(null, new javax.net.ssl.TrustManager[]{xtm}, null);

				org.apache.http.conn.ssl.SSLSocketFactory socketFactory = new org.apache.http.conn.ssl.SSLSocketFactory(ctx);

				httpClient.getConnectionManager().getSchemeRegistry().register(new Scheme("https", 443, socketFactory));

			} catch (Exception e) {
				throw new RuntimeException();
			}
		}

		return httpClient;
	}
	public static HttpClient getClient(String certPath,String certPassword){
		CloseableHttpClient httpclient;
		try {
			KeyStore keyStore  = KeyStore.getInstance("PKCS12");
			FileInputStream instream = new FileInputStream(new File(certPath));
			try{
				keyStore.load(instream,certPassword.toCharArray());
			}finally{
				instream.close();
			}
			SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore,certPassword.toCharArray()).build(); 
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext,new String[] { "TLSv1" },null,SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);    
			httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		return httpclient;
	}
	private static String send(HttpClient httpClient,URL url, Credential credential, Object dataBody, HTTPMethod method) {
		String result = null;
		try {

			HttpResponse response = null;
			switch (method) {
			case POST:
				HttpPost httpPost = new HttpPost(url.toURI());
				if (credential != null) {
					Token.applyAuthentication(httpPost, credential);
				}
				httpPost.setEntity(new StringEntity(dataBody.toString(), "UTF-8"));
				response = httpClient.execute(httpPost);
				break;
			case PUT:
				HttpPut httpPut = new HttpPut(url.toURI());
				if (credential != null) {
					Token.applyAuthentication(httpPut, credential);
				}
				httpPut.setEntity(new StringEntity(dataBody.toString(), "UTF-8"));
				response = httpClient.execute(httpPut);
				break;
			case DELETE:
				HttpDelete httpDelete = new HttpDelete(url.toURI());
				if (credential != null) {
					Token.applyAuthentication(httpDelete, credential);
				}
				response = httpClient.execute(httpDelete);
				break;
			case GET:
				HttpGet httpGet = new HttpGet(url.toURI());
				if (credential != null) {
					Token.applyAuthentication(httpGet, credential);
				}
				response = httpClient.execute(httpGet);
				break;
			
			}
			result = paseResponse(response);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		return result;
	}
	public static String sendHTTPRequest(HTTPMethod method, URL url,String xmlData,String certPath,String certPassword){
		return send(getClient(certPath, certPassword), url, null, xmlData, method);
	}
	
	public static String sendHTTPRequest(HTTPMethod method, String addressUrl,String xmlData,String certPath,String certPassword){
		try {
			return sendHTTPRequest(method,new URL(addressUrl),xmlData,certPath,certPassword);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static String sendHTTPRequest(URL url, Credential credential, Object dataBody, HTTPMethod method) {
		HttpClient httpClient = getClient(true);
		return send(httpClient,url, credential, dataBody, method);
		
	}
	
	public static void main(String[] args) {
	}
}
