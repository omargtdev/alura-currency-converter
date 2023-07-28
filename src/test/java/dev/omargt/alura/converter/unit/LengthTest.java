package dev.omargt.alura.converter.unit;

import dev.omargt.alura.converter.unit.Length;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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