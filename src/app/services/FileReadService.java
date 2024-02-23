package app.services;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileReadService {
    public static String readFile(String path) {
        StringBuilder content = new StringBuilder();
        Path filePath = Paths.get(path);
        try (Stream<String> linesStream = Files.lines(filePath, StandardCharsets.UTF_8)) {
            linesStream.forEach(line -> content.append(line).append("\n"));
        } catch (IOException e) {
            System.out.println("Problem with read file");
        }
        return content.toString();
    }
}