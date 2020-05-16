package com.company;

public class SubjectBinder {

    private String Code,Sect,Title,Chr,Day,Time,Venue,Lecturer1,Lecturer2;

    public SubjectBinder(String one,String two,String three,String four,String five,String six,String seven,String eight,String nine){
        Code = one;
        Sect = two;
        Title = three;
        Chr = four;
        Time = five;
        Venue = six;
        Day= seven;
        Lecturer1 = eight;
        Lecturer2 = nine;

    }

    public SubjectBinder(String code, String sect, String title) {
        Code = code;
        Sect = sect;
        Title = title;
        System.out.println(Code+ " " + Sect + " " + Title + " ");
    }
}
