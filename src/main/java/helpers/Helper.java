package helpers;

import java.nio.file.Paths;

/**
 * Created by Arthur on 1/12/2017.
 */
public class Helper {

    public static String printWorkingDirectory(){
        String workingDirectory = "PWD: " + Paths.get(".").toAbsolutePath().normalize().toString();
        System.out.println(workingDirectory);
        return workingDirectory;
    }
}
