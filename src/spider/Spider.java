package spider;

import model.Zhihu;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 1.获取网页源码
 * 2.将与正则表达式匹配的字符串封装到Zhihu对象中，然后将Zhihu对象添加至泛型ArrayList中。
 * Created by codingBoy on 16/10/20.
 */
public class Spider
{

    public  static String sendGet(String url)//静态方法，直接类名.方法可以直接调用
    {

        BufferedReader reader=null;

        String result="";

        try {
            URL realUrl = new URL(url);
            URLConnection connection = realUrl.openConnection();

            reader=new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));

            String line;
            while ((line = reader.readLine()) != null) {
                result+=line;
            }

            return result;
        }catch (Exception e)
        {
            throw  new RuntimeException("get方法请求错误"+e);
        }finally {
            try {
                if (reader != null) reader.close();
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public static ArrayList<Zhihu> zhihuRegexString(String targetStr)
    {
        ArrayList<Zhihu> lists=new ArrayList<>();



        Pattern urlPattern=Pattern.compile("<h2>.+?question_link.+?href=\"(.+?)\".+?</h2>");//匹配url地址
        Matcher urlMatcher=urlPattern.matcher(targetStr);


        //group是针对（）来说的，group（0）就是指的整个串，group（1） 指的是第一个括号里的东西，group（2）指的第二个括号里的东西。
        while (urlMatcher.find())
        {
            //String test = urlMatcher.group(0);
            Zhihu zhihu=new Zhihu("https://www.zhihu.com"+urlMatcher.group(1));


            lists.add(zhihu);
        }
        return lists;
    }
}
