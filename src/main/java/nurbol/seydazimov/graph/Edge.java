package nurbol.seydazimov.graph;

class Edge {
    public Vertex to;
    public int distance;

    Edge(Vertex to, int distance) {
        this.to = to;
        this.distance = distance;
    }
}
