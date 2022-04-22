package Lada303.lesson5;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Knapsack {

    private List<Things> knapsackThings;
    private int knapsackWeight;
    private int maxKnapsackCost;

    public Knapsack() {
        knapsackThings = new ArrayList<>();
    }

    public List<Things> packKnapsack(List<Things> allThings, int knapsackWeight) {
        knapsackThings.clear();
        this.knapsackWeight = knapsackWeight;
        find(allThings, 0, 0, new ArrayDeque<>());
        return  knapsackThings;
    }

    private void find(List<Things> listAll, int currentCost, int currentWeight, ArrayDeque<Things> currentKnapsack) {
        for (int i = 0; i < listAll.size(); i++) {
            int thisCurrentCost = currentCost;
            int thisCurrentWeight = currentWeight;
            if (currentWeight + listAll.get(i).getWeight() <= knapsackWeight) {
                currentKnapsack.addLast(listAll.get(i));
                thisCurrentCost += listAll.get(i).getPrice();
                thisCurrentWeight += listAll.get(i).getWeight();
                if (thisCurrentCost > maxKnapsackCost) {
                    maxKnapsackCost = thisCurrentCost;
                    knapsackThings = new ArrayList<>(currentKnapsack);
                    //System.out.println("MAX ->  " + knapsackThings + " cost = " + maxKnapsackCost);
                }
                List<Things> newListAll = new ArrayList<>(listAll);
                newListAll.remove(i);
                find(newListAll, thisCurrentCost, thisCurrentWeight, currentKnapsack);
            }
        }
        if (!currentKnapsack.isEmpty()) {
            currentKnapsack.removeLast();
        }
    }

}



