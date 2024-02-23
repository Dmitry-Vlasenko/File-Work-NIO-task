package app.views;

import app.config.Config;
import app.services.FileReadService;
import app.services.FileWriteService;
import app.utils.FilesUtil;

import java.util.Map;
import java.util.Scanner;


public class WriteView {
    private static final String writeMenu = """
            ------------ WRITE --------------
            1) exist files list
            2) write file
            3) to previous menu
            """;

    public static void writeViewProcessing(Scanner scanner, FilesUtil existsFiles) {
        while (true) {
            System.out.println(writeMenu);
            System.out.print("Input your select: ");
            String readSelect = scanner.nextLine();
            switch (readSelect) {
                case "1":
                    String printFilesString = existsFiles.stringToPrintExistFiles();
                    System.out.println(printFilesString);
                    break;
                case "2":
                    String[] resultWrite = writeFileProcessing(scanner, existsFiles);
                    String writeFileContent = FileReadService.readFile(resultWrite[1]);
                    System.out.println("Write Success!");
                    System.out.println("File: " + resultWrite[0]);
                    System.out.println(writeFileContent);
                    break;
                case "3":
                    return;
                default:
                    System.out.println("Wrong selection");
                    break;
            }
        }
    }

    private static String[] writeFileProcessing(Scanner scanner, FilesUtil existsFiles) {
        System.out.println("Input write file name:");
        String fileName = scanner.nextLine();
        System.out.println("Input file content: ");
        String content = scanner.nextLine();
        String findFile = fileName + Config.FILE_FORMAT;
        Map<String, String> currentFilesHashTable = existsFiles.directoryFiles();
        String[] result = new String[2];
        result[0] = findFile;
        if (!currentFilesHashTable.containsKey(findFile)) {
            String newFilePath = FileWriteService.makeNewFilePath(findFile);
            FileWriteService.writeToFile(newFilePath, content);
            result[1] = newFilePath;
        } else {
            System.out.println("File with that name is exist");
            String existFilePath = currentFilesHashTable.get(findFile);
            FileWriteService.writeToFile(existFilePath, content);
            result[1] = existFilePath;
        }
        return result;
    }
}
