package nurbol.seydazimov.graph;

import nurbol.seydazimov.graph.components.Edge;
import nurbol.seydazimov.graph.components.Vertex;
import nurbol.seydazimov.graph.helper.DistancePrinter;

public class DistanceOfRoute {

    final Graph graph;
    final DistancePrinter printer;

    public DistanceOfRoute(final Graph graph, final DistancePrinter printer) {
        this.graph = graph;
        this.printer = printer;
    }

    public void show(int outputNumber, String route) {
        int distance = distanceOfRoute(route);

        printer.print(distance, outputNumber);
    }

    private void showDistance(int distance, int outputNumber) {
        System.out.print("Output #" + outputNumber + ": ");
        System.out.println(distance == -1 ? "NO SUCH ROUTE" : distance);
    }

    private int distanceOfRoute(String route) {
        char[] towns = route.replace("-","").toCharArray();

        if (towns.length < 2) return 0;

        int distance = 0;

        for (int i = 0; i < towns.length - 1; ++i) {
            char fromTown = towns[i];
            char toTown = towns[i + 1];

            Vertex vertex = graph.vertices[graph.getTownNumber(fromTown)];

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
}
