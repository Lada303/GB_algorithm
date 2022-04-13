package Lada303.lesson4;

import Lada303.lesson3.Queue;

public interface Deque<E> extends Queue<E> {

        boolean insertLeft(E value);
        boolean insertRight(E value);

        E removeLeft();
        E removeRight();
}
