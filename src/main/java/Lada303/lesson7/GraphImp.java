package Lada303.lesson7;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class GraphImp implements Graph{

    private final List<String> vertexList;
    private final int[][] adjMatrix;

    public GraphImp(int maxVertexCount) {
        this.vertexList = new ArrayList<>(maxVertexCount);
        this.adjMatrix = new int[maxVertexCount][maxVertexCount];
    }

    @Override
    public void addVertex(String label) {
        vertexList.add(label);
    }

    @Override
    public boolean addEdge(String startLabel, String endLabel, int value) {
        int indexStart = indexVertex(startLabel);
        int indexEnd = indexVertex(endLabel);

        if (indexStart == -1 || indexEnd == -1) {
            return false;
        }

        adjMatrix[indexStart][indexEnd] = value;
        adjMatrix[indexEnd][indexStart] = value;
        return true;
    }

    private int indexVertex(String label) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (label.equals(vertexList.get(i))) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int getSize() {
        return vertexList.size();
    }

    @Override
    public void display() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < vertexList.size(); i++) {
            sb.append(vertexList.get(i)).append(" [");
            for (int j = 0; j < adjMatrix.length; j++) {
                sb.append(adjMatrix[i][j]);
                if (j != adjMatrix.length - 1) {
                    sb.append(", ");
                }
            }
            sb.append("]\n");
        }
        return sb.toString();
    }

    @Override
    public List<String> getListVertexWithMinDistance(String startLabel, String endLabel) {
        List<String> list = new ArrayList<>();
        int minDistance = 100000;
        int indexStart = indexVertex(startLabel);
        int endStart = indexVertex(endLabel);
        if (indexStart == -1 || endStart == -1) {
            System.out.println("Wrong Vertex");
            return list;
        }

        Stack<String> stackVertex = new Stack<>();
        boolean[] inStack = new boolean[vertexList.size()];
        Stack<Integer> stackDistance = new Stack<>();
        boolean[][] isVisited = new boolean[vertexList.size()][vertexList.size()];

        stackVertex.add(startLabel);
        inStack[indexStart] = true;
        stackDistance.add(0);
        int currentDistance = 0;
        int indexCurrentVertex = indexStart;

        while (!stackVertex.isEmpty()) {
            for (int i = 0; i < vertexList.size(); i++) {
              //  System.out.println(vertexList.get(indexCurrentVertex) + " " + vertexList.get(i));
                if (adjMatrix[indexCurrentVertex][i] != 0 &&
                        !inStack[i] && !isVisited[indexCurrentVertex][i]) {
                    stackVertex.add(vertexList.get(i));
                    inStack[i] = true;
                    isVisited[indexCurrentVertex][i] = true;
                    stackDistance.add(adjMatrix[indexCurrentVertex][i]);
                    currentDistance += adjMatrix[indexCurrentVertex][i];
                    indexCurrentVertex = i;
//                    System.out.println(stackVertex + " " + currentDistance);

                    //Если зашел в конечный пунк или общая дистанция уже больше минимальной - стоп и откат
                    if (vertexList.get(indexCurrentVertex).equals(endLabel) || currentDistance > minDistance) {
//                        System.out.println(stackVertex + " " + currentDistance + " ? " + minDistance);
                        if (currentDistance < minDistance) {
                            minDistance = currentDistance;
                            list.clear();
                            list.addAll(stackVertex);
//                            System.out.println(stackVertex + " " + currentDistance);
                        }
                        currentDistance -= stackDistance.pop();
                        inStack[indexCurrentVertex] = false;
                        stackVertex.pop();
                        indexCurrentVertex = indexVertex(stackVertex.peek());
                        continue;
                    }
                    break;
                }

                //Если нет ни одной непросмотренной вершины - откат
                if (i == vertexList.size() - 1) {
                    for (int j = 0; j < vertexList.size(); j++) {
                        isVisited[indexCurrentVertex][j] = false;
                    }
                    currentDistance -= stackDistance.pop();
                    inStack[indexCurrentVertex] = false;
                    stackVertex.pop();
                    if (!stackVertex.isEmpty()) {
                    indexCurrentVertex = indexVertex(stackVertex.peek());
                    }
//                    System.out.println(" last i - " + stackVertex + " " + currentDistance);
                }
            }
        }
        System.out.println(list + " -> " + minDistance);
        return list;
    }

}
