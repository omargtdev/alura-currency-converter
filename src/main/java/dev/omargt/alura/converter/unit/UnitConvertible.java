package dev.omargt.alura.converter.unit;

public interface UnitConvertible<T> {

    /**
     * Adds the ability to convert the current unit to another
     * with a provided value.
     *
     * @param unitToConvert The destination of the unit to convert.
     * @param value         The value in units for the conversion.
     * @return The value of the destination already converted.
     */
    double convertTo(T unitToConvert, double value);

}
