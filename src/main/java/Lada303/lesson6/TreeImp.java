package Lada303.lesson6;

import java.util.*;

public class TreeImp<E extends Comparable<E>>  implements Tree<E>{

    private Node<E> root;
    private int size;
    private int level;
    private final int maxLevel;
    private boolean isStopIfMaxLevel;

    public TreeImp() {
        this.maxLevel = -1;
        this.isStopIfMaxLevel = false;
    }

    public TreeImp(int maxLevel) {
        this.maxLevel = maxLevel;
        this.isStopIfMaxLevel = false;
    }

    public boolean isStopIfMaxLevel() {
        return isStopIfMaxLevel;
    }

    @Override
    public boolean contains(E value) {
        Node<E> newNode = doFind(value);
        return newNode != null && newNode.getValue() != null;
    }

    @Override
    public boolean add(E value) {
        if (isStopIfMaxLevel) {
            return false;
        }
        
        Node<E> newNode = doFind(value);
        if (newNode == null) {
            root = new Node<>(value);
            level = 1;
            size++;
            return true;
        }
        
        if (newNode.getValue() != null) {
            newNode.incrementRepeatCount();
            return false;
        }
        
        if (maxLevel > 0 && level >= maxLevel) {
            isStopIfMaxLevel = true;
            return false;
        }
        
        newNode.setValue(value);
        if (newNode.getParent().isLeftChild(value)) {
            newNode.getParent().setLeftChild(newNode);
        } else {
            newNode.getParent().setRightChild(newNode);
        }
        size++;
        return true;
    }

    @Override
    public boolean remove(E value) {
        Node<E> removed = doFind(value);
        if (removed == null || removed.getValue() == null) {
            return false;
        }

        if (removed.isLeaf()) {
            removeNode(removed, null);
        } else if (removed.hasOnlyOneChild()) {
            removeNodeWithOneChild(removed);
        } else {
            removeNodeWithAllChildren(removed);
        }
        size--;
        return true;
    }

    private Node<E> doFind(E value) {
        Node<E> current = root;
        level = 0;

        while (current != null && current.getValue() != null) {
            level++;
            if (current.getValue().equals(value)) {
                break;
            }

            if (current.isLeftChild(value)) {
                if (current.getLeftChild() != null) {
                    current = current.getLeftChild();
                } else {
                    current = new Node<>(current);
                }
            } else {
                if (current.getRightChild() != null) {
                    current = current.getRightChild();
                } else {
                    current = new Node<>(current);
                }
            }
        }
        return current;
    }

    private void removeNode(Node<E> removed, Node<E> replacement) {
        if (removed == root) {
            root = replacement;
        } else if (removed.getParent().isLeftChild(removed.getValue())) {
            removed.getParent().setLeftChild(replacement);
        } else {
            removed.getParent().setRightChild(replacement);
        }
        removed.setParent(null);
        removed.setLeftChild(null);
        removed.setRightChild(null);
    }

    private void removeNodeWithOneChild(Node<E> removed) {
        Node<E> child = removed.getLeftChild() != null
                ? removed.getLeftChild()
                : removed.getRightChild();
        child.setParent(removed.getParent());

        removeNode(removed, child);
    }

    private void removeNodeWithAllChildren(Node<E> removed) {
        Node<E> successor = getSuccessor(removed);
        successor.setParent(removed.getParent());
        removed.getLeftChild().setParent(successor);
        removed.getRightChild().setParent(successor);

        removeNode(removed, successor);
    }

    private Node<E> getSuccessor(Node<E> removed) {
        Node<E> successor = removed;
        Node<E> parent = null;
        Node<E> current = removed.getRightChild();

        while (current != null) {
            parent = successor;
            successor = current;
            current = current.getLeftChild();
        }

        if (successor != removed.getRightChild() && parent!= null) {
            parent.setLeftChild(successor.getRightChild());
            successor.setRightChild(removed.getRightChild());
        }
        successor.setLeftChild(removed.getLeftChild());

        return successor;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void display() {
        Stack<Node<E>> globalStack = new Stack<>();
        globalStack.push(root);
        int nBlanks = 64;
        boolean isRowEmpty = false;
        System.out.println("................................................................");
        while (!isRowEmpty) {
            Stack<Node<E>> localStack = new Stack<>();
            isRowEmpty = true;
            for (int i = 0; i < nBlanks; i++) {
                System.out.print(" ");
            }

            while (!globalStack.isEmpty()) {
                Node<E> tempNode = globalStack.pop();
                if (tempNode != null) {
                    System.out.print(tempNode.getValue());
                    localStack.push(tempNode.getLeftChild());
                    localStack.push(tempNode.getRightChild());
                    if (tempNode.getLeftChild() != null || tempNode.getRightChild() != null) {
                        isRowEmpty = false;
                    }
                } else {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < nBlanks * 2 - 2; j++) {
                    System.out.print(" ");
                }
            }
            System.out.println();

            while (!localStack.isEmpty()) {
                globalStack.push(localStack.pop());
            }
            nBlanks /= 2;
        }
        System.out.println("................................................................");
    }

    @Override
    public void traverse(TraversMode mode) {
        switch (mode) {
            case PRE_ORDER -> preOrder(root); //прямой обход
            case IN_ORDER -> inOrder(root); //центрированный обход
            case POST_ORDER -> postOrder(root); //обратный обход
        }
        System.out.println();
    }

    private void preOrder(Node<E> current) {
        if (current == null) {
            return;
        }

        System.out.print(current.getValue() + " ");
        preOrder(current.getLeftChild());
        preOrder(current.getRightChild());
    }

    private void inOrder(Node<E> current) {
        if (current == null) {
            return;
        }

        inOrder(current.getLeftChild());
        System.out.print(current.getValue() + " ");
        inOrder(current.getRightChild());
    }

    private void postOrder(Node<E> current) {
        if (current == null) {
            return;
        }

        postOrder(current.getLeftChild());
        postOrder(current.getRightChild());
        System.out.print(current.getValue() + " ");
    }



    public boolean isBalancedTree() {
        Queue<Node<E>> queueNodes = new ArrayDeque<>();
        queueNodes.add(root);
        int level = 1;
        int countNodesInCurrentLevel = 1;
        int countAllNodes = 1;
        System.out.println("true level 1");
        while(!queueNodes.isEmpty()){
            int countNodesInNextLevel = 0;
            for (int i = 0; i < countNodesInCurrentLevel; i++) {
                Node<E> node = queueNodes.remove();

                if (node.getLeftChild() != null) {
                    queueNodes.add(node.getLeftChild());
                    countNodesInNextLevel++;
                    countAllNodes++;
                }
                if (node.getRightChild() != null) {
                    queueNodes.add(node.getRightChild());
                    countNodesInNextLevel++;
                    countAllNodes++;
                }

                if (size == countAllNodes) {
                    System.out.println("last level " + (level + 1));
                    return true;
                }
            }

            if (countNodesInNextLevel == Math.pow(2, level)) {
                System.out.println("true level " + (level + 1));
                level++;
                countNodesInCurrentLevel = countNodesInNextLevel;
                continue;
            }
            System.out.println("false level " + (level + 1));
            return false;
        }
        return true;
    }
}
