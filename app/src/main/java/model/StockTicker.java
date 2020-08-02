package model;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class StockTicker {

    private String name;
    private double pct;
    private double price;

    public StockTicker() {
        int min = 0;
        int max = 50;
        this.pct = ThreadLocalRandom.current().nextDouble(-100, 100);
        if (this.pct < 0) {
            min += 50;
            max += 50;
        }
        this.price = ThreadLocalRandom.current().nextDouble(min, max);
        this.name = generateStockName(new Random().nextInt(1) + 3);
    }

    public String generateStockName(Integer length) {

        int leftLimit = 97;
        int rightLimit = 122;
        int targetStringLength = length;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();

        return generatedString.toUpperCase();
    }

    public String getName() {
        return this.name;
    }

    public double getPct() {
        return Math.floor(this.pct * 100) / 100;
    }

    public double getPrice() {
        return Math.floor(this.price * 100) / 100;
    }

    public double getFinishPrice() {
        return Math.floor(this.price + ((this.pct / 100) * this.price));
    }
}
