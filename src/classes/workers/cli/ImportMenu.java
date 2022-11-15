package classes.workers.cli;

import classes.data.*;
import classes.data.user.User;
import classes.data.user.Users;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.nio.file.Path;
import java.util.ArrayList;

class ImportMenu extends SuperMenu {

    ImportMenu() {
        super();
    }

    void importMainMenu() {
        String read;
        try {
            System.out.print("""
                    -------------------------------------------------
                    IMPORT MENU
                    -------------------------------------------------
                    Enter 1 for import records to DB from json file
                    Enter 2 for import records to DB from xml file
                    Enter 0 for back to main menu
                    --------------------------------------------------
                    """);
            read = supporter.readChoice();
            switch (read) {
                case "1" -> jsonImportMenu();
                case "2" -> xmlImportMenu();
                case "0" -> {
                }
                default -> System.out.printf("'%s' is incorrect value, try again\n", read);
            }
        } catch (Exception e) {
            System.out.println("Exception in ImportMenu.importMainMenu()");
        }
    }

    private void jsonImportMenu() {
        String read;
        try {
            System.out.print("""
                    -------------------------------------------------
                    JSON IMPORT MENU
                    -------------------------------------------------
                    Enter 1 for import 'Users' to DB from json file
                    Enter 2 for import 'Posts' to DB from json file
                    Enter 3 for import 'Comments' to DB from json file
                    Enter 4 for import 'Albums' to DB from json file
                    Enter 5 for import 'Photos' to DB from json file
                    Enter 0 for back to main menu
                    --------------------------------------------------
                    """);
            read = supporter.readChoice();
            switch (read) {
                case "1" -> {
                    String stringPath = supporter.readPath();
                    Path path = supporter.checkReadFile(stringPath, ".json");
                    Type type = new TypeToken<ArrayList<User>>() {
                    }.getType();
                    ArrayList<User> users = gsonWorker.jsonToObj(type, path);
                    dbWorker.addUsers(users);
                }
                case "2" -> {
                    String stringPath = supporter.readPath();
                    Path path = supporter.checkReadFile(stringPath, ".json");
                    Type type = new TypeToken<ArrayList<Post>>() {
                    }.getType();
                    ArrayList<Post> posts = gsonWorker.jsonToObj(type, path);
                    dbWorker.addPosts(posts);
                }
                case "3" -> {
                    String stringPath = supporter.readPath();
                    Path path = supporter.checkReadFile(stringPath, ".json");
                    Type type = new TypeToken<ArrayList<Comment>>() {
                    }.getType();
                    ArrayList<Comment> comments = gsonWorker.jsonToObj(type, path);
                    dbWorker.addComments(comments);
                }
                case "4" -> {
                    String stringPath = supporter.readPath();
                    Path path = supporter.checkReadFile(stringPath, ".json");
                    Type type = new TypeToken<ArrayList<Album>>() {
                    }.getType();
                    ArrayList<Album> albums = gsonWorker.jsonToObj(type, path);
                    dbWorker.addAlbums(albums);
                }
                case "5" -> {
                    String stringPath = supporter.readPath();
                    Path path = supporter.checkReadFile(stringPath, ".json");
                    Type type = new TypeToken<ArrayList<Photo>>() {
                    }.getType();
                    ArrayList<Photo> photos = gsonWorker.jsonToObj(type, path);
                    dbWorker.addPhotos(photos);
                }
                case "0" -> {
                }
                default -> System.out.printf("'%s' is incorrect value, try again\n", read);
            }
        } catch (Exception e) {
            System.out.println("Exception in ImportMenu.jsonImportMenu()");
            e.printStackTrace();
        }
    }

    private void xmlImportMenu() {
        String read;
        try {
            System.out.print("""
                    -------------------------------------------------
                    XML IMPORT MENU
                    -------------------------------------------------
                    Enter 1 for import 'Users' to DB from xml file
                    Enter 2 for import 'Posts' to DB from xml file
                    Enter 3 for import 'Comments' to DB from xml file
                    Enter 4 for import 'Albums' to DB from xml file
                    Enter 5 for import 'Photos' to DB from xml file
                    Enter 0 for back to main menu
                    --------------------------------------------------
                    """);
            read = supporter.readChoice();
            switch (read) {
                case "1" -> {
                    String stringPath = supporter.readPath();
                    Path path = supporter.checkReadFile(stringPath, ".xml");
                    ArrayList<User> users = jaxbWorker.xmlToObj(new Users(), path).getUser();
                    dbWorker.addUsers(users);
                }
                case "2" -> {
                    String stringPath = supporter.readPath();
                    Path path = supporter.checkReadFile(stringPath, ".xml");
                    ArrayList<Post> posts = jaxbWorker.xmlToObj(new Posts(), path).getPost();
                    dbWorker.addPosts(posts);
                }
                case "3" -> {
                    String stringPath = supporter.readPath();
                    Path path = supporter.checkReadFile(stringPath, ".xml");
                    ArrayList<Comment> comments = jaxbWorker.xmlToObj(new Comments(), path).getComment();
                    dbWorker.addComments(comments);
                }
                case "4" -> {
                    String stringPath = supporter.readPath();
                    Path path = supporter.checkReadFile(stringPath, ".xml");
                    ArrayList<Album> albums = jaxbWorker.xmlToObj(new Albums(), path).getAlbum();
                    dbWorker.addAlbums(albums);
                }
                case "5" -> {
                    String stringPath = supporter.readPath();
                    Path path = supporter.checkReadFile(stringPath, ".xml");
                    ArrayList<Photo> photos = jaxbWorker.xmlToObj(new Photos(), path).getPhoto();
                    dbWorker.addPhotos(photos);
                }
                case "0" -> {
                }
                default -> System.out.printf("'%s' is incorrect value, try again\n", read);
            }
        } catch (Exception e) {
            System.out.println("Exception in ImportMenu.xmlImportMenu()");
        }
    }

}
