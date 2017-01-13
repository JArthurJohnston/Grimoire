package helpers;

import java.nio.file.Paths;

/**
 * Created by Arthur on 1/12/2017.
 */
public class Helper {

    public static void printWorkingDirectory(){
        System.out.println("PWD: " + Paths.get(".").toAbsolutePath().normalize().toString());
    }
}
