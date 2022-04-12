package Lada303.lesson4;


import Lada303.lesson3.Queue;
import java.util.ListIterator;

public class lesson4 {

    public static void main(String[] args) {

        //проверка методов Двусвязного списка
        examinationDoubleLinkedList();
        //проверка Queue из ДЗ 3 , но через Deque, на основе двусвязного списка
        examinationQueue();
        //проаерка Deque - полностью всех методов
        examinationDeque();



    }
    //Проверка работы двухсвязного списка
    private static void examinationDoubleLinkedList() {
        DoublyLinkedListImp<Integer> list = new DoublyLinkedListImp<>();
        //просмотры
        System.out.println("size -> " + list.size());
        System.out.println("isEmpty -> " + list.isEmpty());
        System.out.println("contains 3 -> " + list.contains(3));
        System.out.println("getFirst -> " + list.getFirst());
        System.out.println("getLast -> " + list.getLast());

        //добавления
        list.insertFirst(1);
        list.insertFirst(2);
        list.insertFirst(3);
        list.insertFirst(4);
        list.insertFirst(5);
        list.displayFromHead();
        list.displayFromTail();
        list.insertLast(6);
        list.displayFromHead();
        list.displayFromTail();
        list.insertLast(7);
        list.displayFromHead();
        list.displayFromTail();
        list.insert(2, 11);
        list.displayFromHead();
        list.displayFromTail();

        //просмотры
        System.out.println("size -> " + list.size());
        System.out.println("isEmpty -> " + list.isEmpty());
        System.out.println("contains 3 -> " + list.contains(3));
        System.out.println("getFirst -> " + list.getFirst());
        System.out.println("getLast -> " + list.getLast());

        //удаления
        list.removeFirst();
        list.displayFromHead();
        list.displayFromTail();
        list.removeLast();
        list.displayFromHead();
        list.displayFromTail();
        list.remove(2);
        list.displayFromHead();
        list.displayFromTail();

        //просмотры
        System.out.println("size -> " + list.size());
        System.out.println("contains 11 -> " + list.contains(11));
        System.out.println("getFirst -> " + list.getFirst());
        System.out.println("getLast -> " + list.getLast());
        System.out.println();

        //проверка итератора
        System.out.println("iterator");
        ListIterator<Integer> iter = list.iterator();
        while (iter.hasNext()) {
            System.out.print(iter.next() + "-> ");
        }
        System.out.println();
        while (iter.hasPrevious()) {
            System.out.print(iter.previous() + "-> ");
        }
        System.out.println();
        list.displayFromHead();
        System.out.println("element = " + iter.next());
        System.out.println("nextIndex - " + iter.nextIndex());
        System.out.println("previousIndex - " + iter.previousIndex());
        System.out.println("set element");
        iter.set(33);
        list.displayFromHead();
        System.out.println("add element");
        iter.add(44);
        list.displayFromHead();
        list.displayFromTail();
        System.out.println("remove element");
        iter.remove();
        list.displayFromHead();
        list.displayFromTail();
        //просмотры
        System.out.println("size -> " + list.size());
        System.out.println("contains 33 -> " + list.contains(33));
        System.out.println("getFirst -> " + list.getFirst());
        System.out.println("getLast -> " + list.getLast());
        System.out.println("element = " + iter.next());
        System.out.println("nextIndex - " + iter.nextIndex());
        System.out.println("previousIndex - " + iter.previousIndex());
        System.out.println();
    }

    // Проверяем очередь на основе двусвязного списка
    // на той ж последовательности, что и в ДЗ 3
    private static void examinationQueue() {
        System.out.println("Очередь на сонове двусявязного списка");
        Queue<Integer> queue = new DequeImp<>();
        printInsertQueue(queue, 1);
        printInsertQueue(queue, 2);
        printInsertQueue(queue, 3);
        printInsertQueue(queue, 4);
        printInsertQueue(queue, 5);
        printRemoveQueue(queue);
        printRemoveQueue(queue);
        printInsertQueue(queue,6);
        printInsertQueue(queue,7);
        printRemoveQueue(queue);
        printRemoveQueue(queue);
        printRemoveQueue(queue);
        printInsertQueue(queue, 8);
        printRemoveQueue(queue);
        printRemoveQueue(queue);
        printRemoveQueue(queue);
        printRemoveQueue(queue);
        printInsertQueue(queue, 9);
        // Проверим другие методы
        System.out.println("peekFront - " + queue.peekFront());
        System.out.println("isEmpty - " + queue.isEmpty());
        System.out.println("isFull - " + queue.isFull());
        System.out.println("size - " + queue.size());
        System.out.println();
    }

    private static void printInsertQueue (Queue<Integer> queue, int n) {
        System.out.println("Добавить " + n + " - " + queue.insert(n));
      //  System.out.println("Массив " + Arrays.toString(queue.getData()));
        System.out.print("Очередь ");
        queue.display();
    }

    private static void printRemoveQueue (Queue<Integer> queue) {
        System.out.println("Изъятие из очереди - " + queue.remove());
      //  System.out.println("Массив " + Arrays.toString(queue.getData()));
        System.out.print("Очередь ");
        queue.display();
    }

    //Проверим полную реализацию Deque
    private static void examinationDeque() {
        DequeImp<Integer> deque = new DequeImp<>();
        System.out.println(deque.insert(1));
        deque.display();
        System.out.println(deque.insertLeft(2));
        deque.display();
        System.out.println(deque.insertRight(3));
        deque.display();
        System.out.println(deque.insert(4));
        deque.display();
        System.out.println(deque.insert(5));
        deque.display();
        System.out.println(deque.remove());
        deque.display();
        System.out.println(deque.removeLeft());
        deque.display();
        System.out.println(deque.removeRight());
        deque.display();
    }


}
