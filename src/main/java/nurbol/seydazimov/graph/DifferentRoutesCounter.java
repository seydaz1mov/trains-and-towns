package nurbol.seydazimov.graph;

import java.util.Arrays;

public class DifferentRoutesCounter {

    final Graph graph;
    final int[][][] count;

    public DifferentRoutesCounter(final Graph graph) {
        this.graph = graph;
        count = new int[5][31][30];
    }

    public void compute(final char appointedTown) {
        fillWithMinusOne(count);

        count[graph.getTownNumber(appointedTown)][0][0] = 1;

        for (int stoppage = 1; stoppage <= 30; stoppage++) {
            for (int distance = 1; distance < 30; distance++) {
                for (int town = 0; town < 5; ++town) {
                    Vertex vertex = graph.vertices[town];
                    for (Edge incomingEdge : vertex.incomingEdges) {
                        int beforeDistance = distance - incomingEdge.distance;

                        if (beforeDistance >= 0) {
                            int fromTown = graph.getTownNumber(incomingEdge.to.townName);
                            if (count[fromTown][stoppage - 1][beforeDistance] != -1) {
                                if (count[town][stoppage][distance] == -1)
                                    count[town][stoppage][distance] = 0;
                                count[town][stoppage][distance] += count[fromTown][stoppage - 1][beforeDistance];
                            }
                        }
                    }
                }
            }
        }

        int differentRoutesCount = 0;

        for (int stoppage = 0; stoppage <= 30; stoppage++) {
            for (int distance = 1; distance < 30; distance++) {
                if (count[graph.getTownNumber(appointedTown)][stoppage][distance] != -1)
                    differentRoutesCount += count[graph.getTownNumber(appointedTown)][stoppage][distance];
            }
        }

        System.out.println("Output #10: " + differentRoutesCount);
    }

    private void fillWithMinusOne(int[][][] count) {
        for (int i = 0; i < count.length; ++i)
            for (int j = 0; j < count[i].length; ++j)
                Arrays.fill(count[i][j], -1);
    }
}
