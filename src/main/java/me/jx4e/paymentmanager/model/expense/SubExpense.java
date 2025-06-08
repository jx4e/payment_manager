package me.jx4e.paymentmanager.model.expense;

import static j2html.TagCreator.li;
import static j2html.TagCreator.span;

public class SubExpense extends Expense {
    private final float total;

    public SubExpense(String name, String description, float value, float total) {
        super(name, description, value);
        this.total = total;
    }

    public SubExpense(TotalExpense totalExpense, float value) {
        super(totalExpense.name, totalExpense.description, value);
        this.total = totalExpense.value;
    }

    public float getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return super.toString() + " (" + Math.round((getValue() / getTotal()) * 100) +  "% of total)";
    }

    @Override
    public String generateHtml() {
        return li(
                span(name),
                span(description),
                span(String.format("$%.2f", value) + " (" + Math.round((getValue() / getTotal()) * 100) +  "% of total)")
        ).withStyle("display:flex; justify-content:space-between;").render();
    }
}
