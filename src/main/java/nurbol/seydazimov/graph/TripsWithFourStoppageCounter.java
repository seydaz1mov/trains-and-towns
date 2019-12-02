package nurbol.seydazimov.graph;

import java.util.Arrays;

public class TripsWithFourStoppageCounter {
    final Graph graph;

    int[][] count;

    public TripsWithFourStoppageCounter(Graph graph) {
        this.graph = graph;
        this.count = new int[5][5];
    }

    public void compute(final char startTown, final char endTown) {
        fillWithMinusOne(count);

        count[graph.getTownNumber(startTown)][0] = 1;

        for (int stoppage = 1; stoppage <= 4; stoppage++) {
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

        if (count[graph.getTownNumber(endTown)][4] != -1)
            total += count[graph.getTownNumber(endTown)][4];

        System.out.println("Output #7: " + total);
    }

    private void fillWithMinusOne(int[][] count) {
        for (int i = 0; i < count.length; ++i)
            Arrays.fill(count[i], -1);
    }
}
