import java.util.Arrays;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scanFiles = new Scanner(System.in);
        String files = scanFiles.nextLine();
        String[] split = files.split("\\s+");
        for (int i = 0; i < split.length; i++){
            switch (split[i]) {
                case "-o" -> System.out.println("path: " + split[i + 1]);
                case "-p" -> System.out.println("file name: " + split[i + 1] + "integers.txt");
                case "-a" -> System.out.println("append mode: true");
                case "-s" -> System.out.println("full statistic: false");
                case "-f" -> System.out.println("full statistic: true");
            }
        }
    }
}
