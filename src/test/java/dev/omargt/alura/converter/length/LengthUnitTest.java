package dev.omargt.alura.converter.length;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LengthUnitTest {

    @Test
    void convertTo() {
        double valueReceived = 20; // Length provided by user
        LengthUnit centimeter = LengthUnit.CENTIMETER; // Selected 1
        LengthUnit meter = LengthUnit.METER; // Selected 2

        assertEquals(2000, meter.convertTo(centimeter, valueReceived));
        assertEquals(0.2, centimeter.convertTo(meter, valueReceived));
    }

}