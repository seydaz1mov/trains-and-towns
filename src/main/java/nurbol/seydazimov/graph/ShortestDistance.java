package nurbol.seydazimov.graph;

import java.util.Arrays;

public class ShortestDistance {

    final Graph graph;

    public ShortestDistance(final Graph graph) {
        this.graph = graph;
    }

    public int compute(char startTown, char endTown) {
        boolean[] used = new boolean[5];
        int[] distance = new int[5];

        dijkstra(startTown, used, distance);

        if (startTown == endTown) {
            int shortestDistance = -1;
            Vertex vertex = graph.vertices[graph.getTownNumber(startTown)];
            for (Edge edge: vertex.incomingEdges) {
                int fromTownNumber = graph.getTownNumber(edge.to.townName);

                if (distance[fromTownNumber] != -1) {
                    if (shortestDistance == -1)
                        shortestDistance = Integer.MAX_VALUE;

                    shortestDistance = Math.min(shortestDistance, distance[fromTownNumber] + edge.distance);
                }
            }
            return shortestDistance;
        }

        return distance[graph.getTownNumber(endTown)];
    }

    private void dijkstra(char town, boolean[] used, int[] distance) {
        Arrays.fill(distance, -1);

        distance[graph.getTownNumber(town)] = 0;

        while (true) {
            int smallestDistance = Integer.MAX_VALUE;
            int currentVertexNumber = -1;

            for (int i = 0; i < 5; ++i) {
                if (distance[i] != -1 && !used[i] && distance[i] < smallestDistance) {
                    smallestDistance = distance[i];
                    currentVertexNumber = i;
                }
            }
            if (currentVertexNumber == -1) break;

            used[currentVertexNumber] = true;

            Vertex vertex = graph.vertices[currentVertexNumber];

            for (Edge edge: vertex.outgoingEdges) {
                int toTown = graph.getTownNumber(edge.to.townName);

                if (distance[toTown] == -1)
                    distance[toTown] = Integer.MAX_VALUE;

                distance[toTown] = Math.min(distance[toTown], distance[currentVertexNumber] + edge.distance);
            }
        }
    }
}
