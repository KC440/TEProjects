package com.techelevator;


import java.util.Map;
import java.util.TreeMap;


public class VendingMachine {

    private final Map<String, VendingMachineItem> stock = new TreeMap<>();
    private double totalMoney = 0;
    private int quarters;
    private int dimes;
    private int nickles;
    private boolean madeSale;

    public VendingMachine() {
        Reader reader = new Reader();
        reader.importFile(stock);
    }
    public Map<String, VendingMachineItem> getStock() {
        return stock;
    }
    public String getTotalMoney() {
        return String.format("%.2f", totalMoney);
    }
    public double getTotalMoneyForMath() {
        return totalMoney;
    }

    public int getQuarters() { return quarters; }
    public int getDimes() { return dimes; }
    public int getNickles() { return nickles; }
    public boolean getSaleStatus() {
        return madeSale;
    }

    public void feedMoney(int totalMoney) {
        this.totalMoney += totalMoney;
    }
    public void finishTransaction() {
        int dollars = (int) totalMoney;
        String centsString = getTotalMoney().substring(getTotalMoney().length() - 2);
        int cents = Integer.parseInt(centsString);
        quarters = 0;
        dimes = 0;
        nickles = 0;
        quarters += dollars * 4;
        while(cents >= 25) {
            quarters++;
            cents -= 25;
        }
        while (cents >= 10) {
            dimes++;
            cents -= 10;
        }
        while (cents >= 5) {
            nickles++;
            cents -= 5;
        }
        System.out.println("\r\n$" + String.format("%.2f", totalMoney) + " is your change.\r\n");
        System.out.println("Your change included: ");
        System.out.println("Quarters: " + quarters);
        System.out.println("Dimes: " + dimes);
        System.out.println("Nickles: " + nickles);
        totalMoney = 0;
    }
    public void buyItem(VendingMachineItem item) {
        madeSale = false;
        if(getTotalMoneyForMath() < item.getPriceForMath()) {
            System.out.println("\r\n" + item.getName() + " costs $" + item.getPrice() +
                    " Please add more money, or select a new item." + "\r\n");
        } else {
            totalMoney -= item.getPriceForMath();
            item.dispense();
            madeSale = true;
            System.out.println("\r\nYou just bought " + item.getName() + " for $" + item.getPrice() +
                    "\r\n" + item.getMessage() + "\r\n" + "There are " + item.getNumLeft() + " of those left.\r\n");
        }
    }

}

