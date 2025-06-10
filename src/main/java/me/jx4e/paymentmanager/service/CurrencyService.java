package me.jx4e.paymentmanager.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Service
public class CurrencyService {
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${currency.api.url}")
    private final String apiUrl = "https://api.exchangerate-api.com/v4/latest/";

    @Value("${currency.api.delay}")
    private int delay;

    private Map<String, Double> cachedRates = new HashMap<>();
    private Instant lastFetched = Instant.MIN;

    private String getExchangeRateUrl(String currency) {
        return apiUrl + currency;
    }

    public Map<String, Double> getExchangeRates() {
        // Only fetch if older than 1 hour
        if (Duration.between(lastFetched, Instant.now()).toMinutes() > delay || cachedRates.isEmpty()) {
            ResponseEntity<Map> response = restTemplate.getForEntity(getExchangeRateUrl("USD"), Map.class);
            Map body = response.getBody();
            if (body != null && body.containsKey("rates")) {
                Map<String, Double> rates = (Map<String, Double>) body.get("rates");
                cachedRates = Map.of(
                        "USD", 1.0,
                        "CAD", rates.get("CAD"),
                        "EUR", rates.get("EUR"),
                        "GBP", rates.get("GBP")
                );
                lastFetched = Instant.now();
            }
        }
        return cachedRates;
    }
}
