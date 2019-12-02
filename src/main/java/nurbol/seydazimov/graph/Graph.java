package nurbol.seydazimov.graph;

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

    public int distanceOfRoute(String route) {
        char[] towns = route.replace("-","").toCharArray();

        if (towns.length < 2) return 0;

        int distance = 0;

        for (int i = 0; i < towns.length - 1; ++i) {
            char fromTown = towns[i];
            char toTown = towns[i + 1];

            Vertex vertex = vertices[getTownNumber(fromTown)];

            boolean toTownFound = false;

            for (Edge outgoingEdge: vertex.outgoingEdges) {
                if (outgoingEdge.to.townName == toTown) {
                    distance += outgoingEdge.distance;
                    toTownFound = true;
                }
            }

            if (!toTownFound)
                return -1;
        }

        return distance;
    }

    int getTownNumber(char town) {
        return town - 'A';
    }
}
