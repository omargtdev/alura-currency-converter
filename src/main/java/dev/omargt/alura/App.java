package dev.omargt.alura;

import dev.omargt.alura.converter.CurrencyServiceException;
import dev.omargt.alura.converter.ExchangerCurrency;

import javax.swing.*;
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

            // Creating a frame
            JFrame frame = new JFrame("My first GUI in IntellijIDEA");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(300, 300);
            JButton myButton = new JButton("Press me");
            myButton.addActionListener(e -> {
                JOptionPane.showMessageDialog(frame, e.getActionCommand());
            });
            frame.add(myButton);
            frame.setVisible(true);

            // Wait complete all
            //CompletableFuture.allOf(response1, response2).join();
        } catch (CurrencyServiceException e) {
            e.printStackTrace();
        }
    }

}
