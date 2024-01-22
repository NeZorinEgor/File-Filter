import ru.neZorinEgor.task.OptionManager.OptionManager;

import java.io.File;
import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        String[] comand = new String[args.length];
        for (int i = 0; i < args.length; i++) {
            comand[i] = args[i];
        }

        System.out.println(Arrays.toString(args));
    }
}
