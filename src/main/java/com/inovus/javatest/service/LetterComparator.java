package com.inovus.javatest.service;

import java.util.Comparator;

public class LetterComparator implements Comparator<String> {

    public int compare(String a, String b){

        return a.compareToIgnoreCase(b);
    }
}
