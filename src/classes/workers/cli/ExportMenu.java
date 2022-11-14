package classes.workers.cli;

import classes.data.*;
import classes.data.user.User;
import classes.data.user.Users;

import java.nio.file.Path;
import java.util.ArrayList;

class ExportMenu extends SuperMenu {

    ExportMenu() {
        super();
    }

    void exportMainMenu() {
        String read;
        try {
            System.out.print("""
                    -------------------------------------------------
                    EXPORT MENU
                    -------------------------------------------------
                    Enter 1 for export records from DB to json file
                    Enter 2 for export records from DB to xml file
                    Enter 0 for back to main menu
                    --------------------------------------------------
                    """);
            read = supporter.readChoice();
            switch (read) {
                case "1" -> jsonExportMenu();
                case "2" -> xmlExportMenu();
                case "0" -> {
                }
                default -> System.out.printf("'%s' is incorrect value, try again\n", read);
            }
        } catch (Exception e) {
            System.out.println("Exception in ExportMenu.exportMainMenu()");
        }
    }

    private void jsonExportMenu() {
        String read;
        try {
            System.out.print("""
                    -------------------------------------------------
                    JSON EXPORT MENU
                    -------------------------------------------------
                    Enter 1 for export 'Users' from DB to json file
                    Enter 2 for export 'Posts' from DB to json file
                    Enter 3 for export 'Comments' from DB to json file
                    Enter 4 for export 'Albums' from DB to json file
                    Enter 5 for export 'Photos' from DB to json file
                    Enter 0 for back to main menu
                    --------------------------------------------------
                    """);
            read = supporter.readChoice();
            switch (read) {
                case "1" -> {
                    ArrayList<User> users = dbWorker.getUsers();
                    String stringPath = supporter.readPath();
                    Path path = supporter.checkWriteFile(stringPath, ".json");
//                    gsonWorker.usersToJson(users, path);
                    gsonWorker.objToJson(users, path);
                }
                case "2" -> {
                    ArrayList<Post> posts = dbWorker.getPosts();
                    String stringPath = supporter.readPath();
                    Path path = supporter.checkWriteFile(stringPath, ".json");
                    gsonWorker.postsToJson(posts, path);
                }
                case "3" -> {
                    ArrayList<Comment> comments = dbWorker.getComments();
                    String stringPath = supporter.readPath();
                    Path path = supporter.checkWriteFile(stringPath, ".json");
                    gsonWorker.commentsToJson(comments, path);
                }
                case "4" -> {
                    ArrayList<Album> albums = dbWorker.getAlbums();
                    String stringPath = supporter.readPath();
                    Path path = supporter.checkWriteFile(stringPath, ".json");
                    gsonWorker.albumsToJson(albums, path);
                }
                case "5" -> {
                    ArrayList<Photo> photos = dbWorker.getPhotos();
                    String stringPath = supporter.readPath();
                    Path path = supporter.checkWriteFile(stringPath, ".json");
                    gsonWorker.photosToJson(photos, path);
                }
                case "0" -> {
                }
                default -> System.out.printf("'%s' is incorrect value, try again\n", read);
            }
        } catch (Exception e) {
            System.out.println("Exception in ExportMenu.jsonExportMenu()");
        }
    }

    private void xmlExportMenu() {
        String read;
        try {
            System.out.print("""
                    -------------------------------------------------
                    XML EXPORT MENU
                    -------------------------------------------------
                    Enter 1 for export 'Users' from DB to xml file
                    Enter 2 for export 'Posts' from DB to xml file
                    Enter 3 for export 'Comments' from DB to xml file
                    Enter 4 for export 'Albums' from DB to xml file
                    Enter 5 for export 'Photos' from DB to xml file
                    Enter 0 for back to main menu
                    --------------------------------------------------
                    """);
            read = supporter.readChoice();
            switch (read) {
                case "1" -> {
                    Users users = new Users(dbWorker.getUsers());
                    String stringPath = supporter.readPath();
                    Path path = supporter.checkWriteFile(stringPath, ".xml");
                    jaxbWorker.usersToXml(users, path);
                }
                case "2" -> {
                    Posts posts = new Posts(dbWorker.getPosts());
                    String stringPath = supporter.readPath();
                    Path path = supporter.checkWriteFile(stringPath, ".xml");
                    jaxbWorker.postsToXml(posts, path);
                }
                case "3" -> {
                    Comments comments = new Comments(dbWorker.getComments());
                    String stringPath = supporter.readPath();
                    Path path = supporter.checkWriteFile(stringPath, ".xml");
                    jaxbWorker.commentsToXml(comments, path);
                }
                case "4" -> {
                    Albums albums = new Albums(dbWorker.getAlbums());
                    String stringPath = supporter.readPath();
                    Path path = supporter.checkWriteFile(stringPath, ".xml");
                    jaxbWorker.albumsToXml(albums, path);
                }
                case "5" -> {
                    Photos photos = new Photos(dbWorker.getPhotos());
                    String stringPath = supporter.readPath();
                    Path path = supporter.checkWriteFile(stringPath, ".xml");
                    jaxbWorker.photosToXml(photos, path);
                }
                case "0" -> {
                }
                default -> System.out.printf("'%s' is incorrect value, try again\n", read);
            }
        } catch (Exception e) {
            System.out.println("Exception in ExportMenu.xmlExportMenu()");
        }
    }

}
