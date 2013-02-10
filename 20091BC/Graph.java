
import static java.lang.System.out;
import java.util.*;

public class Graph {
    EdgeNode[] edges;
    int[] degree;
    int nvertices;
    int nedges;
    boolean directed;

    public Graph(int maxEdges, boolean directed) {

        nvertices = 0;
        nedges = 0;
        directed = directed;

        edges = new EdgeNode[maxEdges+1];
        degree = new int[maxEdges+1];
    }

    public static class EdgeNode {
        int y;
        int weight;
        EdgeNode next;
    }

    public void insertEdge(int x, int y, boolean directed) {
        EdgeNode p = new EdgeNode();
        p.y = y;
        p.next = edges[x];
        edges[x] = p;
        degree[x]++;
        // If directed, add the other edge and only increment the edge count once we're done
        if (!directed)
            insertEdge(y, x, true);
        else
            nedges++;
    }

    public static Graph readGraph(boolean directed) {
        int i;
        int m;
        int x, y;

        Graph g = new Graph(1000, directed);
        Scanner scanner = new Scanner(System.in);
        g.nvertices = scanner.nextInt();
        m = scanner.nextInt();

        for (i=1; i<=m; i++) {
            x = scanner.nextInt();
            y = scanner.nextInt();
            g.insertEdge(x, y, directed);
        }
        return g;
    }

    public void printGraph() {
        for(int i=1; i <= nvertices; i++) {
            out.format("%d:", i);
            EdgeNode p = edges[i];
            while (p != null) {
                out.format(" %d", p.y);
                p = p.next;
            }
            out.println("");
        }
    }

    public static void main(String[] args) {
        Graph g = readGraph(false);
        g.printGraph();
    }
}

