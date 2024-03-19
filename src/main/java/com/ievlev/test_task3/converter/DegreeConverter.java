package com.ievlev.test_task3.converter;

import com.ievlev.test_task3.exceptions.DegreeNotFoundException;
import com.ievlev.test_task3.model.Degree;

import javax.persistence.AttributeConverter;

public class DegreeConverter implements AttributeConverter<Degree, String> {

    @Override
    public String convertToDatabaseColumn(Degree attribute) {
        return attribute.getName();
    }

    @Override
    public Degree convertToEntityAttribute(String dbData) {
        return Degree.fromName(dbData).orElseThrow(() ->
                new DegreeNotFoundException("degree :" + dbData + "not found in java enum Degree")
        );
    }
}
