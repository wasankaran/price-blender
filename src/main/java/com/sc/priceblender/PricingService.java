package com.sc.priceblender;

import java.util.Random;

public class PricingService implements IPricingService {

    private final String sourceName;

    PricingService(String sourceName) {
        this.sourceName = sourceName;
    }

    @Override
    public Price getPrice() throws InterruptedException {
        Thread.sleep(new Random().ints(1000, 5000).findFirst().getAsInt());
        return new Price(this.sourceName, new Random().nextDouble());
    }

    @Override
    public String getSourceName() {
        return sourceName;
    }
}
