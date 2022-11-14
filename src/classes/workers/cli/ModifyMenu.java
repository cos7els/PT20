package classes.workers.cli;

class ModifyMenu extends SuperMenu {

    ModifyMenu() {
        super();
    }

    void modifyMainMenu() {
        String read;
        try {
            System.out.print("""
                    -------------------------------------------------
                    MODIFY MENU
                    -------------------------------------------------
                    Enter 1 for work with 'Users'
                    Enter 2 for work with 'Posts'
                    Enter 3 for work with 'Comments'
                    Enter 4 for work with 'Albums'
                    Enter 5 for work with 'Photos'
                    Enter 0 for back to main menu
                    --------------------------------------------------
                    """);
            read = supporter.readChoice();
            switch (read) {
                case "1" -> usersModifyMenu();
                case "2" -> postsModifyMenu();
                case "3" -> commentsModifyMenu();
                case "4" -> albumsModifyMenu();
                case "5" -> photosModifyMenu();
                case "0" -> {
                }
                default -> System.out.printf("'%s' is incorrect value, try again\n", read);
            }
        } catch (Exception e) {
            System.out.println("Exception in ModifyMenu.modifyMainMenu()");
        }
    }

    private void usersModifyMenu() {
        String read;
        try {
            System.out.print("""
                    -------------------------------------------------
                    USERS MODIFY MENU
                    -------------------------------------------------
                    Enter 1 for add 'User' to DB
                    Enter 2 for remove 'User' from DB
                    Enter 3 for update 'User' in DB
                    Enter 0 for back to main menu
                    --------------------------------------------------
                    """);
            read = supporter.readChoice();
            switch (read) {
                case "1" -> dbWorker.addUser();
                case "2" -> {
                    int id = supporter.readId("user");
                    dbWorker.removeUser(id);
                }
                case "3" -> {
                    int id = supporter.readId("user");
                    dbWorker.updateUser(id);
                }
                case "0" -> {
                }
                default -> System.out.printf("'%s' is incorrect value, try again\n", read);
            }
        } catch (Exception e) {
            System.out.println("Exception in ModifyMenu.usersModifyMenu()");
        }
    }

    private void postsModifyMenu() {
        String read;
        try {
            System.out.print("""
                    -------------------------------------------------
                    POSTS MODIFY MENU
                    -------------------------------------------------
                    Enter 1 for add 'Post' to DB
                    Enter 2 for remove 'Post' from DB
                    Enter 3 for update 'Post' in DB
                    Enter 0 for back to main menu
                    --------------------------------------------------
                    """);
            read = supporter.readChoice();
            switch (read) {
                case "1" -> dbWorker.addPost();
                case "2" -> {
                    int id = supporter.readId("post");
                    dbWorker.removePost(id);
                }
                case "3" -> {
                    int id = supporter.readId("post");
                    dbWorker.updatePost(id);
                }
                case "0" -> {
                }
                default -> System.out.printf("'%s' is incorrect value, try again\n", read);
            }
        } catch (Exception e) {
            System.out.println("Exception in ModifyMenu.postsModifyMenu()");
        }
    }

    private void commentsModifyMenu() {
        String read;
        try {
            System.out.print("""
                    -------------------------------------------------
                    COMMENTS MODIFY MENU
                    -------------------------------------------------
                    Enter 1 for add 'Comment' to DB
                    Enter 2 for remove 'Comment' from DB
                    Enter 3 for update 'Comment' in DB
                    Enter 0 for back to main menu
                    --------------------------------------------------
                    """);
            read = supporter.readChoice();
            switch (read) {
                case "1" -> dbWorker.addComment();
                case "2" -> {
                    int id = supporter.readId("comment");
                    dbWorker.removeComment(id);
                }
                case "3" -> {
                    int id = supporter.readId("comment");
                    dbWorker.updateComment(id);
                }
                case "0" -> {
                }
                default -> System.out.printf("'%s' is incorrect value, try again\n", read);
            }
        } catch (Exception e) {
            System.out.println("Exception in ModifyMenu.commentsModifyMenu()");
        }
    }

    private void albumsModifyMenu() {
        String read;
        try {
            System.out.print("""
                    -------------------------------------------------
                    ALBUMS MODIFY MENU
                    -------------------------------------------------
                    Enter 1 for add 'Album' to DB
                    Enter 2 for remove 'Album' from DB
                    Enter 3 for update 'Album' in DB
                    Enter 0 for back to main menu
                    --------------------------------------------------
                    """);
            read = supporter.readChoice();
            switch (read) {
                case "1" -> dbWorker.addAlbum();
                case "2" -> {
                    int id = supporter.readId("album");
                    dbWorker.removeAlbum(id);
                }
                case "3" -> {
                    int id = supporter.readId("album");
                    dbWorker.updateAlbum(id);
                }
                case "0" -> {
                }
                default -> System.out.printf("'%s' is incorrect value, try again\n", read);
            }
        } catch (Exception e) {
            System.out.println("Exception in ModifyMenu.albumsModifyMenu()");
        }
    }

    private void photosModifyMenu() {
        String read;
        try {
            System.out.print("""
                    -------------------------------------------------
                    PHOTOS MODIFY MENU
                    -------------------------------------------------
                    Enter 1 for add 'Photo' to DB
                    Enter 2 for remove 'Photo' from DB
                    Enter 3 for update 'Photo' in DB
                    Enter 0 for back to main menu
                    --------------------------------------------------
                    """);
            read = supporter.readChoice();
            switch (read) {
                case "1" -> dbWorker.addPhoto();
                case "2" -> {
                    int id = supporter.readId("photo");
                    dbWorker.removePhoto(id);
                }
                case "3" -> {
                    int id = supporter.readId("photo");
                    dbWorker.updatePhoto(id);
                }
                case "0" -> {
                }
                default -> System.out.printf("'%s' is incorrect value, try again\n", read);
            }
        } catch (Exception e) {
            System.out.println("Exception in ModifyMenu.photosModifyMenu()");
        }
    }

}
