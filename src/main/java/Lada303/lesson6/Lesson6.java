package Lada303.lesson6;

import java.util.Random;

public class Lesson6 {

    private static final int COUNT_TREE = 20;
    private static final int MAX_LEVEL = 4;

    public static void main(String[] args) {

        //проверка работы дерева
        Tree<Integer> tree = new TreeImp<>();
        tree.add(60);
        tree.add(50);
        tree.add(66);
        tree.add(40);
        tree.add(55);
        tree.add(70);
        tree.add(31);
        tree.add(45);
        tree.add(42);
        tree.add(43);
        tree.add(69);
        tree.add(67);
        tree.add(68);
        tree.add(81);

        tree.display();
        System.out.println(tree.contains(81));
        System.out.println(tree.contains(1));
        tree.traverse(Tree.TraversMode.PRE_ORDER);
        tree.traverse(Tree.TraversMode.IN_ORDER);
        tree.traverse(Tree.TraversMode.POST_ORDER);
        tree.remove(81);
        tree.display();
        tree.remove(45);
        tree.display();
        tree.remove(60);
        tree.display();


        // ДЗ:
        //генерация деревьев
        Random random = new Random();

        TreeImp<Integer>[] arrTree = new TreeImp[COUNT_TREE];
        int countBalancedTree = 0;
        for (int i = 0; i < arrTree.length; i++) {

            arrTree[i] = new TreeImp<>(MAX_LEVEL);

            while(!arrTree[i].isStopIfMaxLevel()) {
                arrTree[i].add(random.nextInt(50) - 25);
            }
            arrTree[i].display();
            // проверка на сбалансированность
            // делаем прямой обход в ширину, если очередной уровень заполнен не полностью, а узлы в дереве еще остались
            // значит дерево несбалансировано
            boolean isBalancedTree = arrTree[i].isBalancedTree();
            System.out.println("Balanced Tree -> " + isBalancedTree);
            if (isBalancedTree) {
                countBalancedTree++;
            }
        }
        System.out.println();
        System.out.println("All balancedTree = " + countBalancedTree);
    }

}
