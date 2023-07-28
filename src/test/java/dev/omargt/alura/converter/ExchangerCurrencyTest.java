package dev.omargt.alura.converter;

import org.junit.jupiter.api.Test;

import java.util.Currency;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExchangerCurrencyTest {

    /**
     * Test the number of currencies/symbols from the <a href="https://api.exchangerate.host/symbols">service</a>.
     * Also, The currencies of the service do not all match Java Currency type provided by the
     * <code>getCurrencies</code> method.
     * Last count date of currencies from the service: 27/07/2023
     *
     * @see ExchangerCurrency
     * @see Currency
     */
    @Test
    void getCurrencies() {
        try {
            List<CurrencyWrapper> currencies = ExchangerCurrency.getCurrencies().join();
            int lastCount = 166;
            assertEquals(lastCount, currencies.size());
        } catch (CurrencyServiceException e) {
            e.printStackTrace();
        }
    }

}