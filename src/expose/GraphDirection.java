package expose;

import java.util.ArrayList;
import java.util.List;

public class GraphDirection<T> {
    private List<List<Integer>> adjMatrix;
    private List<T> vertices;

    public GraphDirection() {
        adjMatrix = new ArrayList<>();
        vertices = new ArrayList<>();
    }

    public boolean addVertex(T v) {
        if (indexOf(v) <  0) {
            int n = adjMatrix.size();
            adjMatrix.add(new ArrayList<>());
            for (int i = 0; i <= n; i++) {
                adjMatrix.get(i).add(0);
            }

            for (int i = 0; i < n; i++) {
                adjMatrix.get(n).add(0);
            }

            vertices.add(v);
            return true;
        }

        return false;
    }

    public List<T> getVertex() {
        return vertices;
    }

    public int indexOf(T v) {
        return vertices.indexOf(v);
    }

    public boolean addEdge(T v1, T v2, int weight) {
        if (indexOf(v1) >= 0 && indexOf(v2) >= 0) {
            int iV1 = vertices.indexOf(v1);
            int iV2 = vertices.indexOf(v2);
            adjMatrix.get(iV1).set(iV2, weight);
            return true;
        }

        return false;
    }

    public boolean containsEdge(T v1, T v2) {
        if (indexOf(v1) >= 0 && indexOf(v2) >= 0) {
            int iV1 = vertices.indexOf(v1);
            int iV2 = vertices.indexOf(v2);
            return adjMatrix.get(iV1).get(iV2) != 0;
        }

        return false;
    }

    public int getWeight(T v1, T v2) {
        if (indexOf(v1) >= 0 && indexOf(v2) >= 0) {
            int iV1 = vertices.indexOf(v1);
            int iV2 = vertices.indexOf(v2);
            return adjMatrix.get(iV1).get(iV2);
        }

        return 0;
    }

    public int totalInDegree(T v) {
        if(indexOf(v) >= 0) {
            int n = 0;
            int iV = vertices.indexOf(v);
            for (int i = 0; i < vertices.size(); i++) {
                if (adjMatrix.get(i).get(iV) > 0) {
                    n++;
                }
            }

            return n;
        }

        return 0;
    }

    public int totalOutDegree(T v) {
        if(indexOf(v) >= 0) {
            int n = 0;
            int iV = vertices.indexOf(v);
            for (int i = 0; i < vertices.size(); i++) {
                if (adjMatrix.get(iV).get(i) > 0) {
                    n++;
                }
            }

            return n;
        }

        return 0;
    }

    public int totalVertex() {
        return vertices.size();
    }

    public int totalEdge() {
        int n = vertices.size();
        int totalEdge = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (adjMatrix.get(i).get(j) > 0) {
                    totalEdge++;
                }
            }
        }

        return totalEdge;
    }

    public List<List<T>> getOutEdge(T v) {
        if (indexOf(v) >= 0) {
            List<List<T>> result = new ArrayList<>();
            int iV = vertices.indexOf(v);
            for (int i = 0; i < vertices.size(); i++) {
                int weight = adjMatrix.get(iV).get(i);
                if (weight > 0) {
                    List<T> tmp = new ArrayList<>();
                    tmp.add(vertices.get(i));
                    tmp.add((T)Integer.toString(weight));
                    result.add(tmp);
                }
            }

            return result;
        }

        return null;
    }

    public List<T> getNeighbors(T v) {
        if (indexOf(v) >= 0) {
            List<T> neighbors = new ArrayList<>();
            int iV = vertices.indexOf(v);
            for (int i = 0; i < vertices.size(); i++) {
                T neighbor = vertices.get(i);
                int col = adjMatrix.get(iV).get(i);
                int row = adjMatrix.get(i).get(iV);
                if ((col > 0 || row > 0)
                    && (!neighbors.contains(neighbor))) {
                    neighbors.add(neighbor);
                }
            }

            return neighbors;
        }

        return null;
    }

    public int size() {
        return vertices.size();
    }

    public Integer[][] toArray () {
        int n = vertices.size();
        Integer[][] arry = new Integer[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n ; j++) {
                arry[j][i] = adjMatrix.get(j).get(i);
            }
        }

        return arry;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("  " 
                + vertices.toString() + "\n");

        for (int i = 0; i < adjMatrix.size(); i++) {
            str.append(vertices.get(i) + " " 
                    + adjMatrix.get(i).toString() + "\n");
        }

        return str.toString();
    }
}
