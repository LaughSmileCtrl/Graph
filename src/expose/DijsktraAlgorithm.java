package expose;

import java.util.Arrays;

public class DijsktraAlgorithm {

    private Integer[][] adjMatrx;

    private int size;

    public DijsktraAlgorithm(GraphDirection graphDi) {
        adjMatrx = graphDi.toArray();
        size = graphDi.size();
    }

    public int minIndex(int[] distance, boolean[] vertexVisited) {
        int min = Integer.MAX_VALUE;
        int iMin = -1;

        for (int i = 0; i < vertexVisited.length; i++) {
            if (!vertexVisited[i] && distance[i] < min) {
                iMin = i;
                min = distance[i];
            }
        }

        return iMin;
    }

    public int[] finding (int iV) {
        boolean[] vertexVisited = new boolean[size];
        int[] distance = new int[size];

        for (int i = 0; i < size; i++) {
            vertexVisited[i] = false;
            distance[i] = Integer.MAX_VALUE;
        }

        distance[iV] = 0;

        for (int i = 0; i < size-1; i++) {
            int n = minIndex(distance, vertexVisited);
            vertexVisited[n] = true;

            for (int j = 0; j < size; j++) {
                if (!vertexVisited[j] && distance[n] != Integer.MAX_VALUE && adjMatrx[n][j] != 0 && distance[n] + adjMatrx[n][j] < distance[j]) {
                    distance[j] = distance[n] + adjMatrx[n][j];
                }
            }

        }

        return distance;
    }
}
