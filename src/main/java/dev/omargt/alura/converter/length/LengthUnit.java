package dev.omargt.alura.converter.length;

public enum LengthUnit {
    METER("Meter", "m", 1),
    KILOMETER("Kilometer", "km", 1000),
    CENTIMETER("Meter", "cm", 0.01),
    MILLIMETER("Millimeter", "mm", 0.001),
    INCH("Inch", "in", 0.0254),
    FOOT("Foot", "ft", 0.3048),
    ;

    private final String name;
    private final String symbol;
    private final double conversionFactor;

    LengthUnit(String name, String symbol, double conversionFactor) {
        this.name = name;
        this.symbol = symbol;
        this.conversionFactor = conversionFactor;
    }

    public double convertTo(LengthUnit lengthUnitToConvert, double length){
        double toMetersOfCurrent = length * conversionFactor;
        double fromMetersOfDestiny = toMetersOfCurrent / lengthUnitToConvert.conversionFactor;
        return fromMetersOfDestiny;
    }

}
