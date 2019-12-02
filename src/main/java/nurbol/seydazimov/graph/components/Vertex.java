package nurbol.seydazimov.graph.components;

import java.util.ArrayList;
import java.util.List;

public class Vertex {

    public char townName;
    public List<Edge> outgoingEdges;
    public List<Edge> incomingEdges;

    public Vertex(char townName) {
        this.townName = townName;
        outgoingEdges = new ArrayList<>();
        incomingEdges = new ArrayList<>();
    }
}
