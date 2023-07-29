package dev.omargt.alura.converter.unit;

public enum TemperatureScale implements UnitConvertible<TemperatureScale> {
    CELSIUS("Celsius", "°C", 'C'),
    FAHRENHEIT("Fahrenheit", "°F", 'F'),
    KELVIN("Kelvin", "K", 'K'),
    RANKINE("Rankine", "°R", 'R');

    private final char identifier;
    private final String name;
    private final String symbol;

    TemperatureScale(String name, String symbol, char identifier) {
        this.name = name;
        this.symbol = symbol;
        this.identifier = identifier;
    }

    /**
     * Adds the ability to convert the current unit to another
     * with a provided value.
     *
     * @param unitToConvert The destination of the temperature scale to convert.
     * @param value         The value in units for the conversion.
     * @return The value of the destination already converted.
     */
    @Override
    public double convertTo(TemperatureScale unitToConvert, double value) {
        // CC -> From Celsius to Celsius
        String identifierFromTo = String.valueOf(this.identifier) +
                unitToConvert.identifier;

        switch (identifierFromTo) {
            case "CC":
            case "FF":
            case "KK":
            case "RR":
                return value;

            // Celsius to
            case "CF":
                return value * 9 / 5 + 32;
            case "CK":
                return value + 273.15;
            case "CR":
                return value * 9 / 5 + 491.67;

            // Fahrenheit to
            case "FC":
                return (value - 32) * 5 / 9;
            case "FK":
                return (value + 459.67) * 5 / 9;
            case "FR":
                return value + 459.67;

            // Kelvin to
            case "KC":
                return value - 273.15;
            case "KF":
                return value * 9 / 5 - 459.67;
            case "KR":
                return value * 1.8;

            // Rankine to
            case "RC":
                return (value - 491.67) * 5 / 9;
            case "RF":
                return value - 459.67;
            case "RK":
                return value * 5 / 9;

            default:
                return 0;
        }
    }

    @Override
    public String toString() {
        return name + " (" + symbol + ")";
    }
}
