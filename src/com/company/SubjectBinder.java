package com.company;

import java.util.ArrayList;


public class SubjectBinder {

    ArrayList<String> DataBind = new ArrayList<>();
    ArrayList<ArrayList<String>> GroupBind = new ArrayList<>();

    public SubjectBinder() {
    }

    public void DataBinder(String subjectTitle, String section, String subjectCode, String Lecture1, String Lecturer2, String Lecturer3) {
        DataBind.add(subjectCode);
        DataBind.add(subjectTitle);
        DataBind.add(section);
        DataBind.add(Lecture1);
        DataBind.add(Lecturer2);
        DataBind.add(Lecturer3);
        GroupBind.add(DataBind);
        System.out.println(DataBind);




    }
}