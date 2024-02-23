package app.services;

import app.config.Config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileWriteService {
    public static void writeToFile(String writePath, String content) {
        Path filePath = Paths.get(writePath);
        try {
            Files.write(filePath, content.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            System.out.println("Problem with write file");
        }
    }

    public static String makeNewFilePath(String newFileName) {
        return Config.BASE_DIRECTORY + newFileName;
    }

}
