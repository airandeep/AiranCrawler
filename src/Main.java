
import model.Zhihu;
import spider.Spider;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        String url = "https://www.zhihu.com/explore/recommendations";
        String source = Spider.sendGet(url);
        Date date;
        //SimpleDateFormat formatter = new SimpleDateFormat("yyyy年-MM月dd日-HH时mm分ss秒");
        SimpleDateFormat formatter = new SimpleDateFormat("HH时mm分ss秒");


        //long currentTime = System.currentTimeMillis();
        date = new Date(System.currentTimeMillis());
        String currentTime = formatter.format(date);
        long c = System.currentTimeMillis();

        ArrayList<Zhihu> results;
        results = Spider.zhihuRegexString(source);

        date = new Date(System.currentTimeMillis());
        String endTime = formatter.format(date);
        long e = System.currentTimeMillis();

        System.out.println("开始爬取时间:"+currentTime+" 结束爬取时间:"+endTime);
        System.out.println("\n\n");//换两行
        //System.out.println(c-e);
        System.out.println(results);

    }
}
