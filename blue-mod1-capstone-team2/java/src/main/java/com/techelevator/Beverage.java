package com.techelevator;

public class Beverage extends VendingMachineItem{

    public Beverage(String name, double price) {
        super(name, price);
    }
    @Override
    public String getMessage() {
        return "Glug Glug, Yum!";
    }

}
