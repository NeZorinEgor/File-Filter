import java.util.Arrays;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        returnPath("integers.txt", "     ", "");
//        Scanner scanFiles = new Scanner(System.in);
//        String files = scanFiles.nextLine();
//        String[] split = files.split("\\s+");
//
//        StringBuilder path = new StringBuilder("");
//        StringBuilder prefix = new StringBuilder("");
//        boolean appendMode = false;
//        boolean fullStatistic = false;
//
//        for (int i = 0; i < split.length; i++){
//            switch (split[i]) {
//                case "-o" -> path.append(split[i + 1]);
//                case "-p" -> prefix.append( split[i + 1]);
//                case "-a" -> appendMode = true;
//                case "-s" -> fullStatistic = false;
//                case "-f" -> fullStatistic = true;
//                default -> System.out.println("неизвестная опция:" + split[i]);
//            }
//        }
//        System.out.println("path" + path);
//        System.out.println("prefix " + prefix);
//        System.out.println("append " + appendMode);
//        System.out.println("full statistic" + fullStatistic);
    }

    public static void returnPath(String name, String prefix, String path){
        if (!path.isEmpty()){
            System.out.println(path + "/" + prefix + name);
        } else System.out.println(path + prefix + name);

        if (!prefix.isEmpty()){
            System.out.println(path + "/" + prefix + name);
        } else System.out.println(path + prefix + name);


    }
}
