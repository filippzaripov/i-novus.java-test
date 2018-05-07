package com.inovus.javatest.service;

import com.inovus.javatest.model.RegNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
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
        letters.add("У");
        letters.add("К");
        letters.add("Х");
        letters.add("С");
        letters.add("В");
        letters.add("М");
        letters.sort(new LetterComparator());
    }

    private final String region = "116 RUS";

    /**
     * Generates random RegNumber if possible.
     *
     * @param attempts
     * @return new RegNumber, if all numbers are taken return null
     */
    public RegNumber generateRandomRegNumber(int attempts) {

        String letterPart = "";
        String numberPart = "";

        // choosing letters
        for (int i = 0; i < 3; i++) {
            int a = 0;
            int b = this.letters.size();
            int random_number = a + (int) (Math.random() * b);
            letterPart += letters.get(random_number);
            random_number = a + (int) (Math.random() * 10);
            numberPart += random_number;
        }
        RegNumber regNumber = new RegNumber(numberPart, letterPart);
        RegNumber findedRegNumber = null;
        try {
            findedRegNumber = this.regNumberService.findRegNumberByRegNumberString(regNumber.getRegNumberString());
        } catch (NullPointerException e) {
            System.out.println("There is no such record in DB");
        }
        if (findedRegNumber == null) {
            return this.regNumberService.addRegNumber(regNumber);
        } else if (attempts < 1728000) {
            generateRandomRegNumber(attempts++);
            System.out.println(regNumber.getRegNumberString() + " is busy. Trying to find another random Reg Number.");
        } else {
            System.out.println("All numbers are busy.");
            return null;
        }
        return null;
    }

    /**
     * Generates next number regarding the last added element.
     *
     * @return increased RegNumber. If it's impossible returns null
     */
    //TODO: increasing letters doesn't work

    public RegNumber generateNextRegNumber() {
        RegNumber lastRegNumber = null;
        RegNumber newRegNumber = null;
        try {
            lastRegNumber = regNumberService.getLastRegNumber();
        } catch (NullPointerException e) {
            System.out.println("This is not any record in DB");
        }

        if (lastRegNumber == null) {
            return this.regNumberService.addRegNumber(new RegNumber("000", "ААА"));
        } else {
            String numberPart = lastRegNumber.getNumberPart();
            String letterPart = lastRegNumber.getLetterPart();

            if (numberPart.equals("999") && !letterPart.equals("ХХХ")) {
               newRegNumber = new RegNumber("000", increaseLetters(letterPart));
               if (isRegNumberReserved(newRegNumber.getRegNumberString())){
                   System.out.println(newRegNumber.getRegNumberString() + " is already reserved. Next number is not available. Please choose random");
                   return null;
               }else{
                   newRegNumber = this.regNumberService.addRegNumber(newRegNumber);
                   return newRegNumber;
               }
            } else if (!numberPart.equals("999")) {
                newRegNumber = new RegNumber(increaseNumber(numberPart), letterPart);
                if (isRegNumberReserved(newRegNumber.getRegNumberString())){
                    System.out.println(newRegNumber.getRegNumberString() + " is already reserved. Next number is not available. Please choose random");
                    return null;
                }else{
                    newRegNumber = this.regNumberService.addRegNumber(new RegNumber(increaseNumber(numberPart), letterPart));
                    return newRegNumber;
                }
            } else {
                System.out.println("Next number is not available. Please choose random");
                return null;
            }

        }
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
                    return letters.substring(0, 1) + mid + this.letters.get(0);
                }
            }
        } else {
            String first = letters.substring(0, 1);
            for (int i = 0; i < this.letters.size(); i++) {
                if (this.letters.get(i).equals(first)) {
                    first = this.letters.get(i++);
                    return first + this.letters.get(0) + this.letters.get(0);
                }
            }
        }
        return null;
    }


    private boolean isRegNumberReserved(String regNumber) {
        if (this.regNumberService.findRegNumberByRegNumberString(regNumber) != null) {
            return true;
        } else {
            return false;
        }
    }
}
