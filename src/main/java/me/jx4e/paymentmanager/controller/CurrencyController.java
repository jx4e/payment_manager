package me.jx4e.paymentmanager.controller;

import me.jx4e.paymentmanager.service.CurrencyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class CurrencyController {
    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/settings")
    public String showSettingsPage(Model model) {
        Map<String, Double> exchangeRate = currencyService.getExchangeRates();
        model.addAttribute("exchangeRate", exchangeRate);
        return "settings"; // corresponds to settings.html
    }
}
