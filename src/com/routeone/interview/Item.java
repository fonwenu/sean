package com.routeone.interview;

import java.math.BigDecimal;

class Item implements Comparable<Item> {
    Item(String name, BigDecimal price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    String getName() {
        return name;
    }

    BigDecimal getPrice() {
        return price;
    }

    String getCategory() {
        return category;
    }

    @Override
    public int compareTo(Item other) {
        if (!this.price.equals(other.price)) {
            return other.price.compareTo(this.price);
        }
        return this.name.compareTo(other.name);
    }

    private final String name;
    private final BigDecimal price;
    private final String category;
}
