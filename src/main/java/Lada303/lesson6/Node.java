package Lada303.lesson6;

public class Node <T extends Comparable<T>>{

    private T value;
    private Node<T> parent;
    private Node<T> leftChild;
    private Node<T> rightChild;
    private int repeatCount;

    public Node(){}

    public Node(T value) {
        this.value = value;
        this.repeatCount = 0;
    }

    public Node(Node<T> parent) {
        this.parent = parent;
        this.repeatCount = 0;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Node<T> getParent() {
        return parent;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    public Node<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node<T> leftChild) {
        this.leftChild = leftChild;
    }

    public Node<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node<T> rightChild) {
        this.rightChild = rightChild;
    }

    public int getRepeatCount() {
        return repeatCount;
    }

    public void incrementRepeatCount() {
        this.repeatCount++;
    }

    public boolean isLeftChild(T value) {
        return getValue().compareTo(value) > 0;
    }

    public boolean isLeaf() {
        return leftChild == null && rightChild == null;
    }

    public boolean hasOnlyOneChild() {
        return rightChild != null ^ leftChild != null;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", parent=" + (parent == null ? "null" : parent.value) +
                ", leftChild=" + (leftChild == null ? "null" : leftChild.value) +
                ", rightChild=" + (rightChild == null ? "null" : rightChild.value) +
                ", repeatCount=" + repeatCount +
                '}';
    }
}
