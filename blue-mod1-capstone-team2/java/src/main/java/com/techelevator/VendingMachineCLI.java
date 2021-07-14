package com.techelevator;

import com.techelevator.view.Menu;

import java.util.Map;
import java.util.Scanner;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT };

	private final Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {
		VendingMachine vendingMachine = new VendingMachine();
		Logger logger = new Logger();
		logger.clearLog();

		
		boolean shouldRun = true;
		while (shouldRun) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			switch (choice) {
				case MAIN_MENU_OPTION_DISPLAY_ITEMS:
					// display vending machine items
					for (Map.Entry<String, VendingMachineItem> entry : vendingMachine.getStock().entrySet()) {
						System.out.println(entry.getKey() + ") " + entry.getValue().getName() +
								" $" + entry.getValue().getPrice() + " | " + entry.getValue().getNumLeft() + " left");
					}
					break;
				case MAIN_MENU_OPTION_PURCHASE:
					// do purchase
					boolean finished = false;
					while (!finished) {
						System.out.println("1) Feed Money");
						System.out.println("2) Select Product");
						System.out.println("3) Finish Transaction" + "\r\n");
						System.out.println("Current money provided: $" + vendingMachine.getTotalMoney());
						System.out.print("Choose an option >>> ");
						Scanner scanner = new Scanner(System.in);
						String purchaseMenuSelection = scanner.nextLine();
						switch (purchaseMenuSelection) {
							case "1":
								boolean finishedFeedMoney = false;
								int totalMoney;
								System.out.print("This machine accepts \"1\"s, \"5\"s, \"10\"s, and \"20\"s" + "\r\n");
								while(!finishedFeedMoney) {
									System.out.print("\r\n" + "Enter a bill, or enter \"0\" if finished >>> ");
									Scanner scannerFeedMoney = new Scanner(System.in);
									String billChoice = scannerFeedMoney.nextLine();
									switch (billChoice) {
										case "1":
											totalMoney = 1;
											vendingMachine.feedMoney(totalMoney);
											logger.logFile("FEED MONEY:", String.format("$%s.00 $%.2f", billChoice, vendingMachine.getTotalMoneyForMath()));
											break;
										case "5":
											totalMoney = 5;
											vendingMachine.feedMoney(totalMoney);
											logger.logFile("FEED MONEY:", String.format("$%s.00 $%.2f", billChoice, vendingMachine.getTotalMoneyForMath()));
											break;
										case "10":
											totalMoney = 10;
											vendingMachine.feedMoney(totalMoney);
											logger.logFile("FEED MONEY:", String.format("$%s.00 $%.2f", billChoice, vendingMachine.getTotalMoneyForMath()));
											break;
										case "20":
											totalMoney = 20;
											vendingMachine.feedMoney(totalMoney);
											logger.logFile("FEED MONEY:", String.format("$%s.00 $%.2f", billChoice, vendingMachine.getTotalMoneyForMath()));
											break;
										case "0":
											finishedFeedMoney = true;

											break;
										default:
											System.out.println("\r\n" + "That is not a valid bill choice.");
											break;
									}

								}
								break;
							case "2":
								for (Map.Entry<String, VendingMachineItem> entry : vendingMachine.getStock().entrySet()) {
									System.out.println(entry.getKey() + ") " + entry.getValue().getName() +
											" $" + entry.getValue().getPrice() + " | " + entry.getValue().getNumLeft() + " left");
								}
								boolean finishedSelectItem = false;
								VendingMachineItem selectedItem = null;
								String selectedKey = "";

								while(!finishedSelectItem) {
									Scanner scannerSelectItem = new Scanner(System.in);
									System.out.print("Please enter desired item code or \"0\" to cancel >>> ");
									String userInput = scannerSelectItem.nextLine();
									selectedKey = userInput;
									selectedItem = vendingMachine.getStock().get(userInput);

									if (vendingMachine.getStock().containsKey(userInput) && selectedItem.getNumLeft() > 0) {
										finishedSelectItem = true;
									} else if (vendingMachine.getStock().containsKey(userInput) && selectedItem.getNumLeft() == 0) {
										System.out.println("\r\nThat item is sold out!\r\n");
										finishedSelectItem = true;
									} else if (userInput.equals("0")) {
										finishedSelectItem = true;
									} else {
										System.out.println("\r\nThat is not a valid choice.\r\n");
										finishedSelectItem = true;
									}
								}
								if(selectedItem != null && selectedItem.getNumLeft() != 0) {
									String totalMoneyBefore = vendingMachine.getTotalMoney();
								vendingMachine.buyItem(selectedItem);
									if(vendingMachine.getSaleStatus()) {
										logger.logFile(selectedItem.getName() + " " + selectedKey + ":", String.format("$%s $%s",
												totalMoneyBefore, vendingMachine.getTotalMoney()));
									}
								}

								break;
							case "3":
								logger.logFile("GIVE CHANGE:", String.format("$%s $0.00", vendingMachine.getTotalMoney()));
								vendingMachine.finishTransaction();
								finished = true;
								break;
							default:
								System.out.println("\r\nThat is not a valid option.\r\n");
								break;
						}
					}
					break;
				case MAIN_MENU_OPTION_EXIT:

					shouldRun = false;
					break;
			}
		}
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
