package Lada303.lesson8;

public class Lesson8 {


    public static void main(String[] args) {

        HashTable<Product, Integer> hashTable = new HashTableImpl<>(5);

        System.out.println("Orange " + hashTable.put(new Product(1, "Orange"), 150)); //1
        System.out.println("Banana " + hashTable.put(new Product(77, "Banana"), 100)); //2
        System.out.println("Carrot " + hashTable.put(new Product(67, "Carrot"), 228)); //2
        System.out.println("Lemon " + hashTable.put(new Product(60, "Lemon"), 250)); //0
        System.out.println("Milk " + hashTable.put(new Product(51, "Milk"), 120)); //1
        System.out.println("Potato " + hashTable.put(new Product(21, "Potato"), 67)); //1, но не поместился по initialCapacity

        System.out.println("Size is " + hashTable.size());
        hashTable.display();

        System.out.println("Cost potato is " + hashTable.get(new Product(21, "Potato")));
        System.out.println("Cost banana is " + hashTable.get(new Product(77, "Banana")));
        System.out.println("Cost carrot is " + hashTable.get(new Product(67, "Carrot")));

        System.out.println("remove Potato -> " + hashTable.remove(new Product(21, "Potato")));
        System.out.println("remove Banana ->" + hashTable.remove(new Product(77, "Banana")));

        System.out.println("Cost potato is " + hashTable.get(new Product(21, "Potato")));
        System.out.println("Cost banana is " + hashTable.get(new Product(77, "Banana")));
        System.out.println("Cost carrot is " + hashTable.get(new Product(67, "Carrot")));

        System.out.println("Pineapple " + hashTable.put(new Product(47, "Pineapple"), 228));
        System.out.println("get cost Pineapple " + hashTable.get(new Product(47, "Pineapple")));

        hashTable.display();
    }
}
