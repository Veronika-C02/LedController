package at.edu.c02.ledcontroller;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.util.Scanner;

/**
 * This class handles the actual logic
 */
public class LedControllerImpl implements LedController {
    private final ApiService apiService;

    public LedControllerImpl(ApiService apiService)
    {
        this.apiService = apiService;
    }

    @Override
    public void demo() throws IOException
    {
        // Call `getLights`, the response is a json object in the form `{ "lights": [ { ... }, { ... } ] }`
        JSONObject response = apiService.getLights();
        // get the "lights" array from the response
        JSONArray lights = response.getJSONArray("lights");
        // read the first json object of the lights array
        JSONObject firstLight = lights.getJSONObject(0);
        // read int and string properties of the light
        System.out.println("First light id is: " + firstLight.getInt("id"));
        System.out.println("First light color is: " + firstLight.getString("color"));
    }

    @Override
    public void status() throws IOException {

        System.out.println("Geben Sie hier die ID des LEDs an: ");
        Scanner sc = new Scanner(System.in);
        String inputString = sc.nextLine();
        int inputID = Integer.parseInt(inputString);

        // Call `getLights`, the response is a json object in the form `{ "lights": [ { ... }, { ... } ] }`
        JSONObject response = apiService.getLights(inputID);
        // get the "lights" array from the response
        JSONArray lights = response.getJSONArray("lights");
        // read the first json object of the lights array
        JSONObject specifiedLed = lights.getJSONObject(0);
        // read int and string properties of the light

        String onOffButton;
        if (specifiedLed.getBoolean("on") == true){
            onOffButton = "on";
        }else onOffButton = "off";

        System.out.println("LED: "+ inputID + " is currently " + onOffButton + ". Color: " + specifiedLed.getString("color"));


    }

    public JSONArray getGroupLeds() throws IOException {
        JSONArray array = new JSONArray();
        JSONObject response = apiService.getLights();
        JSONArray lights = response.getJSONArray("lights");
        int index = 0;
        for (Object light : lights) {
            JSONObject specifiedLed = lights.getJSONObject(index);
            String group = specifiedLed.getJSONObject("groupByGroup").getString("name");
            if (group.equals("D")){
                array.put(specifiedLed);
            }
            index++;
        }
        return array;
    }

    public void groupstatus() throws IOException{

        JSONArray array = getGroupLeds();
        int index = 0;
        for (Object o : array) {
            JSONObject current = array.getJSONObject(index);
            String onOffButton;
            if (current.getBoolean("on") == true){
                onOffButton = "on";
            }else onOffButton = "off";

            System.out.println("LED: "+ current.getInt("id") + " is currently " + onOffButton + ". Color: " + current.getString("color"));

            index++;
        }

    }

}
