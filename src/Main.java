import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class Main {

    public static void main(String[] args) {

        Path sourceFile1 = Path.of("D:/123/v1.txt");
        Path targetFile1 = Path.of("D:/123/v2.txt");
        Path sourceFile2 = Path.of("D:/123/w1.txt");
        Path targetFile2 = Path.of("D:/123/w2.txt");


        long startTime = System.currentTimeMillis();
        copyFile(sourceFile1, targetFile1);
        copyFile(sourceFile2, targetFile2);
        long endTime = System.currentTimeMillis();
        System.out.println("Последовательное копирование: " + (endTime - startTime) + " мс");

       
        startTime = System.currentTimeMillis();
        Thread thread1 = new Thread(() -> copyFile(sourceFile1, targetFile1));
        Thread thread2 = new Thread(() -> copyFile(sourceFile2, targetFile2));
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        endTime = System.currentTimeMillis();
        System.out.println("Параллельное копирование: " + (endTime - startTime) + " мс");
    }

    private static void copyFile(Path source, Path target) {
        try {
            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}