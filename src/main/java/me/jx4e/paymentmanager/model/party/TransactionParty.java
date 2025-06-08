package me.jx4e.paymentmanager.model.party;

public abstract class TransactionParty {
    private final String name;

    public TransactionParty(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
