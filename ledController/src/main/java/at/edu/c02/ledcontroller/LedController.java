package at.edu.c02.ledcontroller;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface LedController {
    void demo() throws IOException;
    void status() throws IOException;

    public static JSONArray getGroupLeds() throws IOException {
        JSONArray array = new JSONArray();
        ApiServiceImpl api = new ApiServiceImpl();
        JSONObject response = api.getLights();
        JSONArray lights = response.getJSONArray("lights");
        int index = 0;
        for (Object light : lights) {
            JSONObject specifiedLed = lights.getJSONObject(index);
            String group = specifiedLed.getString("groupByGroup");
            if (group == "D"){
                array.put(specifiedLed);
            }
            index++;
        }
            return array;
    }

}
