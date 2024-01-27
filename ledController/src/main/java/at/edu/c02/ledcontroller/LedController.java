package at.edu.c02.ledcontroller;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface LedController {
    void demo() throws IOException;
    void status() throws IOException;


}
