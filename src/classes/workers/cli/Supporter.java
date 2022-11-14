package classes.workers.cli;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class Supporter {
    private final Scanner in;

    public Supporter() {
        in = new Scanner(System.in);
    }

    String readChoice() {
        System.out.print("Your choice: ");
        return in.nextLine();
    }

    String readPath() {
        System.out.print("Enter the path to file: ");
        return in.nextLine();
    }

    String readName() {
        System.out.print("Enter the name of 'User': ");
        return in.nextLine();
    }

    public String readClassData(String varName, String className) {
        System.out.printf("Enter the %s of '%s': ", varName, className);
        return in.nextLine();
    }

    int readId(String tableName) {
        System.out.printf("Enter the id of '%s': ", tableName);
        return Integer.parseInt(in.nextLine());
    }

    Path checkWriteFile(String path, String extension) throws IOException {
        String newPath = checkExtension(path, extension);
        File file = new File(newPath);
        if (!file.exists()) {
            file.createNewFile();
        }
        return file.toPath();
    }

    Path checkReadFile(String path, String extension) {
        String newPath = checkExtension(path, extension);
        File file = new File(newPath);
        while (!file.exists()) {
            System.out.printf("File '%s' is not exist, try again\n", file.getAbsolutePath());
            file = new File(checkExtension(readPath(), extension));
        }
        return file.toPath();
    }

    String checkExtension(String path, String extension) {
        return path.endsWith(extension) ? path : path + extension;
    }

}
