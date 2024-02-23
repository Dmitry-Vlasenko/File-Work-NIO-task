import app.config.Config;
import app.utils.FilesUtil;
import app.views.ReadView;
import app.views.WriteView;

import java.util.Scanner;

public class Main {
    private static final String menu = """
            --------------
            Select action:
            --------------
            1) write file
            2) read file
            3) stop and exit
            """;

    public static void main(String[] args) {
        FilesUtil existsFiles = new FilesUtil(Config.BASE_DIRECTORY);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(menu);
            System.out.print("Input your select: ");
            String action = scanner.nextLine();
            switch (action) {
                case "1":
                    WriteView.writeViewProcessing(scanner, existsFiles);
                    break;
                case "2":
                    ReadView.readViewProcessing(scanner, existsFiles);
                    break;
                case "3":
                    System.out.println("By-by");
                    return;
                default:
                    System.out.println("Wrong selection");
                    break;
            }
        }
    }
}
