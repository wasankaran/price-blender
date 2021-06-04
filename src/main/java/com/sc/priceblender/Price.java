package com.sc.priceblender;

public final class Price {
    private final String source;
    private final double price;

    Price(String source, double price){
        this.source = source;
        this.price = price;
    }

    public String getSource() {
        return source;
    }

    public double getPrice() {
        return price;
    }
}
