package Lada303.lesson7;

import java.util.List;

public class lesson7 {
    public static final String MOSCOW = "Москва";
    public static final String TULA = "Тула";
    public static final String RYAZAN = "Рязань";
    public static final String KALUGA = "Калуга";
    public static final String YELETS = "Елец";
    public static final String LIPETSK = "Липецк";
    public static final String OREL = "Орёл";
    public static final String TAMBOV = "Тамбов";
    public static final String KURSK = "Курск";
    public static final String VORONEZH = "Воронеж";



    public static void main(String[] args) {
        GraphImp citiesDistances = new GraphImp(10);
        citiesDistances.addVertex(MOSCOW);
        citiesDistances.addVertex(TULA);
        citiesDistances.addVertex(RYAZAN);
        citiesDistances.addVertex(KALUGA);
        citiesDistances.addVertex(YELETS);
        citiesDistances.addVertex(LIPETSK);
        citiesDistances.addVertex(OREL);
        citiesDistances.addVertex(TAMBOV);
        citiesDistances.addVertex(KURSK);
        citiesDistances.addVertex(VORONEZH);

        citiesDistances.addEdge(MOSCOW, TULA, 184);
        citiesDistances.addEdge(MOSCOW, RYAZAN, 197);
        citiesDistances.addEdge(MOSCOW, KALUGA, 210);
        citiesDistances.addEdge(TULA, RYAZAN, 185);
        citiesDistances.addEdge(TULA, KALUGA, 107);
        citiesDistances.addEdge(TULA, OREL, 187);
        citiesDistances.addEdge(TULA, YELETS, 211);
        citiesDistances.addEdge(RYAZAN, LIPETSK, 266);
        citiesDistances.addEdge(RYAZAN, TAMBOV, 288);
        citiesDistances.addEdge(KALUGA, OREL, 210);
        citiesDistances.addEdge(OREL, YELETS, 213);
        citiesDistances.addEdge(OREL, KURSK, 164);
        citiesDistances.addEdge(YELETS, LIPETSK, 85);
        citiesDistances.addEdge(YELETS, VORONEZH, 135);
        citiesDistances.addEdge(LIPETSK, VORONEZH, 126);
        citiesDistances.addEdge(TAMBOV, VORONEZH, 221);
        citiesDistances.addEdge(KURSK, VORONEZH, 225);

        citiesDistances.display();

        long startTime = System.currentTimeMillis();
        List<String> list = citiesDistances.getListVertexWithMinDistance(MOSCOW, VORONEZH);
        long stopTime = System.currentTimeMillis();
        System.out.println((stopTime - startTime) + " ms");
        System.out.println(list);


    }
}
