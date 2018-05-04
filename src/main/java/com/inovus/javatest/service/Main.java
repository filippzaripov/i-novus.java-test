package com.inovus.javatest.service;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        RegNumberGenerator gn = new RegNumberGenerator();
        gn.generateRandomRegNumber();

        ArrayList<String> list = new ArrayList();
        list.add("А");
        list.add("Е");
        list.add("Т");
        list.add("О");
        list.add("Р");
        list.add("Н");
        list.add("у");
        list.add("К");
        list.add("Х");
        list.add("С");
        list.add("В");
        list.add("М");

        list.sort(new LetterComparator());

        System.out.println(list.size());
    }
}
