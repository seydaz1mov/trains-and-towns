package nurbol.seydazimov.input;

public class InputEdge {
    public final char fromTown;
    public final char toTown;
    public final int distance;

    public InputEdge(final char fromTown, final char toTown, final int distance) {
        this.fromTown = fromTown;
        this.toTown = toTown;
        this.distance = distance;
    }
}
