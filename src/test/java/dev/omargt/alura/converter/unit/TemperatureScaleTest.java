package dev.omargt.alura.converter.unit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TemperatureScaleTest {

    @Test
    void convertTo() {
        double userValue = 150;
        TemperatureScale temperatureSelected = TemperatureScale.KELVIN;
        TemperatureScale temperatureToConvert = TemperatureScale.RANKINE;

        double result = temperatureSelected.convertTo(temperatureToConvert, userValue);
        assertEquals(270, result);
    }

}