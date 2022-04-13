package Lada303.lesson4;

public class DequeImp<E> implements Deque<E>{

    private final DoublyLinkedList<E> deque;

    public DequeImp() {
        this.deque = new DoublyLinkedListImp<>();
    }

    @Override
    public boolean insert(E value) {
        deque.insertLast(value);
        return true;
    }

    @Override
    public E remove() {
        return deque.removeFirst();
    }

    @Override
    public E peekFront() {
        return deque.getFirst();
    }

    @Override
    public int size() {
        return deque.size();
    }

    @Override
    public boolean isEmpty() {
        return deque.isEmpty();
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public void display() {
        deque.displayFromHead();
    }

    @Override
    public boolean insertLeft(E value) {
        deque.insertFirst(value);
        return true;
    }

    @Override
    public boolean insertRight(E value) {
        deque.insertLast(value);
        return true;
    }

    @Override
    public E removeLeft() {
        return deque.removeFirst();
    }

    @Override
    public E removeRight() {
        return deque.removeLast();
    }
}
