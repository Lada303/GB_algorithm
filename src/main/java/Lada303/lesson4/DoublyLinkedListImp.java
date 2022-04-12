package Lada303.lesson4;

import java.util.Iterator;
import java.util.ListIterator;

public class DoublyLinkedListImp<E> implements DoublyLinkedList<E>, Iterable<E>{

    protected Node<E> first;
    protected Node<E> last;
    protected int size;

    @Override
    public void insertFirst(E value) {
        Node<E> newNode = new Node<>(value, first, null);
        if (size == 0) {
            first = newNode;
            last = first;
        } else {
            first.previous = newNode;
            first = newNode;
        }
        size++;
    }

    @Override
    public void insertLast(E value) {
        if (size == 0) {
            insertFirst(value);
            return;
        }
        last.next = new Node<>(value, null, last);
        last = last.next;
        size++;
    }

    @Override
    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Node<E> removedNode = first;
        if (size == 1) {
            first = null;
        } else {
            first = first.next;
            first.previous = null;
        }
        removedNode.next = null;
        removedNode.previous = null;
        size--;
        return removedNode.value;
    }

    @Override
    public E removeLast() {
        if (isEmpty()) {
            return null;
        }
        Node<E> removedNode = last;
        last = last.previous;
        last.next = null;
        removedNode.next = null;
        removedNode.previous = null;
        size--;
        return removedNode.value;
    }


    @Override
    public E getFirst() {
        if (first == null) {
            return null;
        }
        return first.value;
    }

    @Override
    public E getLast() {
        if (first == null) {
            return null;
        }
        return last.value;
    }

        @Override
    public boolean contains(E value) {
        Node<E> current = first;
        while (current != null) {
            if (current.value.equals(value)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public boolean insert(int index, E value) {
        if (index > size) {
            return false;
        }
        if (index == 0) {
            insertFirst(value);
            return true;
        }
        if (index == size) {
            insertLast(value);
            return  true;
        }

        Node<E> current;
        if (index <= size / 2) {
            current = first;
            for (int i = 1; i < index; i++) {
                current = current.next;
            }
        } else {
            current = last;
            for (int i = size - 1; i >= index ; i--) {
                current = last.previous;
            }
        }
        Node<E> newNode = new Node<>(value, current.next, current);
        current.next.previous = newNode;
        current.next = newNode;
        size++;
        return true;
    }

    @Override
    public boolean remove(E value) {
        Node<E> prev = null;
        Node<E> current = first;

        while (current != null) {
            if (current.value.equals(value)) {
                break;
            }
            prev = current;
            current = current.next;
        }
        if (current == null) {
            return false;
        }
        if (current == first) {
            removeFirst();
            return true;
        }

        if (current == last) {
            removeLast();
            return true;
        }
        prev.next = current.next;
        current.next.previous = prev;
        current.next = null;
        current.previous = null;
        size--;
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void displayFromHead() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> current = first;
        while (current != null) {
            sb.append(current.value);
            if (current.next != null) {
                sb.append(" <-> ");
            }
            current = current.next;
        }
        return sb.append("]").toString();
    }

    @Override
    public void displayFromTail() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> current = last;
        while (current != null) {
            sb.append(current.value);
            if (current.previous != null) {
                sb.append(" <-> ");
            }
            current = current.previous;
        }
        sb.append("]");
        System.out.println(sb);
    }

    @Override
    public ListIterator<E> iterator() {
        return new ListIterator<E>() {
            Node<E> current = null;
            int index = 0;

            @Override
            public boolean hasNext() {
                if (current == null) {
                    return size != 0;
                }
                return current.next != null;
            }

            @Override
            public E next() {
                if (current == null) {
                    current = first;
                    index = 0;
                } else {
                    current = current.next;
                    index++;
                }
                return current.value;
            }

            @Override
            public boolean hasPrevious() {
                if (current == null) {
                    return false;
                }
                return current.previous != null;
            }

            @Override
            public E previous() {
                if (current == null) {
                    return null;
                }
                current = current.previous;
                index--;
                return current.value;
            }

            @Override
            public int nextIndex() {
                return index + 1;
            }

            @Override
            public int previousIndex() {
                return index - 1;
            }

            @Override
            public void remove() {
                Node<E> removedNode = current;
                current.previous.next = current.next;
                current.next.previous = current.previous;
                current = current.previous;
                removedNode.next = null;
                removedNode.previous = null;
                index--;
                size--;
            }

            @Override
            public void set(E e) {
                current.value = e;
            }

            @Override
            public void add(E e) {
                Node<E> newNode = new Node<>(e, current, current.previous);
                current.previous.next = newNode;
                current.previous = newNode;
                size++;
                index++;
            }

        };
    }

}
