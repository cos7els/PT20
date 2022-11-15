package classes.workers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class GsonWorker {
    private final Gson gson;

    public GsonWorker() {
        gson = new Gson();
    }

    public <T> ArrayList<T> jsonToObj(Type type, Path path) {
        String json = readJson(path);
        return gson.fromJson(json, type);
    }

    public <T> void objToJson(ArrayList<T> objects, Path path) {
        Type type = new TypeToken<ArrayList<T>>() {
        }.getType();
        String json = gson.toJson(objects, type);
        writeJson(json, path);
    }

    private String readJson(Path path) {
        String json = null;
        try {
            json = new String(Files.readAllBytes(path));
        } catch (IOException e) {
            System.out.println("IOException in GsonWorker.readJson()");
        }
        return json;
    }

    private void writeJson(String json, Path path) {
        try {
            Files.write(path, json.getBytes());
        } catch (IOException e) {
            System.out.println("IOException in GsonWorker.writeJson()");
        }
    }

}