package com.techelevator;

public class Chip extends VendingMachineItem{

    public Chip(String name, double price) {
        super(name, price);
    }


    @Override
    public String getMessage() {
        return "Crunch Crunch, Yum!";
    }
}
