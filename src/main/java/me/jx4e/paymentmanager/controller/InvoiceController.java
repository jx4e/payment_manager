package me.jx4e.paymentmanager.controller;

import me.jx4e.paymentmanager.Main;
import me.jx4e.paymentmanager.model.Invoice;
import me.jx4e.paymentmanager.model.expense.Expense;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InvoiceController {

    @GetMapping("/api/invoice")
    public String getInvoice(Model model) {
        Invoice invoice = Main.getTemplate();

        model.addAttribute("invoiceTitle", "Invoice");
        model.addAttribute("issuedTo", invoice.getParty().getName());
        model.addAttribute("issuedBy", invoice.getIssuer().getName());
        model.addAttribute("expenses", invoice.getExpenses());
        model.addAttribute("total", invoice.getExpenses().stream()
                .map(Expense::getValue)
                .reduce(Float::sum)
                .orElse(0f));

        return "invoice"; // Renders invoice.html from templates/
    }
}