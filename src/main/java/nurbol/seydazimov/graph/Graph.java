package nurbol.seydazimov.graph;

import nurbol.seydazimov.graph.components.Edge;
import nurbol.seydazimov.graph.components.Vertex;
import nurbol.seydazimov.input.InputEdge;

import java.util.List;

public class Graph {

    Vertex[] vertices;

    public void buildGraph(List<InputEdge> edges) {
        vertices = new Vertex[5];
        for (char town = 'A'; town <= 'E'; ++town)
            vertices[getTownNumber(town)] = new Vertex(town);

        for (InputEdge inputEdge : edges) {
            int fromTownNumber = getTownNumber(inputEdge.fromTown);
            int toTownNumber = getTownNumber(inputEdge.toTown);
            Vertex fromVertex = vertices[fromTownNumber];
            Vertex toVertex = vertices[toTownNumber];
            fromVertex.outgoingEdges.add(
                    new Edge(toVertex, inputEdge.distance)
            );

            toVertex.incomingEdges.add(
                    new Edge(fromVertex, inputEdge.distance)
            );
        }
    }

    int getTownNumber(char town) {
        return town - 'A';
    }
}
