package nurbol.seydazimov.graph.helper;

public class DistancePrinter {

    public void print(int distance, int outputNumber) {
        System.out.print("Output #" + outputNumber + ": ");
        System.out.println(distance == -1 ? "NO SUCH ROUTE" : distance);
    }
}
