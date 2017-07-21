package com.routeone.interview;

import java.io.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StoreRegister {
    public void loadInventory(File inventoryFile){
        try (FileReader fileReader = new FileReader(inventoryFile)) {
            try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                String currentLine = null;
                int lineNumber = 1;
                while ((currentLine = bufferedReader.readLine()) != null) {
                    lineNumber++; // Used to provide a more detailed error message if the file is not formatted correctly
                    String[] itemDetails = currentLine.split(",");
                    if (itemDetails.length != 3) {
                        throw new RuntimeException(
                                "Line " + lineNumber + " of inventory file has the wrong number of columns.  " +
                                "Expected 3 comma-separated columns.");
                    }
                    String itemName = itemDetails[0];
                    BigDecimal price = new BigDecimal(itemDetails[1]);
                    String category = itemDetails[2];
                    inventoryByItemName.put(itemName, new Item(itemName, price, category));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading from inventory file.", e);
        }
    }

    public Receipt checkoutOrder(List<String> items) {
        StoreReceipt receipt = new StoreReceipt();
        for (String itemName : items) {
            if (!inventoryByItemName.containsKey(itemName)) {
                throw new RuntimeException("Request to purchase " + itemName + ", which is not in inventory.");
            }
            receipt.addItem(inventoryByItemName.get(itemName));
        }
        return receipt;
    }

    private final Map<String, Item> inventoryByItemName = new HashMap<>();
}