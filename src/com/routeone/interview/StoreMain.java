package com.routeone.interview;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class StoreMain {
    /**
     * Used to test that {@link StoreRegister} and {@link Receipt} methods have been implemented properly.
     * @param args The first argument should be the inventory file name.  The remaining arguments should be the list of
     *             items being purchased
     */
    public static void main(String[] args) {
        if (args.length < 1) {
            throw new RuntimeException("No program arguments specified.  Require at least 1 argument to run.");
        }
        String inventoryFileName = args[0];
        List<String> items = Arrays.asList(Arrays.copyOfRange(args, 1, args.length));

        StoreRegister register = createRegister(inventoryFileName);
        Receipt receipt = register.checkoutOrder(items);
        printReceipt(receipt);
    }

    private static StoreRegister createRegister(String inventoryFileName) {
        StoreRegister register = new StoreRegister();
        register.loadInventory(new File(inventoryFileName));
        return register;
    }

    private static void printReceipt(Receipt receipt) {
        System.out.println("Your total is: " + receipt.getFormattedTotal());
        System.out.println("Items purchased: ");
        for (String itemName : receipt.getOrderedItems()) {
            System.out.println("\t" + itemName);
        }
    }
}
