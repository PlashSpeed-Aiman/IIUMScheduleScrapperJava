package com.company;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        String url = "http://albiruni.iium.edu.my/myapps/StudentOnline/schedule1.php?";
        int page_view = 50;
        String kulliyyahStr = "CFL";
        int semesterVal = 2;
        String sessionVal = "2019/2020";
        String url2 = "action=view&view=".concat(String.valueOf(page_view)) + "&kuly=".concat(kulliyyahStr) + "&ctype=%3C&course=&sem=".concat(String.valueOf(semesterVal)) + "&ses=".concat(sessionVal);
        String url3 = url + url2;
        WebScrapper(url3);

    }

    public static void WebScrapper(String url) throws IOException {
        //Tries to connect. Haven't implement the "if it fails" option
        Document document = Jsoup.connect(url).userAgent("Google Chrome").timeout(6000).get();
        Elements subjectCodeRowRough = document.select("body > table:nth-child(4) > tbody > tr:nth-child(n)");//Select the table that contains the Data List
        System.out.println(subjectCodeRowRough.size());
        //For loop eliminates the parts that don't contain the data we need
        for (int i = 3; i < subjectCodeRowRough.size() - 2; i++) {
            Element test = subjectCodeRowRough.get(i);
            String subjectCode = test.select("tr:nth-of-type(n) > td:nth-of-type(1)").first().text();
            System.out.println("Code: " + subjectCode);
            String subjectTitle = test.select("tr:nth-of-type(n) >td:nth-of-type(3)").first().text();
            System.out.println("Title: " + subjectTitle);
            String Section = test.select("tr:nth-of-type(n) >td:nth-of-type(2)").first().text();
            System.out.println("Section:" + Section);
            String Lecture1 = test.select(" tr:nth-child(n) > td:nth-child(5) > table > tbody > tr > td:nth-child(4)").first().text();
            System.out.println("Lecture 1: " + Lecture1);
            String Lecturer2 ;
            //check if the index value exist
            if (!test.select(" tr:nth-child(n) > td:nth-child(5) > table > tbody > tr:nth-child(2) > td:nth-child(4)").isEmpty()) {
                //check if its not empty
                if (!(test.select("tr:nth-child(n) > td:nth-child(5) > table > tbody > tr > td:nth-child(4)").get(1) == null)) {
                    Lecturer2 = test.select("tr:nth-child(n) > td:nth-child(5) > table > tbody > tr > td:nth-child(4)").get(1).text();
                    System.out.println("Lecturer 2: " + Lecturer2);
                }
            } else {
                continue;
            }
            String CreditHour;
            //SubjectBinder puts the data into a data structure.
//                SubjectBinder subjectBinder = new SubjectBinder(subjectCode, Section, subjectTitle);
        }
    }


}
