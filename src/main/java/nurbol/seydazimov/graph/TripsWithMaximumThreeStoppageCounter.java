package nurbol.seydazimov.graph;

import nurbol.seydazimov.graph.components.Edge;
import nurbol.seydazimov.graph.components.Vertex;

import java.util.Arrays;

public class TripsWithMaximumThreeStoppageCounter {

    final Graph graph;

    int[][] count;

    public TripsWithMaximumThreeStoppageCounter(Graph graph) {
        this.graph = graph;
        this.count = new int[5][4];
    }

    public void compute(final char appointedTown) {
        fillWithMinusOne(count);

        count[graph.getTownNumber(appointedTown)][0] = 1;

        for (int stoppage = 1; stoppage <= 3; stoppage++) {
            for (int town = 0; town < 5; ++town) {
                Vertex vertex = graph.vertices[town];
                for (Edge incomingEdge : vertex.incomingEdges) {
                    int fromTown = graph.getTownNumber(incomingEdge.to.townName);
                    if (count[fromTown][stoppage - 1] != -1) {
                        if (count[town][stoppage] == -1)
                            count[town][stoppage] = 0;
                        count[town][stoppage] += count[fromTown][stoppage - 1];
                    }
                }
            }
        }

        int total = 0;

        for (int stoppage = 1; stoppage <= 3; stoppage++) {
            if (count[graph.getTownNumber(appointedTown)][stoppage] != -1)
            total += count[graph.getTownNumber(appointedTown)][stoppage];
        }

        System.out.println("Output #6: " + total);
    }

    private void fillWithMinusOne(int[][] count) {
        for (int i = 0; i < count.length; ++i)
            Arrays.fill(count[i], -1);
    }
}
