package com.hexaware.service;

import com.hexaware.exception.InvalidMarksException;

import java.util.List;
import java.util.stream.Collectors;

public class MyService {

    public double computePercent(List<Double> listMarks,
                                 double totalMarks) throws InvalidMarksException {

        for(double m : listMarks) {
            if(m<0)
                throw new InvalidMarksException("Marks cannot be negative");
        }

        double totalMarksScored = listMarks.stream()
                .collect(Collectors.summingDouble(e->e));

        double percent = (totalMarksScored / totalMarks) * 100;

        if(percent>100)
            throw new InvalidMarksException("Percent cannot be > 100");

        return percent;
    }
}