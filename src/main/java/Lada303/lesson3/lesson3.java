package Lada303.lesson3;

import java.util.Arrays;
import java.util.Random;

public class lesson3 {

    private static final int ARR_SIZE = 50000;

    public static void main(String[] args) {
        Random random = new Random();
        // Проверяем время работы задачи 1
        System.out.println("ДЗ 3 задача 1 - найти одно пропущенное число из идущих подряд чисел массива");
        int misNum = random.nextInt(ARR_SIZE);
        //misNum = 5;
        int[] arr = generateArr(ARR_SIZE, misNum);
        //System.out.println(Arrays.toString(arr));
        System.out.println("загаданное пропущенное число " + misNum + " из " + (ARR_SIZE));
        long startTime = System.currentTimeMillis();
        missingNumber(arr);
        long stopTime = System.currentTimeMillis();
        System.out.println((stopTime - startTime) + " ms");


        // Проверяем время работы задачи 2
        System.out.println("ДЗ 3 задача 2 - закольцевать очередь на массив");
        QueueImp<Integer> queue = new QueueImp<>(5);
        printInsertQueue(queue, 1);
        printInsertQueue(queue, 2);
        printInsertQueue(queue, 3);
        printInsertQueue(queue, 4);
        printInsertQueue(queue, 5);
        printInsertQueue(queue, 6);
        printRemoveQueue(queue);
        printRemoveQueue(queue);
        printInsertQueue(queue,6);
        printInsertQueue(queue,7);
        printInsertQueue(queue,8);
        printRemoveQueue(queue);
        printRemoveQueue(queue);
        printRemoveQueue(queue);
        printInsertQueue(queue, 8);
        printRemoveQueue(queue);
        printRemoveQueue(queue);
        printRemoveQueue(queue);
        printRemoveQueue(queue);
        printInsertQueue(queue, 9);
    }

    //Для поиска пропущенного числа используем бинарный поиск
    private static int missingNumber(int[] arr) {
        int countIterations = 0;
        int left = 0;
        int right = arr.length - 1;
        if (arr[right] != arr.length) {
            int base;
            while(left<=right){
                countIterations++;
//                System.out.printf("left %d - %d  /  right %d - %d\n", left, arr[left], right, arr[right]);
                if (arr[left] != left + 1) {
                    System.out.println("количество итераций: " + countIterations);
                    System.out.println("найдено пропущенное число: " + (left + 1));
                    return left + 1;
                }
                base = (right + left)/2;
 //               System.out.printf("base %d - %d \n", base, arr[base]);
                if (arr[base] == base + 1) {
                    left = base + 1;
                } else {
                    right = base;
                }
            }
        }
        System.out.println("количество итераций: " + countIterations);
        System.out.println("пропущенного числа не было!");
        return 0;
    }

    //Генерирует массив с пропущенным числом
    private static int[] generateArr(int size, int missingNumber) {
        int[] arr = new  int[size];
        for (int i = 0; i < size; i++) {
            if (missingNumber == 0) {
                arr[i] = i + 1;
            } else {
                arr[i] = i + 1 < missingNumber ? i + 1 : i + 2;
            }
        }
        return  arr;
    }

    private static void printInsertQueue (QueueImp<Integer> queue, int n) {
        System.out.println("Добавить 1 - " + queue.insert(n));
        System.out.println("Массив " + Arrays.toString(queue.getData()));
        System.out.print("Очередь ");
        queue.display();
    }

    private static void printRemoveQueue (QueueImp<Integer> queue) {
        System.out.println("Изъятие из очереди - " + queue.remove());
        System.out.println("Массив " + Arrays.toString(queue.getData()));
        System.out.print("Очередь ");
        queue.display();
    }

}
