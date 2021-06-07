package expose;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class GraphApp {
    public static void main(String[] args) throws IOException{
        FileReader fileReader = new FileReader("fileGraph.txt");
        BufferedReader bufferedRead = new BufferedReader(fileReader);
        GraphDirection<String> graphDi = new GraphDirection<>();
        int totalVertex = Integer.parseInt(bufferedRead.readLine());
        String[] vertices = bufferedRead.readLine().split(" ");

        for (String str : vertices) {
            graphDi.addVertex(str);
        }
        
        int totalEdge = Integer.parseInt(bufferedRead.readLine());
        
        for (int i = 0; i < totalEdge; i++) {
            String[] tempInfoEdge = bufferedRead.readLine().split(" ");
            graphDi.addEdge(tempInfoEdge[0], 
                    tempInfoEdge[1], 
                    Integer.parseInt(tempInfoEdge[2]));
        }

        for (int i = 0; i < totalVertex; i++) {
            String vertex = vertices[i];

            System.out.print( vertex + " : ");
            System.out.print("In-degree " + graphDi.totalInDegree(vertex));
            System.out.println("\tOut-degree " + graphDi.totalOutDegree(vertex));;
            List<List<String>> outEdges = graphDi.getOutEdge(vertex);
            System.out.print("Edges : ");

            for (List<String> list : outEdges) {
                System.out.print(list.get(0) + "(" 
                        + list.get(1) + ") ");
            }
            System.out.println();
        }

        System.out.println(graphDi.toString());

        System.out.println("Short path from 1 to : ");
        Integer[] weights = new DijsktraAlgorithm(graphDi).getShortPath(graphDi.indexOf("1"));
        for (int i = 0; i < totalVertex; i++) {
            System.out.println("\t" + vertices[i] + " : " + weights[i]);
        }
    }
}
