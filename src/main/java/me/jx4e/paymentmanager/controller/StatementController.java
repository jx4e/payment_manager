package me.jx4e.paymentmanager.controller;

import me.jx4e.paymentmanager.model.Statement;
import me.jx4e.paymentmanager.service.StatementService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/statements")
public class StatementController {
    private final StatementService statementService;

    public StatementController(StatementService statementService) {
        this.statementService = statementService;
    }

    @GetMapping
    public String getStatements(Model model) {
        model.addAttribute("statements", statementService.getAllStatements());
        model.addAttribute("object", new Statement());
        return "statements/list";
    }

    @GetMapping("/{id}")
    public String editStatement(@PathVariable("id") Long id, Model model) {
        Optional<Statement> statement = statementService.getStatementById(id);

        if (statement.isPresent()) {
            model.addAttribute("statement", statement.get());
            return "statements/detail";
        }

        return "redirect:/dashboard";
    }

    @PostMapping("/add")
    public String addStatement(@ModelAttribute("statement") Statement statement, RedirectAttributes redirectAttributes) {
        statementService.addStatement(statement);
        redirectAttributes.addFlashAttribute("success", "Statement added successfully!");
        return "redirect:/statements";
    }

    @PostMapping("/{id}/edit")
    public String editStatement(@PathVariable("id") Long id, @ModelAttribute Statement updatedStatement, RedirectAttributes redirectAttributes) {
        statementService.updateStatement(id, updatedStatement);
        redirectAttributes.addFlashAttribute("success", "Statement updated successfully!");
        return "redirect:/statements";
    }

    @PostMapping("/{id}/delete")
    public String deleteStatement(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        statementService.removeStatementById(id);
        redirectAttributes.addFlashAttribute("success", "Statement removed successfully!");
        return "redirect:/statements";
    }
}