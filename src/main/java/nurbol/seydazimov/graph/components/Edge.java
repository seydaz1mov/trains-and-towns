package nurbol.seydazimov.graph.components;

public class Edge {

    public Vertex to;
    public int distance;

    public Edge(Vertex to, int distance) {
        this.to = to;
        this.distance = distance;
    }
}
