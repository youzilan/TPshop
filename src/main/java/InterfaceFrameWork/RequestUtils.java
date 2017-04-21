package InterfaceFrameWork;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.*;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by hy on 2017/4/15.
 */
public class RequestUtils {
    static HttpClientBuilder builder = HttpClients.custom()
            .disableAutomaticRetries() //关闭自动处理重定向
            .setRedirectStrategy(new LaxRedirectStrategy());//利用LaxRedirectStrategy处理POST重定向问题
    static CloseableHttpClient httpClient = builder.build();

    static CookieStore cookieStore = new BasicCookieStore();

    // Create local HTTP context
    static HttpClientContext localContext = HttpClientContext.create();


    private static String get(String url,String cookies){
        localContext.setCookieStore(cookieStore);
        String stringResponse=null;
        //判空
        if (url!=null){
            //去除空格
            if (url.contains(" ")){
                url.trim();
            }

            HttpGet httpGet=new HttpGet(url);
            if (cookies!=null){
                httpGet.addHeader("Cookie",cookies);
            }
            try {
                CloseableHttpResponse response=httpClient.execute(httpGet);
                if (response==null) {
                    System.out.println("响应为空");
                    return null;
                }else{
                    if(response.getStatusLine().getStatusCode()==200){
                        if (response.getEntity().getContentLength()==0){
                            System.out.println("响应实体为空");
                            return null;
                        }else{
                            HttpEntity entity = response.getEntity();
                            stringResponse = EntityUtils.toString(entity);
                            return stringResponse;
                        }
                    }else{
                        System.out.println("响应错误：状态码为"+response.getStatusLine().getStatusCode());
                        return null;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("url为空");
            return null;
        }
        return stringResponse;

    }
    public static String get(String url,String cookies ,List<NameValuePair>...params){
        localContext.setCookieStore(cookieStore);
        String response=null;
        if (params.length==0){
            response=get(url,cookies);
            return response;
        }
        else{
            url=url+"?";
            for (int i=0;i<params[0].size();i++){
                url=url+params[0].get(i)+"&";
            }
            System.out.println(url);
            response=get(url,cookies);
            return response;
        }
    }
    public static String post(String url,String cookies ,List<NameValuePair>...params){
        localContext.setCookieStore(cookieStore);
        CloseableHttpResponse response=null;
        String stringResponse=null;
        if (url==null){
            System.out.println("url为空");
            return  null;
        }else{
            if (url.contains(" ")){
                url.trim();
            }
            if (params.length==0){
                System.out.println("请求实体为空");
                return null;
            }else{
                HttpPost httpPost=new HttpPost(url);
                //创建一个值对类型的实体
                UrlEncodedFormEntity entity=new UrlEncodedFormEntity(params[0], Consts.UTF_8);
                httpPost.setEntity(entity);
                if (cookies!=null){
                    httpPost.addHeader("Cookie",cookies);
                }
                try {
                    response=httpClient.execute(httpPost);
                    if (response==null){
                        System.out.println("响应为空");
                        return null;
                    }else {
                        if (response.getStatusLine().getStatusCode()==200){
                            if (response.getEntity().getContentLength()==0){
                                System.out.println("响应实体为空");
                                return null;
                            }else{
                                HttpEntity entityResponse=response.getEntity();
                                stringResponse=EntityUtils.toString(entityResponse);
                                return stringResponse;
                            }
                        }else{
                            System.out.println("响应出错：状态码为"+response.getStatusLine().getStatusCode());
                            return null;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }
        return stringResponse;
    }
    public static String post(String url, File file, String fileName){
        CloseableHttpResponse response=null;
        String stringResponse=null;
        if (url==null){
            System.out.println("url为空");
            return null;
        }else{
            if (url.contains(" ")){
                url.trim();
            }
            if (file==null||fileName==null){
                System.out.println("file为空或者fileName为空");
                return null;
            }else {
                HttpPost httpPost=new HttpPost(url);
                MultipartEntityBuilder builder=MultipartEntityBuilder.create();
                HttpEntity entity=builder.addBinaryBody(fileName,file).build();
                httpPost.setEntity(entity);
                try {
                    response=httpClient.execute(httpPost);
                    if (response==null){
                        System.out.println("响应为空");
                        return null;
                    }else{
                        if (response.getStatusLine().getStatusCode()==200){
                            if (response.getEntity().getContentLength()==0){
                                System.out.println("响应实体为空");
                                return null;
                            }else{
                                HttpEntity entityResponse=response.getEntity();
                                stringResponse=EntityUtils.toString(entityResponse);
                                return stringResponse;
                            }
                        }else{
                            System.out.println("响应出错，状态码为："+response.getStatusLine().getStatusCode());
                            return null;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    public static String post(String url,String jsonObject){
        CloseableHttpResponse response=null;
        String stringResponse=null;
        if (url==null){
            System.out.println("url为空");
            return  null;
        }else{
            if (url.contains(" ")){
                url.trim();
            }
            HttpPost httpPost=new HttpPost(url);
            if (jsonObject==null){
                System.out.println("发送的字符串实体为空");
            }else {
                try {
                    StringEntity stringEntity=new StringEntity(jsonObject);
                    stringEntity.setContentEncoding("utf-8");
                    stringEntity.setContentType("application/json");

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            try {
                response=httpClient.execute(httpPost);
                if (response==null){
                    System.out.println("响应为空");
                    return null;
                }else{
                    if (response.getStatusLine().getStatusCode()==200){
                        if (response.getEntity().getContentLength()==0){
                            System.out.println("响应实体为空");
                            return null;
                        }else{
                            HttpEntity entity=response.getEntity();
                            stringResponse=EntityUtils.toString(entity);
                            return stringResponse;
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return stringResponse;
    }
    //put、delete方法与get方法一样
    public static String put(String url){
        CloseableHttpResponse response=null;
        String stringResponse=null;
        if (url==null){
            System.out.println("url为空");
            return null;
        }
        else{
            if (url.contains(" ")){
                url.trim();
            }
            HttpPut httpPut=new HttpPut(url);
            try {
                response=httpClient.execute(httpPut);
                if (response==null){
                    System.out.println("响应为空");
                    return null;
                }else{
                    if (response.getStatusLine().getStatusCode()==200){
                        if (response.getEntity().getContentLength()==0){
                            System.out.println("响应实体为空");
                            return null;
                        }else{
                            HttpEntity entity=response.getEntity();
                            stringResponse=EntityUtils.toString(entity);
                            return stringResponse;
                        }
                    }else{
                        System.out.println("响应错误，状态码为："+response.getStatusLine().getStatusCode());
                        return null;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return stringResponse;
    }
    //delete
    public static  String delete(String url){
        CloseableHttpResponse response=null;
        String stringResponse=null;
        if (url==null){
            System.out.println("url为空");
            return null;
        }
        else{
            if (url.contains(" ")){
                url.trim();
            }
            HttpDelete httpDelete=new HttpDelete(url);
            try {
                response=httpClient.execute(httpDelete);
                if (response==null){
                    System.out.println("响应为空");
                    return null;
                }else{
                    if (response.getStatusLine().getStatusCode()==200){
                        if (response.getEntity().getContentLength()==0){
                            System.out.println("响应实体为空");
                            return null;
                        }else{
                            HttpEntity entity=response.getEntity();
                            stringResponse=EntityUtils.toString(entity);
                            return stringResponse;
                        }
                    }else{
                        System.out.println("响应错误，状态码为："+response.getStatusLine().getStatusCode());
                        return null;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return stringResponse;
    }

}
