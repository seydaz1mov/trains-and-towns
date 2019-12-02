package nurbol.seydazimov.util;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class InputFileReader {
    final String filename = "input.txt";
    final BufferedReader bufferedReader;

    public InputFileReader() throws FileNotFoundException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(filename).getFile());
        bufferedReader = new BufferedReader(new FileReader(file));
    }

    public String readFile() throws IOException {
        String input = bufferedReader.readLine();
        return input;
    }
}
