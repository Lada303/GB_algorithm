package Lada303.lesson7;

import java.util.List;

public interface Graph {
    void addVertex(String label);
    boolean addEdge(String startLabel, String endLabel, int value);
    int getSize();
    void display();
    List<String> getListVertexWithMinDistance(String startLabel, String endLabel);

}
