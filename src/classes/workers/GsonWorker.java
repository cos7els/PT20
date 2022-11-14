package classes.workers;

import classes.data.Album;
import classes.data.Comment;
import classes.data.Photo;
import classes.data.Post;
import classes.data.user.User;
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

    public ArrayList<User> jsonToUsers(Path path) {
        String json = readJson(path);
        Type type = new TypeToken<ArrayList<User>>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    public ArrayList<Post> jsonToPosts(Path path) {
        String json = readJson(path);
        Type type = new TypeToken<ArrayList<Post>>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    public ArrayList<Comment> jsonToComments(Path path) {
        String json = readJson(path);
        Type type = new TypeToken<ArrayList<Comment>>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    public ArrayList<Album> jsonToAlbums(Path path) {
        String json = readJson(path);
        Type type = new TypeToken<ArrayList<Album>>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    public ArrayList<Photo> jsonToPhotos(Path path) {
        String json = readJson(path);
        Type type = new TypeToken<ArrayList<Photo>>() {
        }.getType();
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