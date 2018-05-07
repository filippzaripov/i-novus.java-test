package com.inovus.javatest.service;

import com.inovus.javatest.model.RegNumber;
import com.inovus.javatest.repositories.RegNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegNumberService {

    @Autowired
    private RegNumberRepository regNumberRepository;

    @Autowired
    private RegNumberGenerator regNumberGenerator;

    public RegNumber getRandomRegNumber() {
        return this.regNumberGenerator.generateRandomRegNumber(1);
    }

    public RegNumber getNextRegNumber() {
        RegNumber regNumber= null;
        try{
            regNumber = regNumberGenerator.generateNextRegNumber();
        }catch (NullPointerException e){
            System.err.println("Next number couldn't be generated");
        }
        return regNumber;
    }

    public RegNumber getLastRegNumber(){
        return this.regNumberRepository.findFirst1ByOrderByIdDesc();
    }

    public RegNumber addRegNumber(RegNumber regNumber){
        return this.regNumberRepository.save(regNumber);
    }
    /**
     * @param regNumber
     * @return regNumber object if exists. Else - null
     */
    public RegNumber findRegNumberByRegNumberString(String regNumber) {
        return regNumberRepository.findRegNumberByRegNumberString(regNumber);
    }
}
