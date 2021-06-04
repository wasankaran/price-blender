package com.sc.priceblender;

public interface IPricingService {
    Price getPrice() throws InterruptedException;
    String getSourceName();
}
