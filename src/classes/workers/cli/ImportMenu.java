package classes.workers.cli;

import classes.data.*;
import classes.data.user.*;
import classes.workers.*;

import java.nio.file.Path;
import java.util.ArrayList;

class ImportMenu {
    private final DBWorker dbWorker;
    private final GsonWorker gsonWorker;
    private final JAXBWorker jaxbWorker;
    private final Supporter supporter;

    ImportMenu() {
        dbWorker = new DBWorker();
        gsonWorker = new GsonWorker();
        jaxbWorker = new JAXBWorker();
        supporter = new Supporter();
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
                case "0" -> {}
                default -> System.out.printf("'%s' is incorrect value, try again\n", read);
            }
        } catch (Exception e) {
            System.out.println("WRONG");
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
                    ArrayList<User> users = gsonWorker.jsonToUsers(path);
                    dbWorker.addUsers(users);
                }
                case "2" -> {
                    String stringPath = supporter.readPath();
                    Path path = supporter.checkReadFile(stringPath, ".json");
                    ArrayList<Post> posts = gsonWorker.jsonToPosts(path);
                    dbWorker.addPosts(posts);
                }
                case "3" -> {
                    String stringPath = supporter.readPath();
                    Path path = supporter.checkReadFile(stringPath, ".json");
                    ArrayList<Comment> comments = gsonWorker.jsonToComments(path);
                    dbWorker.addComments(comments);
                }
                case "4" -> {
                    String stringPath = supporter.readPath();
                    Path path = supporter.checkReadFile(stringPath, ".json");
                    ArrayList<Album> albums = gsonWorker.jsonToAlbums(path);
                    dbWorker.addAlbums(albums);
                }
                case "5" -> {
                    String stringPath = supporter.readPath();
                    Path path = supporter.checkReadFile(stringPath, ".json");
                    ArrayList<Photo> photos = gsonWorker.jsonToPhotos(path);
                    dbWorker.addPhotos(photos);
                }
                case "0" -> {
                }
                default -> System.out.printf("'%s' is incorrect value, try again\n", read);
            }
        } catch (Exception e) {
            System.out.println("WRONG");
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
                    ArrayList<User> users = jaxbWorker.xmlToUsers(path);
                    dbWorker.addUsers(users);
                }
                case "2" -> {
                    String stringPath = supporter.readPath();
                    Path path = supporter.checkReadFile(stringPath, ".xml");
                    ArrayList<Post> posts = jaxbWorker.xmlToPosts(path);
                    dbWorker.addPosts(posts);
                }
                case "3" -> {
                    String stringPath = supporter.readPath();
                    Path path = supporter.checkReadFile(stringPath, ".xml");
                    ArrayList<Comment> comments = jaxbWorker.xmlToComments(path);
                    dbWorker.addComments(comments);
                }
                case "4" -> {
                    String stringPath = supporter.readPath();
                    Path path = supporter.checkReadFile(stringPath, ".xml");
                    ArrayList<Album> albums = jaxbWorker.xmlToAlbums(path);
                    dbWorker.addAlbums(albums);
                }
                case "5" -> {
                    String stringPath = supporter.readPath();
                    Path path = supporter.checkReadFile(stringPath, ".xml");
                    ArrayList<Photo> photos = jaxbWorker.xmlToPhotos(path);
                    dbWorker.addPhotos(photos);
                }
                case "0" -> {}
                default -> System.out.printf("'%s' is incorrect value, try again\n", read);
            }
        } catch (Exception e) {
            System.out.println("WRONG");
        }
    }

}
