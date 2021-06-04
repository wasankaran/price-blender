package com.sc.priceblender;

import java.util.ArrayList;
import java.util.List;

public class PricingClient {

    public static void main(String[] args) {
        Iterable<IPricingService> sources = getPricingSources();

        PriceBlender blender = new PriceBlender();
        List<Price> result = blender.sourcePrices(sources);
        //result.forEach(r -> System.out.println(r.getSource() + ":" + r.getPrice()));
    }

    // ideally each concrete implementation would be separate
    private static Iterable<IPricingService> getPricingSources() {
        List<IPricingService> sources = new ArrayList<>();
        sources.add(new PricingService("Bloomberg"));
        sources.add(new PricingService("FxAll"));
        sources.add(new PricingService("Reuters"));
        return sources;
    }
}
