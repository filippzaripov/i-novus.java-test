package com.inovus.javatest.service;

import com.inovus.javatest.model.RegNumber;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class RegNumberGenerator {

    @Autowired
    private RegNumberService regNumberService;

    private ArrayList<String> letters = new ArrayList();

    public RegNumberGenerator() {
        init();
    }

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


        //TODO: make check with previous numbers

        RegNumber regNumber = regNumberService.getLastRegNumber();

        String numberPart = regNumber.getNumberPart();
        String letterPart = regNumber.getLetterPart();

        if (numberPart.equals("999") && !letterPart.equals("ХХХ")) {
            regNumber.setLetterPart(increaseLetters(letterPart));
            regNumber.setNumberPart("000");
        } else if (!numberPart.equals("999")) {
            regNumber.setNumberPart(increaseNumber(numberPart));
        } else {
            System.out.println("Next number is not available. Please choose random");
            return null;
        }

        return regNumber;
    }

    private String increaseNumber(String number) {

        if (!number.substring(2).equals("9")) {
            int last = Integer.parseInt(number.substring(2));
            last++;
            return number.substring(0, 2) + last;
        } else if (!number.substring(1, 2).equals("9")) {
            int mid = Integer.parseInt(number.substring(1, 2));
            mid++;
            return number.substring(0, 1) + mid + "0";
        } else {
            int first = Integer.parseInt(number.substring(0, 1));
            first++;
            return first + "00";
        }
    }

    private String increaseLetters(String letters) {

        if (!letters.substring(2).equals(this.letters.get(this.letters.size() - 1))) {
            String last = letters.substring(2);
            for (int i = 0; i < this.letters.size(); i++) {
                if (this.letters.get(i).equals(last)) {
                    last = this.letters.get(i++);
                    return letters.substring(0, 2) + last;
                }
            }

        } else if (!letters.substring(1, 2).equals(this.letters.get(this.letters.size() - 1))) {
            String mid = letters.substring(1, 2);
            for (int i = 0; i < this.letters.size(); i++) {
                if (this.letters.get(i).equals(mid)) {
                    mid = this.letters.get(i++);
                    return letters.substring(0,1) + mid + this.letters.get(0);
                }
            }
        } else{
            String first = letters.substring(0,1);
            for (int i = 0; i < this.letters.size(); i++) {
                if (this.letters.get(i).equals(first)){
                    first = this.letters.get(i++);
                    return first + this.letters.get(0) + this.letters.get(0);
                }
            }
        }
        return null;
    }



}
