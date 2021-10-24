package com.company;
import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import com.google.gson.*;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            WebscraperDataBind(WebscraperConnectModule());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Document WebscraperConnectModule() throws IOException {
        String url = "http://myapps.iium.edu.my/StudentOnline/schedule1.php?action=view&view=50&kuly=ENGIN&tot_pages=11&ctype=%3C&course=&sem=1&ses=2021/2022";
        File input = new File("C:\\Users\\AIMAN RAHIM\\IdeaProjects\\untitled5\\src\\com\\company\\cs.html");
        Document document = Jsoup.parse(input,"UTF-8","http://myapps.iium.edu.my/StudentOnline/schedule1.php?action=view&view=50&kuly=ENGIN&tot_pages=11&ctype=%3C&course=&sem=1&ses=2020/2021");
        return document;
    }

    public static void WebscraperDataBind(Document document){
       Elements elements = document.getElementsByTag("tbody").get(1).children();
       for(var i =0;i<elements.size()-3;i++) {
           String code = elements.get(2 + i).getElementsByTag("td").get(0).text();
           String sect = elements.get(2 + i).getElementsByTag("td").get(1).text();
           String Title = elements.get(2 + i).getElementsByTag("td").get(2).text();
           String chr = elements.get(2 + i).getElementsByTag("td").get(3).text();
           Elements data = elements.get(2 + i).getElementsByTag("td").get(4).getElementsByTag("tbody");
           for(Element lol : data){
               System.out.println(lol.children().);
           }
           System.out.println(code);
           System.out.println(sect);
           System.out.println(Title);
       }
    }
}
