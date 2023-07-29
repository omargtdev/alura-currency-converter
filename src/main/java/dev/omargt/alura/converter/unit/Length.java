package dev.omargt.alura.converter.unit;

public enum Length implements UnitConvertible<Length> {
    METER("Meter", "m", 1),
    KILOMETER("Kilometer", "km", 1000),
    CENTIMETER("Centimeter", "cm", 0.01),
    MILLIMETER("Millimeter", "mm", 0.001),
    INCH("Inch", "in", 0.0254),
    FOOT("Foot", "ft", 0.3048),
    ;

    private final String name;
    private final String symbol;
    private final double conversionFactor;

    Length(String name, String symbol, double conversionFactor) {
        this.name = name;
        this.symbol = symbol;
        this.conversionFactor = conversionFactor;
    }

    /**
     * Adds the ability to convert the current unit to another
     * with a provided value.
     *
     * @param unitToConvert The destination of the length to convert.
     * @param value         The value in units for the conversion.
     * @return The value of the destination already converted.
     */
    @Override
    public double convertTo(Length unitToConvert, double value) {
        double toMetersOfCurrent = value * conversionFactor;
        double fromMetersOfDestiny = toMetersOfCurrent / unitToConvert.conversionFactor;
        return fromMetersOfDestiny;
    }

    @Override
    public String toString() {
        return name + " (" + symbol + ")";
    }

}
