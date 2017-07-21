package com.routeone.interview;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

class StoreReceipt implements Receipt {
    StoreReceipt() {
        total = BigDecimal.ZERO;
    }

    @Override
    public String getFormattedTotal() {
        return DecimalFormat.getCurrencyInstance(Locale.US).format(total.doubleValue());
    }

    @Override
    public List<String> getOrderedItems() {
        Collections.sort(items);
        List<String> returnList = new ArrayList<>();
        for (Item item : items) {
            returnList.add(item.getName());
        }
        return returnList;
    }

    void addItem(Item item) {
        items.add(item);
        total = total.add(item.getPrice());
    }

    private final List<Item> items = new ArrayList<>();
    private BigDecimal total;
}
