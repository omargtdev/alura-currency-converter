package dev.omargt.alura.converter;

import java.util.Currency;

/**
 * Wrapper to Currency Java, for replace toString operation
 *
 * @see java.util.Currency
 */
public class CurrencyWrapper {

    private final Currency currency;

    public CurrencyWrapper(Currency currency) {
        this.currency = currency;
    }

    public Currency getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return this.currency.getCurrencyCode() + " ("
                + this.currency.getSymbol() + " "
                + this.currency.getDisplayName() + ")";
    }
}