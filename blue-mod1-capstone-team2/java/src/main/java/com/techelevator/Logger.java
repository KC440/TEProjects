package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Logger {

    public void logFile(String type, String log) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss a");
        File newFile = new File("Log.txt");
        boolean append = newFile.exists();
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(newFile, append))){
            writer.append(formatter.format(new Date())).append(" ").append(type).append(" ").append(log);
            writer.println();
            writer.flush();
        } catch(
                FileNotFoundException ex) {
            System.out.println("File does not exist.");
        }

    }
    public void clearLog() {
        File clearFile = new File("Log.txt");
       try(PrintWriter writer = new PrintWriter(clearFile)) {
           writer.println("");
       } catch (FileNotFoundException fnfe) {
           System.out.println(fnfe.getMessage());
       }
    }
}
