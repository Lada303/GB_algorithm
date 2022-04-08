package Lada303.lesson1;

import java.util.ArrayList;
import java.util.Random;

public class Lesson1 {

    private static final int COUNT_ELEMENTS = 10000;
    private static final int START_PRICE = 500;
    private static final int END_PRICE = 2000;
    private static final int STEP_PRICE = 50;
    private static final int START_RAM = 4;
    private static final int END_RAM = 24;
    private static final int STEP_RAM = 4;

    private static Notebook[] arrNotebooks;
    private static final Random random = new Random();
    private static int[] arrPrices;
    private static int countPrices;
    private static int[] arrRAM;
    private static int countRAM;

    public static void main(String[] args) {
        // генерируем массив случанйых объектов Notebook
        countPrices = (END_PRICE - START_PRICE ) / STEP_PRICE + 1;
        countRAM = (END_RAM - START_RAM ) / STEP_RAM + 1;
        arrPrices = generateArrPrices();
        arrRAM = generateArrRAM();
        arrNotebooks = generateArrNotebooks();
        //проверка генерации вспомагательных массивов и самого массива
//        System.out.println(Arrays.toString(arrPrices));
//        System.out.println(Arrays.toString(arrRAM));
//        for (int i = 0; i < 10; i++) {
//            System.out.println(arrNotebooks[random.nextInt(COUNT_ELEMENTS)]);
//        }

        // Проверяем время работы сортировки по задачи 1
        System.out.println("ДЗ 1 задача 1 -  время сортировки Ноутбуков в 10 000 ед");
        long startTime = System.currentTimeMillis();
        sortArrNotebooks();
        long stopTime = System.currentTimeMillis();
        System.out.println((stopTime - startTime) + " ms");

        //смотрим первые 100 строк отсортированного массива для проверки
        for (int i = 0; i < 100; i++) {
            System.out.println(arrNotebooks[i]);
        }

        //проверяем время работы сортировки выбором в 2 стороны на случайном массиве
        Integer[] someArr = generateRandomArr(50000);
        //System.out.println(Arrays.toString(someArr));
        System.out.println("ДЗ 1 задача 2 -  время сортировки массиыва чисел в 50 000 ед.");
        startTime = System.currentTimeMillis();
        doubleSelectionSort(someArr);
        stopTime = System.currentTimeMillis();
        System.out.println((stopTime - startTime) + " ms");
        //System.out.println(Arrays.toString(someArr));
    }

    //сортировка массива ноутбуков
    //делаем три раза стабильную сортировку подсчетом - вначале по производителю, потом по ОЗУ, потом по цене
    //сложност O (n * countPrices) + O (n * countRAM) + O(n * Manufacturer.NUMBERS)   ->   O(n)
    private static void sortArrNotebooks() {
        int[] arrManufacturer = new int[Manufacturer.NUMBERS];
        for (int i = 0; i < Manufacturer.NUMBERS; i++) {
            arrManufacturer[i] = i;
        }
        sortArrNotebooksByParameter(Manufacturer.NUMBERS, arrManufacturer, 3);

        sortArrNotebooksByParameter(countRAM, arrRAM, 2);

        sortArrNotebooksByParameter(countPrices, arrPrices, 1);
    }

    // сортировка массива по полю
    //3 - сортировка по производителю
    // 2 - сортировка по озу
    // 1 - сортировка по цене
    private static void sortArrNotebooksByParameter(int count, int[] arrParameter, int parameter) {
        ArrayList<Notebook>[] sortByParameter = new ArrayList[count];
        for (Notebook notebook : arrNotebooks) {
            for (int j = 0; j < count; j++) {
                int notebooksParameter = 0;
                switch (parameter) {
                    case 1 -> notebooksParameter = notebook.getPrice();
                    case 2 -> notebooksParameter = notebook.getRam();
                    case 3 -> notebooksParameter = notebook.getManufacturer().getIndex();
                }
                if (notebooksParameter == arrParameter[j]) {
                    if (sortByParameter[j] == null) {
                        sortByParameter[j] = new ArrayList<>();
                    }
                    sortByParameter[j].add(notebook);
                }
            }
        }

        int z = 0;
        for (int i = 0; i < sortByParameter.length; i++) {
            if (sortByParameter[i] == null) {
                continue;
            }
            for (int j = 0; j < sortByParameter[i].size(); j++) {
                arrNotebooks[z] = sortByParameter[i].get(j);
                z++;
            }
        }
    }

    //сортировка выбором с двух сторон
    private static  <E extends Comparable<? super E>> void doubleSelectionSort(E[] arr) {
        for (int i = 0; i < arr.length / 2; i++) {
            int indexMin = i;
            int indexMax = arr.length - 1 - i;
            E temp;

            for (int j = i + 1; j < arr.length - i; j++) {
                if (arr[j].compareTo(arr[indexMin]) < 0) {
                    indexMin = j;
                }
            }
            if (indexMin != i) {
                temp = arr[i];
                arr[i] = arr[indexMin];
                arr[indexMin] = temp;
            }

            for (int j = i + 1; j < arr.length - i; j++) {
                if (arr[j].compareTo(arr[indexMax]) > 0) {
                    indexMax = j;
                }
            }
            if (indexMax != arr.length - 1 - i) {
                temp = arr[arr.length - 1 - i];
                arr[arr.length - 1 - i] = arr[indexMax];
                arr[indexMax] = temp;
            }
            //System.out.println(Arrays.toString(arr));
        }
    }

    //генерация массива возможных цен от 500 до 200 шаг 50
    private static int[] generateArrPrices() {
        int[] arr = new int[countPrices];
        arr[0] = START_PRICE;
        for (int i = 1; i < countPrices; i++) {
            arr[i] = arr[i - 1] + STEP_PRICE;
        }
        return arr;
    }

    //генерация массива возможных ОЗУ от 4 до 24 шаг 4
    private static int[] generateArrRAM() {
        int[] arr = new int[countRAM];
        arr[0] = START_RAM;
        for (int i = 1; i < countRAM; i++) {
            arr[i] = arr[i - 1] + START_RAM;
        }
        return arr;
    }

    //генерация самого массива Ноутбуков
    private static Notebook[] generateArrNotebooks() {
        Notebook[] arr = new Notebook[COUNT_ELEMENTS];
        for (int i = 0; i < COUNT_ELEMENTS; i++) {
            int price = arrPrices[random.nextInt(countPrices)];
            int ram = arrRAM[random.nextInt(countRAM)];
            int manufacturer = random.nextInt(Manufacturer.NUMBERS);
            arr[i] = new Notebook(price, ram, Manufacturer.getManufacturerByIndex(manufacturer));
        }
        return arr;
    }

    //генерация массива случайных чисел
    private static Integer[] generateRandomArr(int size) {
        Integer[] arr = new Integer[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(arr.length);
        }
        return arr;
    }

}

