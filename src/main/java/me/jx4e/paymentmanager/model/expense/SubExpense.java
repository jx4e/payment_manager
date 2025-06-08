package me.jx4e.paymentmanager.model.expense;

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
}
