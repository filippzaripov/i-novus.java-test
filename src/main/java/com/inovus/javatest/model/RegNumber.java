package com.inovus.javatest.model;

import javax.persistence.*;

@Entity
public class RegNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String regNumberString;

    @Column
    private String letterPart;

    @Column
    private String numberPart;

    public RegNumber() {}

    public RegNumber(String numberPart, String letterPart){
        setRegNumberString(letterPart.substring(0,1) + numberPart + letterPart.substring(1));
        this.numberPart = numberPart;
        this.letterPart = letterPart;
    }

    public RegNumber(String regNumberString) {
        this.regNumberString = regNumberString;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegNumberString() {
        return regNumberString;
    }

    public void setRegNumberString(String regNumberString) {
        this.regNumberString = regNumberString;
    }

    public String getLetterPart() {
        return letterPart;
    }

    public void setLetterPart(String letterPart) {
        this.letterPart = letterPart;
    }

    public String getNumberPart() {
        return numberPart;
    }

    public void setNumberPart(String numberPart) {
        this.numberPart = numberPart;
    }

    @Override
    public String toString() {
        return "ID : " + this.id +
                "Reg Number : " + this.regNumberString;
    }
}