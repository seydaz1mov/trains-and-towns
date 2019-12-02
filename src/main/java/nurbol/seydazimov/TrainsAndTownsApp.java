package nurbol.seydazimov;

import nurbol.seydazimov.exceptions.InvalidRouteException;
import nurbol.seydazimov.graph.*;
import nurbol.seydazimov.graph.helper.DistancePrinter;
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
                    DistancePrinter printer = new DistancePrinter();

                    graph.buildGraph(inputEdgeList);

                    DistanceOfRoute distanceOfRoute = new DistanceOfRoute(graph, printer);

                    distanceOfRoute.show(1, "A-B-C");
                    distanceOfRoute.show(2, "A-D");
                    distanceOfRoute.show(3, "A-D-C");
                    distanceOfRoute.show(4, "A-E-B-C-D");
                    distanceOfRoute.show(5, "A-E-D");

                    TripsWithMaximumThreeStoppageCounter tripsWithMaximumThreeStoppageCounter =
                            new TripsWithMaximumThreeStoppageCounter(graph);

                    tripsWithMaximumThreeStoppageCounter.compute('C');

                    TripsWithFourStoppageCounter tripsWithFourStoppageCounter =
                            new TripsWithFourStoppageCounter(graph);

                    tripsWithFourStoppageCounter.compute('A', 'C');

                    ShortestDistance shortestDistance = new ShortestDistance(graph, printer);

                    shortestDistance.show(8, 'A', 'C');
                    shortestDistance.show(9, 'B', 'B');

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
}
