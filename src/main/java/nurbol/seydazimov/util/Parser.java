package nurbol.seydazimov.util;

import nurbol.seydazimov.exceptions.InvalidRouteException;
import nurbol.seydazimov.input.InputEdge;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    public List<InputEdge> parse(final String inputGraph) throws InvalidRouteException {
        List<InputEdge> inputEdges = new ArrayList<>();
        String[] routes = inputGraph.split(",");

        for(String route: routes) {
           route = route.trim();
           if (!isVaidRoute(route))
               throw new InvalidRouteException("Incorrect route: " + route);
            InputEdge inputEdge = parseInputEdge(route);

            inputEdges.add(inputEdge);
        }

        return inputEdges;
    }

    private InputEdge parseInputEdge(String route) {
        char fromTown = route.charAt(0);
        char toTown = route.charAt(1);
        String distanceInString = route.substring(2);
        return new InputEdge(
                fromTown,
                toTown,
                Integer.parseInt(distanceInString)
        );
    }

    private boolean isVaidRoute(String route) {
        if (route.length() < 3)
            return false;
        if (!isValidTownName(route.charAt(0)))
            return false;
        if (!isValidTownName(route.charAt(1)))
            return false;
        for (int i = 2; i < route.length(); ++i)
            if (!Character.isDigit(route.charAt(i)))
                return false;

        return true;
    }

    private boolean isValidTownName(char townName) {
        return 'A' <= townName && townName <= 'E';
    }
}
