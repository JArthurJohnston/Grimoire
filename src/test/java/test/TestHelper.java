package test;

import java.nio.file.Paths;

/**
 * Created by Arthur on 1/11/2017.
 */
@SuppressWarnings("Since15")
public class TestHelper {

    public static void printWorkingDirectory(){
        System.out.println("PWD: " + Paths.get(".").toAbsolutePath().normalize().toString());
    }
}
