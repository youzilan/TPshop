package Interfaces;



import org.testng.log4testng.Logger;

import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by hy on 2017/4/20.
 */
public class getHash {
    private static Logger logger=Logger.getLogger(getHash.class);
    //config:properties文件名
    private static ResourceBundle rb=ResourceBundle.getBundle("TPshopConfig");
    //host.url：properties类型文件中的key值

    public final static String HOST_URL_GETHASH=rb.getString("tpshop.host.gethash.url");

    public static String gethash(Map<String,String> map){
        String stringResponse = null;
        String uri=null;
        String cookies=null;
        if (map==null){
            logger.error("数据为空");
            return null;
        }else {
            uri=HOST_URL_GETHASH;
            cookies=map.get("cookies");
            stringResponse=InterfaceFrameWork.RequestUtils.get(uri,cookies);
            if (stringResponse==null){
                logger.error("获取hash响应信息为空");
                return null;
            }
        }
        return stringResponse;
    }
}
