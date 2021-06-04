package com.sc.priceblender;

import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class PriceBlender {

    final ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private final Map<String, Price> map = new ConcurrentHashMap<>();

    public List<Price> sourcePrices(Iterable<IPricingService> ws) {
        CompletionService<Price> completionService = new ExecutorCompletionService<>(executor);
        ws.forEach(service ->
                completionService.submit(service::getPrice)
        );

        ws.forEach(s -> {
            try {
                Future<Price> f = completionService.take();
                Price p = f.get();
                 map.put(s.getSourceName(), p);
                 displayResult(p);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

        executor.shutdown();

        // no need to copy the List as Price is immutable
        return List.copyOf(map.values());
    }

    private void displayResult(Price p) {
        System.out.println("Price for " + p.getSource() + " is " + p.getPrice());
    }

    public void displayResult(){
        map.forEach((k, v) -> System.out.println("Price for " + v.getSource() + " is " + v.getPrice()));
    }
}


