package com.company;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.*;

public class Main {

    final static List<List<String>> GroupBind = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        String url = "http://albiruni.iium.edu.my/myapps/StudentOnline/schedule1.php";
        int page_view = 50;
        String[] KulliyyahListVal = {
                "KAHS",//0
                "AED",//1
                "BRIDG",//2
                "CFL",//3
                "CCAC",//4
                "DENT",//5
                "EDUC",//6
                "ENGIN",//7
                "ECONS",//8
                "KICT",//9
                "IHART",//10
                "IRKHS",//11
                "IIBF",//12
                "ISTAC",//13
                "KLM",//14
                "LAWS",//15
                "MEDIC",//16
                "NURS",//17
                "PHARM",//18
                "KOS"};//19
        int semesterVal = 2;

        String sessionVal = "2019/2020";

        String url2 = "?action=view&view=".concat(String.valueOf(page_view)) + "&kuly=".concat(KulliyyahListVal[7]) + "&ctype=%3C&course=&sem=".concat(String.valueOf(semesterVal)) + "&ses=".concat(sessionVal);

        String url3 = url + url2;
//
//        GetKulyList(url);

        WebScrapper(url3);

    }

    public static void GetKulyList(String url) throws IOException {
        Document doc = Jsoup
                .connect(url)
                .userAgent("Google Chrome")
                .timeout(6000).get();

        Elements elements = doc
                .select("body > form > table > tbody > tr:nth-child(2) > td:nth-child(2) > select > option:nth-child(n)");

        for (Element element : elements) {
            char[] ListKul;
            ListKul = element
                    .select("option:nth-child(n)")
                    .val()
                    .toCharArray();
//            System.out.println(ListKul);
        }
    }

    public static void WebScrapper(String url) throws IOException {
        String subjectTitle;
        String section;
        String subjectCode;
        String Lecture1;
        String Lecturer2 = null;
        String Lecturer3 = null;

//        ArrayList<String> DataBind = new ArrayList<String>();
//        ArrayList<ArrayList<String>> GroupBind = new ArrayList<ArrayList<String>>();
        //Tries to connect. Haven't implement the "if it fails" option

        Document document = Jsoup.connect(url).userAgent("Google Chrome").timeout(6000).get();

        Elements subjectCodeRowRough = document.select("body > table:nth-child(4) > tbody > tr:nth-child(n)");//Select the table that contains the Data List
//        System.out.println(subjectCodeRowRough.size());
        //For loop eliminates the parts that don't contain the data we need
        for (int i = 3; i < subjectCodeRowRough.size() - 2; i++) {
            //I put here so that it resets the ArrayList for every iteration
            List<String> DataBind = new ArrayList<>();
            Element test = subjectCodeRowRough
                    .get(i);

            subjectCode = test
                    .select("tr:nth-of-type(n) > td:nth-of-type(1)")
                    .first()
                    .text();

//            System.out.println("Code: " + subjectCode);

            subjectTitle = test
                    .select("tr:nth-of-type(n) >td:nth-of-type(3)")
                    .first()
                    .text();
//            System.out.println("Title: " + subjectTitle);

            section = test
                    .select("tr:nth-of-type(n) >td:nth-of-type(2)")
                    .first()
                    .text();

//            System.out.println("Section:" + section);

            Lecture1 = test
                    .select(" tr:nth-child(n) > td:nth-child(5) > table > tbody > tr > td:nth-child(4)")
                    .first()
                    .text();
//            System.out.println("Lecture 1: " + Lecture1);

            //check if the index value exist

            if (!test.select(" tr:nth-child(n) > td:nth-child(5) > table > tbody > tr:nth-child(2) > td:nth-child(4)").isEmpty()) {
                //check if its not empty
                if (!(test.select("tr:nth-child(n) > td:nth-child(5) > table > tbody > tr > td:nth-child(4)").get(1).text() == null)) {
                    Lecturer2 = test.select("tr:nth-child(n) > td:nth-child(5) > table > tbody > tr > td:nth-child(4)").get(1).text();
//                    System.out.println("Lecturer 2: " + Lecturer2);
                }
            } else if (!test.select("tr:nth-child(n) > td:nth-child(5) > table > tbody > tr:nth-child(3) > td:nth-child(4)").isEmpty()) {
                Lecturer3 = test.select("tr:nth-child(n) > td:nth-child(5) > table > tbody > tr > td:nth-child(4)").get(2).text();
//                    System.out.println("Lecturer 3: "+ Lecturer3);
            }

            String CreditHour = test
                    .select("tr:nth-of-type(n) >td:nth-of-type(4)")
                    .first()
                    .text();

            System.out.println("Code: "+ subjectCode+"\n" +"Title: " +subjectTitle +"\n" + "Section: "+section);
            //Unnecessary but it makes life easier
            String[] BundleList = {subjectTitle, section, subjectCode, Lecture1, Lecturer2, Lecturer3};
            System.out.println("Chr: " + CreditHour);
            for (String element : BundleList)
                DataBind.addAll(Collections.singleton(element));
            GroupBind.add(DataBind);
//            System.out.println(GroupBind);

//            DataBind.clear();


//            System.out.println(GroupBind);


//              Binder(subjectTitle, section, subjectCode, Lecture1, Lecturer2,  Lecturer3);

        }
    }

    public static void Binder(String subjectTitle, String section, String subjectCode, String Lecture1, String Lecturer2, String Lecturer3) {
        final SubjectBinder subjectBinder = new SubjectBinder();
        subjectBinder.DataBinder(subjectTitle, section, subjectCode, Lecture1, Lecturer2, Lecturer3);
    }


}
