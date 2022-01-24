package com.model;

import java.io.*;

public class Logger {
    File loggerFileObj;
    //    FileWriter logWriter;
    PrintWriter out;

    public Logger() {
        try {
            this.out = new PrintWriter("operationLogger.txt");
            this.loggerFileObj = new File("operationLogger.txt");

            if (loggerFileObj.createNewFile()) {
                System.out.println("File created: " + loggerFileObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void logOperation(String userId, String operationName, int sum, String machineId) {
        String text = "ID: " + userId + ", Operation: " + operationName + ", Sum: " + sum + ", MachineId : " + machineId;
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("operationLogger.txt", true));
            // Writing on output stream
            out.write(text);
            // Closing the connection
            out.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
