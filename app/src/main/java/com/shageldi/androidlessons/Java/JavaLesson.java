package com.shageldi.androidlessons.Java;

import com.shageldi.androidlessons.Model.Person;

import java.util.ArrayList;

public class JavaLesson {
//    public static void main(String[] args) {
//        int sm=300;
//
//        int m=sm/100;
//
//        float hakyky_sanlar=0.2F;
//        Float hs2=0.2F;
//        double uly_hakyky_sanlar=0.45;
//        long has_uly_sanlar=100_000_000L;
//        String tekst="Salam";
//        boolean isLate=false;
//        char startLetter='S';
//
//        String[] names={"Surajaddin","Shahnur","Shageldi","1","2"};
//
//        String[] numbers=new String[100];
//
//        int[] n2={1,2,3,4,56,7876};
//
//        ArrayList<String> list=new ArrayList<>();
//
//
//
//        list.add("Shageldi");  //0
//        list.add("Surajaddin");  //1
//        list.add("Shahnur");  //2
//
//        System.out.println("Last element: "+list.get(0));
//
//        list.set(1,"Shahruh");
//
//        System.out.println("After edit: "+list);
//
//        list.clear();
//
//        System.out.println("Cleared: "+list);
//    }
    public static void main(String[] args) {
        ArrayList<Person> persons=new ArrayList<>();
        persons.add(new Person("Shageldi","Alyyew","2000-11-18","+99361234567"));//0
        persons.add(new Person("Surajaddin","Familya","2001-11-18","+99361234567"));//1
        persons.add(new Person("Shahnur","Familya","2003-11-18","+99361234567"));//2


        int i=0;

        boolean isRunning=true;

        while (isRunning){
            System.out.println(i);
            i++;

            if(i==500) isRunning=false;
        }


    }
}
