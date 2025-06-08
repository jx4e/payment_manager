package me.jx4e.paymentmanager.model.expense;

// YYJVC

import me.jx4e.paymentmanager.model.HtmlRenderable;

import static j2html.TagCreator.li;
import static j2html.TagCreator.span;

public abstract class Expense implements HtmlRenderable {
    protected final String name;
    protected final String description;
    protected final float value;

    public Expense(String name, String description, float value) {
        this.name = name;
        this.description = description;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public float getValue() {
        return value;
    }

    @Override
    public String toString() {
        return getName() + " | " + getDescription() + " | $" + String.format("$%.2f", value);
    }

    @Override
    public String generateHtml() {
        return li(
                span(name),
                span(description),
                span(String.format("$%.2f", value))
        ).withStyle("display:flex; justify-content:space-between;").render();
    }
}
