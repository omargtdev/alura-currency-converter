package dev.omargt.alura.converter.unit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LengthTest {

    @Test
    void convertTo() {
        double valueReceived = 20; // Length provided by user
        Length centimeter = Length.CENTIMETER; // Selected 1
        Length meter = Length.METER; // Selected 2

        assertEquals(2000, meter.convertTo(centimeter, valueReceived));
        assertEquals(0.2, centimeter.convertTo(meter, valueReceived));
    }

}