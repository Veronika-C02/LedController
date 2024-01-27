package at.edu.c02.ledcontroller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    /**
     * This is the main program entry point. TODO: add new commands when implementing additional features.
     */
    public static void main(String[] args) throws IOException {
        ApiServiceImpl apiService = new ApiServiceImpl();
        LedController ledController = new LedControllerImpl(apiService);



        String input = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while(!input.equalsIgnoreCase("exit"))
        {
            System.out.println("=== LED Controller ===");
            System.out.println("Enter 'demo' to send a demo request");
            System.out.println("Enter 'exit' to exit the program");
            System.out.println("Enter 'status' to get a status of one LED");
            System.out.println("Enter 'groupstatus' to get a status of one LED");
            input = reader.readLine();
            if(input.equalsIgnoreCase("demo"))
            {
                ledController.demo();
            }
            if(input.equalsIgnoreCase("status"))
            {
                ledController.status();
            }

            if(input.equalsIgnoreCase("groupstatus"))
            {
                ledController.groupstatus();
            }
            if(input.equalsIgnoreCase("setLight"))
            {
                apiService.setLights();
            }


        }

    }
}
