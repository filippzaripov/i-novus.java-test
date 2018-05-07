package com.inovus.javatest.controller;

import com.inovus.javatest.model.RegNumber;
import com.inovus.javatest.service.RegNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegNumberController {

    @Autowired
    private RegNumberService regNumberService;

    @RequestMapping(value = "/random", method = RequestMethod.GET)
    public String getRandomRegNumber() {
        return this.regNumberService.getRandomRegNumber().getRegNumberString();
    }

    @RequestMapping(value = "/next", method = RequestMethod.GET)
    public String getNextRegNumber() {
        String regNumber = "null. Please try to call /random";
        try {
            regNumber = this.regNumberService.getNextRegNumber().getRegNumberString();
        } catch (NullPointerException e) {
            System.err.println("RegNumberService returns null");
        }
        return regNumber;
    }
};
