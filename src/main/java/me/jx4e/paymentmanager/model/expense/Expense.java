package me.jx4e.paymentmanager.model.expense;

// YYJVC

public abstract class Expense {
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
        return getName() + " | " + getDescription() + " | $" + getValue();
    }
}
