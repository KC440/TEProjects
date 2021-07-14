package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

public class VendingMachineTests {

    @Test
    public void attempt_to_buy_not_enough_money_should_not_dispense() {
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.feedMoney(1);
        vendingMachine.buyItem(vendingMachine.getStock().get("A1"));
        Assert.assertEquals(vendingMachine.getStock().get("A1").getNumLeft(), 5);
    }

    @Test
    public void attempt_to_buy_not_enough_money_should_not_alter_money() {
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.feedMoney(1);
        vendingMachine.buyItem(vendingMachine.getStock().get("A1"));
        Assert.assertEquals(vendingMachine.getTotalMoneyForMath(), 1, 0.001);
    }

    @Test
    public void buy_with_enough_money_should_dispense() {
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.feedMoney(10);
        vendingMachine.buyItem(vendingMachine.getStock().get("A1"));
        Assert.assertEquals(vendingMachine.getStock().get("A1").getNumLeft(), 4);
    }

    @Test
    public void buy_with_enough_money_should_subtract_money() {
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.feedMoney(10);
        vendingMachine.buyItem(vendingMachine.getStock().get("A1"));
        Assert.assertEquals(vendingMachine.getTotalMoneyForMath(), 6.95, 0.001);
    }

    @Test
    public void feed_money_expect_correct_amount_ofMoney() {
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.feedMoney(1);
        vendingMachine.feedMoney(5);
        vendingMachine.feedMoney(10);
        vendingMachine.feedMoney(20);
        Assert.assertEquals(vendingMachine.getTotalMoneyForMath(), 36, 0.001);
    }

    @Test
    public void finish_transaction_should_empty_change() {
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.feedMoney(5);
        vendingMachine.buyItem(vendingMachine.getStock().get("A1"));
        vendingMachine.finishTransaction();
        Assert.assertEquals(vendingMachine.getTotalMoneyForMath(), 0, 0.001);
    }

    @Test
    public void finish_transaction_should_give_correct_change() {
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.feedMoney(5);
        vendingMachine.buyItem(vendingMachine.getStock().get("B2"));
        vendingMachine.buyItem(vendingMachine.getStock().get("D1"));
        vendingMachine.finishTransaction();
        Assert.assertEquals(vendingMachine.getQuarters(), 10);
        Assert.assertEquals(vendingMachine.getDimes(), 1);
        Assert.assertEquals(vendingMachine.getNickles(), 1);
    }


}
