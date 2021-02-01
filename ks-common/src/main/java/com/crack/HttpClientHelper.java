package com.crack;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.crack.Resources;
import com.crack.UtilConfig;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.google.common.base.Strings;
import top.ks.common.util.LogFormat;
import top.ks.common.util.ToolUtil;

/**
 * Function: TODO
 *
 * ClassName:HttpClientHelper Date: 2014-9-9 下午02:36:00
 *
 * @author 世银
 * @version
 * @since JDK 1.6 Copyright (c) 2014, palm-commerce All Rights Reserved.
 */
@SuppressWarnings("deprecation")
public class HttpClientHelper {

	private static final Log log = LogFactory.getLog(HttpClientHelper.class);

	private static final String DEFAULT_CHARSET = "utf-8";

	public static String sendHttp(String url) {

		return sendHttp(url, null, null, null);
	}

	/**
	 * 该方法会自动设置 消息头 "Content-Type", "application/json" 可能会导致有时候发送数据错误 use
	 * sendHttp(String url, Map<String, String> params, String content,
	 * Map<String, String> headers)
	 *
	 * @param url
	 * @param params
	 * @param content
	 * @return
	 */
	@Deprecated
	public static String sendHttp(String url, Map<String, String> params, String content) {

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");

		return sendHttp(url, params, content, headers);
	}

	/**
	 *
	 * @Title: sendHttpXmlOrTxt
	 * @Description: 请求格式为xml
	 * @param: @param url
	 * @param: @param params
	 * @param: @param content
	 * @param: @return
	 * @return: String
	 * @author:  zhanghuanhuan
	 * @date: 2018年1月15日 上午11:34:25
	 * @throws
	 */
	public static String sendHttpXmlOrTxt(String url, Map<String, String> params, String content){
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "txt/mxl");

		return sendHttp(url, params, content, headers,DEFAULT_CHARSET);
	}

	public static String sendHttp(String url, Map<String, String> params, String content, Map<String, String> headers) {

		return sendHttp(url, params, content, headers, DEFAULT_CHARSET);
	}

	public static String sendHttp(String url, Map<String, String> params, String content, Map<String, String> headers,
								  String charset){

		return sendHttp(url, params, content, headers, charset, "POST");
	}

	public static String sendHttp(String url, Map<String, String> params, String content, Map<String, String> headers,
								  String charset, String httpMethod) {

		log.info(LogFormat.formatMsg("HttpClientHelper.sendHttps", url,
				"send https start.content:" + content + ",params:" + params));

		CloseableHttpClient client = HttpClientBuilder.create().build();

		String result = null;

		HttpRequestBase httpRequest = null;

		CloseableHttpResponse tokenResponse = null;

		try {
			// set proxy in create request and content
			httpRequest = createRequest(url, httpMethod, content, params, charset);

			if (headers != null && !headers.isEmpty()) {
				for (Entry<String, String> entry : headers.entrySet()) {
					httpRequest.addHeader(entry.getKey(), entry.getValue());
				}
			}

			tokenResponse = client.execute(httpRequest);

			int statusCode = tokenResponse.getStatusLine().getStatusCode();

			if(statusCode != 200){

				log.error(LogFormat.formatMsg("HttpClientHelper.sendHttps", "", "reveive wrong,status code:" + statusCode));
				return null;
			}

			HttpEntity entity = tokenResponse.getEntity();

			byte[] data = EntityUtils.toByteArray(entity);

			result = new String(data,charset == null ? DEFAULT_CHARSET : charset);

			log.info(LogFormat.formatMsg("HttpClientHelper.sendHttps", "", "receive:" + result));

		} catch (Exception e) {

			log.error(LogFormat.formatMsg("HttpClientHelper.sendHttps", "", "exception."), e);

		} finally {

			if (httpRequest != null) {
				httpRequest.releaseConnection();
			}

			if (tokenResponse != null) {
				try {
					tokenResponse.close();
				} catch (IOException e) {
					log.error(LogFormat.formatMsg("HttpClientHelper.sendHttp", "", "response.close."), e);
				}
			}

			if (client != null) {
				try {
					client.close();
				} catch (IOException e) {
					log.error(LogFormat.formatMsg("HttpClientHelper.sendHttp", "", "client.close."), e);
				}
			}


		}

		return result;
	}

	private static HttpRequestBase createRequest(String url, String method, String strContent, Map<String,String> params, String charset) throws UnsupportedEncodingException {

		HttpRequestBase request = null;

		if(method == null){

			method = "POST";
		}

		if ("POST".equals(method)) {

			request = new HttpPost(url);
			if (!Strings.isNullOrEmpty(strContent)) {

				((HttpPost) request).setEntity(new StringEntity(strContent, "UTF-8"));
			}

			addParams((HttpPost) request, params, charset);

		} else if ("PUT".equals(method)) {

			request = new HttpPut(url);
			if (!Strings.isNullOrEmpty(strContent)) {

				((HttpPut) request).setEntity(new StringEntity(strContent, "UTF-8"));
			}

		} else if ("GET".equals(method)) {

			request = new HttpGet(url);

		} else if ("DELETE".equals(method)) {

			request = new HttpDelete(url);
		}

		if (request == null) {

			return null;
		}

		Builder builder = RequestConfig.custom().setConnectTimeout(30000).setConnectionRequestTimeout(10000).setSocketTimeout(60000).setProxy(getHttpHost(url));

		request.setConfig(builder.build());

		return request;
	}

	private static void addParams(HttpEntityEnclosingRequestBase httpRequest,Map<String,String> params,String charset) throws UnsupportedEncodingException{

		if (params != null && !params.isEmpty()) {

			Set<Entry<String, String>> paramsMap = params.entrySet();

			List<NameValuePair> nvps = new ArrayList<NameValuePair>();

			for (Entry<String, String> entry : paramsMap) {

				nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}

			httpRequest.setEntity(new UrlEncodedFormEntity(nvps, charset));
		}
	}

	// 根据配置设置代理
	private static HttpHost getHttpHost(String url) {

		String host = UtilConfig.getInstance().getProperty("http.proxyhost");

		if (Strings.isNullOrEmpty(host)) {

			return null;
		}

		// String scheme = Strings.isNullOrEmpty(url) ? "http" :
		// (url.toLowerCase().startsWith("https") ? "https" : "http");

		int port = ToolUtil.intVal(UtilConfig.getInstance().getProperty("http.proxyport", "80"));

		log.info(LogFormat.formatMsg("getHttpHost", "", "is use http proxy,ip:" + host + ",port:" + port));

		return new HttpHost(host, port);
	}

	public static String sendHttpStream(String url, InputStream stream) {

		log.info(LogFormat.formatMsg("HttpClientHelper.sendHttpStream", url, "send https start."));

		String charset = "UTF-8";
		HttpClient client = getClient(true);

		String result = null;

		HttpPost httpPost = null;

		try {

			httpPost = new HttpPost(url);

			if (stream != null) {

				httpPost.setEntity(new InputStreamEntity(stream));
			}

			Builder builder = RequestConfig.custom().setConnectTimeout(30000).setConnectionRequestTimeout(10000).setSocketTimeout(60000).setProxy(getHttpHost(url));
			httpPost.setConfig(builder.build());

			HttpResponse tokenResponse = client.execute(httpPost);

			HttpEntity entity = tokenResponse.getEntity();

			result = EntityUtils.toString(entity, charset);

			log.info(LogFormat.formatMsg("HttpClientHelper.sendHttps", "", ",receive:" + result));

		} catch (Exception e) {

			log.error(LogFormat.formatMsg("HttpClientHelper.sendHttps", "", "exception."), e);

		} finally {

			if (httpPost != null) {

				httpPost.releaseConnection();
			}



		}

		return result;
	}

	public static String sendHttps(String url, Map<String, String> params, String content) {

		return sendHttp(url, params, content, null);
	}

	public static String sendHttps(String url, Map<String, String> params, String content, String p12Path, String p12Pwd) {

		log.info(LogFormat.formatMsg("HttpClientHelper.sendHttps", url, "send https start.content:" + content));

		CloseableHttpClient client = null;

		String result = null;

		HttpPost method = null;

		CloseableHttpResponse response = null;

		try {

			client = createHttpClient(p12Path, p12Pwd);

			RequestConfig reqConfig = RequestConfig.custom().setConnectTimeout(30000).setConnectionRequestTimeout(10000).setProxy(getHttpHost(url)).setSocketTimeout(60000).setStaleConnectionCheckEnabled(true).build();

			method = new HttpPost(url);

			method.setConfig(reqConfig);

			if (params != null && !params.isEmpty()) {

				Set<Entry<String, String>> paramsMap = params.entrySet();

				List<NameValuePair> nvps = new ArrayList<NameValuePair>();

				for (Entry<String, String> entry : paramsMap) {

					nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
				}

				method.setEntity(new UrlEncodedFormEntity(nvps));
			}

			if (!Strings.isNullOrEmpty(content)) {

				method.setEntity(new StringEntity(content, "utf-8"));
			}

			response = client.execute(method);

			int status = response.getStatusLine().getStatusCode();

			HttpEntity entity = response.getEntity();

			if (status == 200) {

				result = EntityUtils.toString(entity, "utf-8");
			}

			EntityUtils.consume(entity);

			log.info(LogFormat.formatMsg("HttpClientHelper.sendHttps", "", "status:" + status + ",receive:" + result));

		} catch (Exception e) {

			log.error(LogFormat.formatMsg("HttpClientHelper.sendHttps", "", "exception."), e);

		} finally {

			if (response != null) {

				try {
					response.close();
				} catch (Exception e) {

					log.error(LogFormat.formatMsg("HttpClientHelper.sendHttps", "", "response.close."), e);
				}
			}

			if (client != null) {

				try {
					client.close();
				} catch (Exception e) {

					log.error(LogFormat.formatMsg("HttpClientHelper.sendHttps", "", "client.close."), e);
				}
			}
		}

		return result;
	}

	private static CloseableHttpClient createHttpClient(String p12Path, String p12Pwd) throws Exception {

		if (hasEmptyStr(p12Path, p12Pwd)) {

			return HttpClients.createDefault();
		}

		KeyStore keyStore = KeyStore.getInstance("PKCS12");
		InputStream instream = Resources.getResourceAsStream(p12Path);

		try {
			keyStore.load(instream, p12Pwd.toCharArray());
		} finally {
			instream.close();
		}
		// Trust own CA and all self-signed certs
		SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, p12Pwd.toCharArray()).build();
		// Allow TLSv1 protocol only
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null,
				SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
		CloseableHttpClient httpclient = HttpClients.custom().setMaxConnPerRoute(20).setMaxConnTotal(100).setSSLSocketFactory(sslsf).build();

		return httpclient;
	}

	private static boolean hasEmptyStr(String... strings) {

		boolean result = false;

		if (strings == null) {

			return result;
		}

		for (int i = 0; i < strings.length; i++) {

			result = Strings.isNullOrEmpty(strings[i]);
			if (result) {

				break;
			}
		}

		return result;
	}

	/**
	 * Create a httpClient instance
	 *
	 * @param isSSL
	 * @return HttpClient instance
	 */
	public static HttpClient getClient(boolean isSSL) {

		HttpClient httpClient = HttpClientBuilder.create().build();
		if (isSSL) {
			X509TrustManager xtm = new X509TrustManager() {

				@Override
				public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				}

				@Override
				public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				}

				@Override
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			};

			try {
				SSLContext ctx = SSLContext.getInstance("TLS");

				ctx.init(null, new TrustManager[] { xtm }, null);

				SSLSocketFactory socketFactory = new SSLSocketFactory(ctx);

				httpClient.getConnectionManager().getSchemeRegistry().register(new Scheme("https", 443, socketFactory));

			} catch (Exception e) {
				throw new RuntimeException();
			}
		}

		return httpClient;
	}

	public static void main(String[] args) {

		String content = sendHttps("https://www.baidu.com", null, null);
		System.out.println(content);
		String charset = "utf-8";
		String url = "http://i.sporttery.cn/odds_calculator/get_odds?i_format=json&i_callback=getData&poolcode=had&_=170323192311100001";
		content = sendHttp(url, null, null, null, charset, "GET");

		System.out.println(content);
	}

	public static int intVal(String num) {
		try {
			if (num == null) {
				return 0;
			}
			int value = Integer.parseInt(num);
			return value;
		} catch (Exception e) {
			return 0;
		}

	}
}
