package com.ievlev.test_task3.converter;

import com.ievlev.test_task3.exceptions.DegreeNotFoundException;
import com.ievlev.test_task3.model.Degree;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DegreeConverterTest {

    @Test
    void testConvertToDatabaseColumn() {
        DegreeConverter converter = new DegreeConverter();
        Degree degree = Degree.ASSISTANT;
        String result = converter.convertToDatabaseColumn(degree);
        assertEquals("assistant", result);
    }

    @Test
    void testConvertToEntityAttribute() {
        DegreeConverter converter = new DegreeConverter();
        String dbData = "associate professor";
        Degree result = converter.convertToEntityAttribute(dbData);
        assertEquals(Degree.ASSOCIATE_PROFESSOR, result);
    }

    @Test
    void testConvertToEntityAttribute_InvalidDegree() {
        DegreeConverter converter = new DegreeConverter();
        String dbData = "invalid";
        assertThrows(DegreeNotFoundException.class, () -> converter.convertToEntityAttribute(dbData));
    }
}
