package com.inovus.javatest.service;

import com.inovus.javatest.model.RegNumber;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class RegNumberGenerator {

    @Autowired
    private RegNumberService regNumberService;

    private ArrayList<String> letters = new ArrayList();

    private void init() {
        letters.add("А");
        letters.add("Е");
        letters.add("Т");
        letters.add("О");
        letters.add("Р");
        letters.add("Н");
        letters.add("у");
        letters.add("К");
        letters.add("Х");
        letters.add("С");
        letters.add("В");
        letters.add("М");
        letters.sort(new LetterComparator());
    }

    private final String region = "116 RUS";

    public RegNumber generateRandomRegNumber() {
        init();
        RegNumber regNumber = new RegNumber();
        String lettersRegNumber = "";
        String numbers = "";

        // choosing letters
        for (int i = 0; i < 3; i++) {
            int a = 0;
            int b = this.letters.size();
            int random_number = a + (int) (Math.random() * b);
            lettersRegNumber += letters.get(random_number);
            random_number = a + (int) (Math.random() * 10);
            numbers += random_number;
        }

        regNumber.setRegNumber(lettersRegNumber.substring(0, 1) + numbers + lettersRegNumber.substring(1) + region);
        System.out.println();

        //TODO: make check with previous numbers

        return regNumber;
    }

    public RegNumber generateNextRegNumber() {

        //TODO: finish with this method

        init();
        RegNumber regNumber = regNumberService.getLastRegNumber();
        String regNumberString = regNumber.getRegNumber();

        String numberPart = regNumberString.substring(1, 4);
        String letterPart = regNumberString.substring(0, 1) + regNumberString.substring(4, 6);


        return null;
    }


}
