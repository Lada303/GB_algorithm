package Lada303.lesson4;

/*
Интерфейс для двусвязного списока // п.1 для ДЗ
В т.ч. вставлен метод insert(), // п.3 для ДЗ
*/

public interface DoublyLinkedList<E> {

    void insertFirst(E value);

    void insertLast(E value);

    E removeFirst();

    E removeLast();

    E getFirst();

    E getLast();

    boolean insert(int index, E value);

    boolean remove(E value);

    boolean contains(E value);

    int size();

    boolean isEmpty();

    void displayFromHead();

    void displayFromTail();

    class Node<E> {
        E value;
        Node<E> next;
        Node<E> previous;

        public Node(E value, Node<E> next, Node<E> previous) {
            this.value = value;
            this.next = next;
            this.previous = previous;
        }
    }

}
