package com.inovus.javatest.service;

import com.inovus.javatest.model.RegNumber;
import com.inovus.javatest.repositories.RegNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegNumberService {

    @Autowired
    private RegNumberRepository regNumberRepository;

    public RegNumber getRandomNumber(){
        return null;
    }

    public RegNumber getLastRegNumber(){
        return null;
    }
}
