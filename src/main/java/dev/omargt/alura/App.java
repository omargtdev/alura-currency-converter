package dev.omargt.alura;

import dev.omargt.alura.converter.CurrencyServiceException;
import dev.omargt.alura.converter.ExchangerCurrency;

import java.util.Currency;
import java.util.List;

public class App {

    public static void main(String[] args) {
        // Making something incredible...
        try {
            List<Currency> currencies =
                    ExchangerCurrency.getCurrencies().join();

            for (Currency currency : currencies) {
                System.out.println(currency.getCurrencyCode());
            }

            ExchangerCurrency.exchange(
                    currencies.get(0),
                    currencies.get(17),
                    1200
            ).thenAccept(System.out::println).join();

            // Wait complete all
            //CompletableFuture.allOf(response1, response2).join();
        } catch (CurrencyServiceException e) {
            e.printStackTrace();
        }
    }

}
