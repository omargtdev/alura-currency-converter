package dev.omargt.alura.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpTimeoutException;
import java.nio.charset.Charset;
import java.time.Duration;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.CompletableFuture;

public abstract class ExchangerCurrency {

    private static final String API_ROUTE_BASE = "https://api.exchangerate.host";
    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();

    public static CompletableFuture<ExchangeResult> exchange(
            Currency from,
            Currency to,
            double amount)
            throws CurrencyServiceException {
        String exchangeUrl = String.format("%s/convert?from=%s&to=%s&amount=%f",
                API_ROUTE_BASE, from.getCurrencyCode(), to.getCurrencyCode(), amount);

        try {
            var exchangeResponse = sendAsyncGetRequest(exchangeUrl)
                    .thenApply(response -> {
                        try {
                            JsonNode rootNode = JSON_MAPPER.readTree(response.body());
                            boolean success = rootNode.get("success").asBoolean();

                            if (!success)
                                return null;

                            double unitRateTo = rootNode.get("info").get("rate").asDouble();
                            double amountResult = rootNode.get("result").asDouble();
                            LocalDate exchangeDate = LocalDate.parse(rootNode.get("date").asText());

                            return new ExchangeResult(unitRateTo, amountResult, exchangeDate);
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                            return null;
                        }
                    });

            return exchangeResponse;
        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw new CurrencyServiceException(
                    "Something was wrong with service." +
                            "Try it later please.");
        } catch (HttpTimeoutException e) {
            e.printStackTrace();
            throw new CurrencyServiceException(
                    "The service is taking time to respond." +
                            "Try it later please.");
        }

    }

    public static CompletableFuture<List<Currency>> getCurrencies()
            throws CurrencyServiceException {
        String currenciesUrl = API_ROUTE_BASE + "/symbols";
        try {
            var currenciesRequest = sendAsyncGetRequest(currenciesUrl)
                    .thenApply(response -> {
                        try {
                            JsonNode rootNode = JSON_MAPPER.readTree(response.body());
                            boolean success = rootNode.get("success").asBoolean();

                            if (!success)
                                return Collections.<Currency>emptyList();

                            List<Currency> currencies = new ArrayList<>();

                            Iterator<Map.Entry<String, JsonNode>> symbols = rootNode.get("symbols").fields();
                            while (symbols.hasNext()) {
                                var symbol = symbols.next();
                                try {
                                    Currency currency = Currency.getInstance(symbol.getKey());
                                    currencies.add(currency);
                                } catch (IllegalArgumentException e) {
                                    // When the currency provided is not supported by Java Currency
                                    System.out.println("Currency not supported: " + symbol.getKey());
                                }
                            }

                            // The service provides the currencies already ordered
                            //Comparator comparePerCurrencyCode = Comparator.comparing(Currency::getCurrencyCode);
                            //currencies.sort(comparePerCurrencyCode);
                            return currencies;
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                            return Collections.<Currency>emptyList();
                        }
                    });

            return currenciesRequest;
        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw new CurrencyServiceException(
                    "Something was wrong with service." +
                            "Try it later please.");
        } catch (HttpTimeoutException e) {
            e.printStackTrace();
            throw new CurrencyServiceException(
                    "The service is taking time to respond." +
                            "Try it later please.");
        }
    }

    private static CompletableFuture<HttpResponse<String>> sendAsyncGetRequest(String url)
            throws URISyntaxException, HttpTimeoutException {
        URI urlToMakeRequest = new URI(url);
        Duration maxDurationOfRequest = Duration.ofSeconds(30);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(urlToMakeRequest)
                .timeout(maxDurationOfRequest)
                .GET()
                .build();

        Charset responseCharset = Charset.forName("UTF-8");
        return HttpClient.newHttpClient()
                .sendAsync(request, HttpResponse.BodyHandlers.ofString(responseCharset));
    }

}
