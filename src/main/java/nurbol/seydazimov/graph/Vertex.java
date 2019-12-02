package nurbol.seydazimov.graph;

import java.util.ArrayList;
import java.util.List;

class Vertex {
    public char townName;
    public List<Edge> outgoingEdges;
    public List<Edge> incomingEdges;

    Vertex(char townName) {
        this.townName = townName;
        outgoingEdges = new ArrayList<>();
        incomingEdges = new ArrayList<>();
    }
}
