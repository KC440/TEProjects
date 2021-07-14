package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Reader {


    private final File inputFile = new File("vendingmachine.csv");


    public void importFile(Map<String, VendingMachineItem> items) {
        try(Scanner scanner = new Scanner(inputFile)) {
        while(scanner.hasNextLine()) {
            String[] inputLine = scanner.nextLine().split("\\|");
            switch (inputLine[3]) {
                case "Chip":
                    items.put(inputLine[0], new Chip(inputLine[1], Double.parseDouble(inputLine[2])));
                    break;
                case "Candy":
                    items.put(inputLine[0], new Candy(inputLine[1], Double.parseDouble(inputLine[2])));
                    break;
                case "Drink":
                    items.put(inputLine[0], new Beverage(inputLine[1], Double.parseDouble(inputLine[2])));
                    break;
                case "Gum":
                    items.put(inputLine[0], new Gum(inputLine[1], Double.parseDouble(inputLine[2])));
                    break;
            }
        }
        } catch (FileNotFoundException fnfe) {
            System.out.println(fnfe.getMessage());
        }

    }

}

