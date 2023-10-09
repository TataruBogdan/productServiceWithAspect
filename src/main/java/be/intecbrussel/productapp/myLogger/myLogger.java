package be.intecbrussel.productapp.myLogger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class myLogger {

    private static final String LOG_FILE = "src/main/resources/application.log";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyy HH:mm:ss");

    public static void log(String massage){
        String messageFormatted = String.format("log: - [%s] %s", dateFormat.format(new Date()), massage);
        writeToFile(messageFormatted);
    }

    private  static void writeToFile(String message) {

        try (PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(LOG_FILE, true)))) {
            printWriter.println(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
