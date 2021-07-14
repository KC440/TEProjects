package com.techelevator;

public abstract class VendingMachineItem {

    private final String name;
    private final double price;
    private int numLeft = 5;

    public VendingMachineItem(String name, double price) {
        this.name = name;
        this.price = price;


    }

    public String getName() {
        return name;
    }

    public double getPriceForMath() {
        return price;
    }

    public String getPrice() {
        return String.format("%.2f", price);
    }


    public int getNumLeft() {
        return numLeft;
    }

    public void dispense() {
        this.numLeft--;
    }

    public abstract String getMessage();
}
