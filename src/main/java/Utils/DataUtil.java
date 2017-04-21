package Utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by hy on 2017/4/15.
 */
public class DataUtil {
    //解析json数据：字符串转换成json数据：
    private static JSONObject stringToJson(String string){
        if (string.equals("")){
            System.out.println("传入的参数为空");
            return null;
        }else{
            return JSON.parseObject(string);
        }
    }
    //私有方法：解析json数据
    private static String JSONParse(JSONObject jsonObject,String path){
        //==和equals的区别：
        if (path==null||path.equals("")){
            System.out.println("path为空，返回原来的json数据");
            return jsonObject.toString();
        }else if (jsonObject==null){
            System.out.println("传入的参数为空");
            return null;
        }else {
            //解析json数据
            return JSONPath.eval(jsonObject,path).toString();
        }
    }
    //对外提供的json数据解析方法：
    public static String JSONParse(String jsonObject,String path){
        //调用本类的私有的静态方法stringToJson()和JSONParse(),解析json数据:
        return JSONParse(stringToJson(jsonObject),path);
    }
    //字符串转HTML
    public static Document stringToHTML(String html){
        if (html==null||html.equals("")){
            return null;
        }else{
            return Jsoup.parse(html);
        }
    }
    //url  -> HTML
    public static Document urlToHTML(String url) throws IOException {
        if (url==null||url.equals("")){
            return null;
        }else{
            return Jsoup.connect(url).get();
        }
    }
    //解析HTML,获取属性值
    private static String htmlParseAttr(Document document,String path,String key){
        if (path==null||path.equals("")){
            return document.toString();
        }else{
            //用选择器解析HTML
            return   document.select(path).attr(key);
        }
    }
    //解析HTML,获取text文本
    private static String htmlParseText(Document document,String path){
        if (path==null||path.equals("")){
            return document.toString();
        }else{
            //用选择器解析HTML
            return   document.select(path).text();
        }
    }
    //对外提供的解析HTML方法
    public static String htmlParse(String document,String path,String...key){
        //通过判断可选参数key的长度是否为0，从而确定是获取属性值还是text文本值
        if (key.length==0){
            //获取HTML中的文本
            return htmlParseText(stringToHTML(document),path);
        }else{
            //获取属性
            return htmlParseAttr(stringToHTML(document),path,key[0]);
        }
    }
}
