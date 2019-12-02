package nurbol.seydazimov;

import nurbol.seydazimov.exceptions.InvalidRouteException;
import nurbol.seydazimov.graph.*;
import nurbol.seydazimov.input.InputEdge;
import nurbol.seydazimov.util.InputFileReader;
import nurbol.seydazimov.util.Parser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class TrainsAndTownsApp {
    public static void main(String[] args) {
        try {
            InputFileReader inputFileReader = new InputFileReader();
            try {
                String inputGraph = inputFileReader.readFile();

                Parser parser = new Parser();
                try {
                    List<InputEdge> inputEdgeList = parser.parse(inputGraph);

                    Graph graph = new Graph();

                    graph.buildGraph(inputEdgeList);

                    showPathDistance(1, "A-B-C", graph);
                    showPathDistance(2, "A-D", graph);
                    showPathDistance(3, "A-D-C", graph);
                    showPathDistance(4, "A-E-B-C-D", graph);
                    showPathDistance(5, "A-E-D", graph);

                    TripsWithMaximumThreeStoppageCounter tripsWithMaximumThreeStoppageCounter =
                            new TripsWithMaximumThreeStoppageCounter(graph);

                    tripsWithMaximumThreeStoppageCounter.compute('C');

                    TripsWithFourStoppageCounter tripsWithFourStoppageCounter =
                            new TripsWithFourStoppageCounter(graph);

                    tripsWithFourStoppageCounter.compute('A', 'C');

                    ShortestDistance shortestDistance = new ShortestDistance(graph);

                    showDistance(shortestDistance.compute('A', 'C'), 8);
                    showDistance(shortestDistance.compute('B', 'B'), 9);

                    DifferentRoutesCounter differentRoutesCounter = new DifferentRoutesCounter(graph);
                    differentRoutesCounter.compute('C');
                }   catch (InvalidRouteException exception) {
                    exception.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static void showPathDistance(int outputNumber, String route, Graph graph) {
        int distance = graph.distanceOfRoute(route);
        showDistance(distance, outputNumber);
    }

    private static void showDistance(int distance, int outputNumber) {
        System.out.print("Output #" + outputNumber + ": ");
        System.out.println(distance == -1 ? "NO SUCH ROUTE" : distance);
    }
}
