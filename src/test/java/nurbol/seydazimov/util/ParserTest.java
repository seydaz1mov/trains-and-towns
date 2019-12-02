package nurbol.seydazimov.util;

import nurbol.seydazimov.exceptions.InvalidRouteException;
import nurbol.seydazimov.input.InputEdge;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    Parser parser = new Parser();

    @Test
    public void parseSimpleValidInputTest() throws InvalidRouteException{
        String inputGraphString = "AB5, BC4, CD8, DC8";

        List<InputEdge> inputEdges = parser.parse(inputGraphString);
        assertEquals(inputEdges.size(), 4);

        assertEquals(inputEdges.get(0).fromTown, 'A');
        assertEquals(inputEdges.get(0).toTown, 'B');
        assertEquals(inputEdges.get(0).distance, 5);

        assertEquals(inputEdges.get(1).fromTown, 'B');
        assertEquals(inputEdges.get(1).toTown, 'C');
        assertEquals(inputEdges.get(1).distance, 4);

        assertEquals(inputEdges.get(2).fromTown, 'C');
        assertEquals(inputEdges.get(2).toTown, 'D');
        assertEquals(inputEdges.get(2).distance, 8);

        assertEquals(inputEdges.get(3).fromTown, 'D');
        assertEquals(inputEdges.get(3).toTown, 'C');
        assertEquals(inputEdges.get(3).distance, 8);
    }

    @Test
    public void parseValidInputWithNotTypicalSpacesCount() throws InvalidRouteException {
        String inputGraphString = "   AB5 , BC4   , CD8  ";

        List<InputEdge> inputEdges = parser.parse(inputGraphString);
        assertEquals(inputEdges.size(), 3);

        assertEquals(inputEdges.get(0).fromTown, 'A');
        assertEquals(inputEdges.get(0).toTown, 'B');
        assertEquals(inputEdges.get(0).distance, 5);

        assertEquals(inputEdges.get(1).fromTown, 'B');
        assertEquals(inputEdges.get(1).toTown, 'C');
        assertEquals(inputEdges.get(1).distance, 4);

        assertEquals(inputEdges.get(2).fromTown, 'C');
        assertEquals(inputEdges.get(2).toTown, 'D');
        assertEquals(inputEdges.get(2).distance, 8);
    }

    @Test
    public void parseInvalidInputWithInvalidRouteFormatTest() {
        String input = "AB5 , B4C, BC3";
        assertThrows(InvalidRouteException.class, () -> {
            parser.parse(input);
        });
    }

    @Test
    public void parseInvalidInputTest() {
        String input = "bla bla bla, yo yo yo";
        assertThrows(InvalidRouteException.class, () -> {
            parser.parse(input);
        });
    }
}