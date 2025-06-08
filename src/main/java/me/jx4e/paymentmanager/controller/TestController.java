package me.jx4e.paymentmanager.controller;

import me.jx4e.paymentmanager.model.Invoice;
import me.jx4e.paymentmanager.model.Statement;
import me.jx4e.paymentmanager.model.expense.TotalExpense;
import me.jx4e.paymentmanager.model.party.Issuer;
import me.jx4e.paymentmanager.model.party.Recipient;
import me.jx4e.paymentmanager.model.party.TransactionParty;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Controller
public class TestController {
    @GetMapping("/api/test")
    private String test() {
        return "index";
    }
}
