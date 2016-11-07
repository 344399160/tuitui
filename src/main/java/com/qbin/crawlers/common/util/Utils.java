package com.qbin.crawlers.common.util;

import org.apache.commons.lang.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述：TODO
 * author qiaobin   2016/10/12 18:53.
 */
public class Utils {
    static HttpClientContext context = HttpClientContext.create();
  //http get
  public  static String get(String url){
      try {
          RequestConfig defaultRequestConfig = RequestConfig.custom()
                  .setSocketTimeout(60000)
                  .setConnectTimeout(60000)
                  .setConnectionRequestTimeout(60000)
                  .setStaleConnectionCheckEnabled(false)
                  .setExpectContinueEnabled(false)
                  .build();
          CloseableHttpClient httpclient = HttpClients.custom()
                  .setDefaultRequestConfig(defaultRequestConfig)
                  .build();

          HttpGet httpget = new HttpGet(url);
          CloseableHttpResponse response = httpclient.execute(httpget, context);
          try {
//                System.out.println(response);

              return EntityUtils.toString(response.getEntity());
          } finally {
              response.close();
          }
      } catch (ClientProtocolException e) {
          e.printStackTrace();
      } catch (IOException e) {
          e.printStackTrace();
      }
      return null;
  }
  //http getcookies
  public  static Map<String, String> getCookies(String url){
      try {
          CloseableHttpClient httpclient = HttpClients.createDefault();
          HttpGet httpget = new HttpGet(url);
          CloseableHttpResponse response = httpclient.execute(httpget, context);
          try {
              System.out.println(response);
//                System.out.println(context.getCookieStore());
              CookieStore cs = context.getCookieStore();
              Map<String, String> map = new HashMap<String, String>();
              for(Cookie c : cs.getCookies()){
                  map.put(c.getName(), c.getValue());
              }
              return map;
          } finally {
              response.close();
          }
      } catch (ClientProtocolException e) {
          e.printStackTrace();
      } catch (IOException e) {
          e.printStackTrace();
      }
      return null;
  }

  //down img
  public static String dowmImg(String url){
      try {
          CloseableHttpClient httpclient = HttpClients.createDefault();
          HttpGet httpget = new HttpGet(url);
          CloseableHttpResponse response = httpclient.execute(httpget, context);
          try {
//                System.out.println(response);
              byte [] bs = EntityUtils.toByteArray(response.getEntity());
              File f =new File("img.png");
              OutputStream os = new FileOutputStream(f);
              os.write(bs);
              os.close();
//                return EntityUtils.toString(response.getEntity());
              return f.getAbsolutePath();
          } finally {
              response.close();
          }
      } catch (ClientProtocolException e) {
          e.printStackTrace();
      } catch (IOException e) {
          e.printStackTrace();
      }
      return null;
  }
  //http post
  public static String post(String url, Object ... kvs){
      try {
          List<NameValuePair> formparams = new ArrayList<NameValuePair>();
          for(int i = 0;i < kvs.length/2; i++){
              formparams.add(new BasicNameValuePair(""+kvs[2*i], ""+kvs[2*i + 1]));
          }
          UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
          HttpPost post = new HttpPost(url);
          post.setEntity(entity);

          CloseableHttpClient httpclient = HttpClients.createDefault();
          CloseableHttpResponse response = httpclient.execute(post, context);
          try {
//                System.out.println(response);
              return EntityUtils.toString(response.getEntity());
          } finally {
              response.close();
          }
      } catch (ClientProtocolException e) {
          e.printStackTrace();
      } catch (IOException e) {
          e.printStackTrace();
      }
      return null;
  }

  //http post
      public static String postString(String url, String str){
          try {
              System.setProperty ("jsse.enableSNIExtension", "false");
              HttpPost post = new HttpPost(url);
              if (StringUtils.isNotEmpty(str)) {
                  StringEntity sentity = new StringEntity(str, Consts.UTF_8);
                  post.setEntity(sentity);
              }

              CloseableHttpClient httpclient = HttpClients.createDefault();
              CloseableHttpResponse response = httpclient.execute(post, context);
              try {
//                    System.out.println(response);
                  HttpEntity ent = response.getEntity();
                  String res = "";
                  try {
                      res = EntityUtils.toString(ent, Charset.forName("UTF-8"));
                  } catch (ParseException e) {
                      e.printStackTrace();
                      res = null;
                  }
                  return res;
              } finally {
                  response.close();
              }
          } catch (ClientProtocolException e) {
              e.printStackTrace();
          } catch (IOException e) {
              e.printStackTrace();
          }
          return null;
      }
}
