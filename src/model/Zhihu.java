package model;

import spider.Spider;

import javax.swing.border.MatteBorder;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2018/6/4.
 */
public class Zhihu {
    public String title;
    public String titleDescription;
    public String zhihuUrl;
    public ArrayList<String> answers;



    public Zhihu(String url)
    {
        title="";
        titleDescription="";
        zhihuUrl="";
        answers=new ArrayList<>();

        if (getRealUrl(url))
        {
            System.out.println("正在抓取链接"+zhihuUrl);

            String content=Spider.sendGet(zhihuUrl);

            Pattern pattern;
            Matcher matcher;

            pattern=Pattern.compile("<h1 class=\"QuestionHeader-title\">(.+?)</h1>");
            matcher=pattern.matcher(content);
            if (matcher.find()) {
                title = matcher.group(1);
            }

            pattern=Pattern.compile("<span class=\"RichText ztext\" itemProp=\"text\">(.+?)</span>");//zh-question-detail.+?<div.+?>(.*?)</div>
            matcher=pattern.matcher(content);
            if (matcher.find()) {
                titleDescription=matcher.group(1);
            }

            pattern=Pattern.compile("<a class=\"UserLink-link\" data-za-detail-view-element_name=\"User\" target=\"_blank\" href=\"//(.+?)\">(.+?)</a>");
            matcher=pattern.matcher(content);
            while (matcher.find())
            {
                String test = "回答者链接:" + matcher.group(1) + "\n";
                answers.add(test);
            }

        }
    }

    @Override
    public String toString() {
        return "问题为:"+title+"\n问题描述为:"+titleDescription+
                "\n地址为:"+zhihuUrl+"\n部分回答者链接为:"+answers+"\n\n\n";
    }


    boolean getRealUrl(String url)
    {
        String regex="question/(.+?)/";

        Pattern pattern=Pattern.compile(regex);
        Matcher matcher=pattern.matcher(url);

        while (matcher.find())
        {
            zhihuUrl="https://www.zhihu.com/question/"+matcher.group(1);

            return true;
        }
        return false;
    }
}
