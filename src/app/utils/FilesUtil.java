package app.utils;


import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public record FilesUtil(String path) {

    public static String createBaseDirectory(String folder_name) {
        String systemSeparator = FileSystems.getDefault().getSeparator();
        String baseDirectory = System.getProperty("user.dir") + systemSeparator + folder_name + systemSeparator;
        Path directory = Paths.get(baseDirectory);
        try {
            Files.createDirectories(directory);
        } catch (Exception e) {
            System.out.println("Failed to create directory");
        }
        return baseDirectory;
    }

    public String stringToPrintExistFiles() {
        StringBuilder listFilesBuffer = new StringBuilder();
        listFilesBuffer.append("------------ EXIST FILES --------------\n");
        directoryFiles().forEach(
                (fileName, filePath) -> listFilesBuffer
                        .append("File name: ").append(fileName).append("\n")
        );
        return listFilesBuffer.toString();
    }

    private void addFilesFromDirectory(String path, Map<String, String> filesTable) {
        try {
            try (Stream<Path> paths = Files.list(Paths.get(path))) {
                paths.forEach(filePath -> {
                    if (Files.isDirectory(filePath)) {
                        addFilesFromDirectory(filePath.toString(), filesTable);
                    } else {
                        filesTable.put(filePath.getFileName().toString(), filePath.toString());
                    }
                });
            }
        } catch (Exception e) {
            System.out.println("Problem with adding files to directory");
        }
    }

    public Map<String, String> directoryFiles() {
        Map<String, String> filesTable = new HashMap<>();
        addFilesFromDirectory(this.path, filesTable);
        return filesTable;
    }
}
