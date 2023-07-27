package dev.omargt.alura.converter;

import java.time.LocalDate;

public class ExchangeResult {

    private double unitRateTo;
    private double result;
    private LocalDate date;

    public ExchangeResult(
            double unitRateTo,
            double result, LocalDate date) {
        this.unitRateTo = unitRateTo;
        this.result = result;
        this.date = date;
    }

    public double getToUnitAmount() {
        return unitRateTo;
    }

    public double getResult() {
        return result;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "ExchangeResult{" +
                "unitRateTo=" + unitRateTo +
                ", result=" + result +
                ", date=" + date +
                '}';
    }

}