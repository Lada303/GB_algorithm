package Lada303.lesson5;

import java.util.ArrayList;
import java.util.List;

public class Lesson5 {
    private static final double NUMBER = 4;
    private static final double DEGREE = 1.5;

    private static final int WEIGHT_KNAPSACK = 5;

    public static void main(String[] args) {
        //№1 ДЗ 5
        System.out.println("Возведение числа " + NUMBER + " в степень " + DEGREE);
        long startTime = System.currentTimeMillis();
        System.out.println("Ответ = " + new NumberToPower().raisingNumberToPower(NUMBER, DEGREE));
        long stopTime = System.currentTimeMillis();
        System.out.println((stopTime - startTime) + " ms");
        System.out.println();


        //№2 ДЗ 5
        List<Things> allThings = new ArrayList<>();
        allThings.add(new Things("Some1", 10000, 10));
        allThings.add(new Things("Some2", 1000, 1));
        allThings.add(new Things("Some3", 3000, 2));
        allThings.add(new Things("Some4", 3000, 4));
        allThings.add(new Things("Some5", 6000, 4));
        allThings.add(new Things("Some6", 5000, 8));
        allThings.add(new Things("Some7", 5000, 5));

        List<Things> knapsack = new Knapsack().packKnapsack(allThings, WEIGHT_KNAPSACK);
        System.out.println("Рюкзак содержит -> " + knapsack);
        int weight = 0;
        int cost = 0;
        for (Things things : knapsack) {
            weight += things.getWeight();
            cost += things.getPrice();
        }
        System.out.printf("Общая стоимость = %d, общий вес = %d\n", cost, weight);
    }

}
