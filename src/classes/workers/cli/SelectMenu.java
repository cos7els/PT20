package classes.workers.cli;

import classes.workers.DBWorker;

class SelectMenu {
    private final DBWorker dbWorker;
    private final Supporter supporter;

    public SelectMenu() {
        dbWorker = new DBWorker();
        supporter = new Supporter();
    }

    void selectMainMenu() {
        String read;
        try {
            System.out.print("""
                    -------------------------------------------------
                    SELECT MENU
                    -------------------------------------------------
                    Enter 1 for select all 'Users'
                    Enter 2 for select all 'Posts'
                    Enter 3 for select all 'Comments'
                    Enter 4 for select all 'Albums'
                    Enter 5 for select all 'Photos'
                    Enter 6 for select 'User' by 'user id'
                    Enter 7 for select 'User' by 'user name'
                    Enter 8 for select 'Post' by 'user id'
                    Enter 9 for select 'Post' by 'user name'
                    Enter 10 for select 'Address' by 'user id'
                    Enter 11 for select 'Address' by 'user name'
                    Enter 12 for select 'Comment' by 'post id'
                    Enter 13 for select 'Comment' by 'user name'
                    Enter 0 for back to main menu
                    --------------------------------------------------
                    """);
            read = supporter.readChoice();
            switch (read) {
                case "1" -> dbWorker.getUsers().forEach(System.out::println);
                case "2" -> dbWorker.getPosts().forEach(System.out::println);
                case "3" -> dbWorker.getComments().forEach(System.out::println);
                case "4" -> dbWorker.getAlbums().forEach(System.out::println);
                case "5" -> dbWorker.getPhotos().forEach(System.out::println);
                case "6" -> System.out.println(dbWorker.getUser(supporter.readId("user")));
                case "7" -> System.out.println(dbWorker.getUser(supporter.readName()));
                case "8" -> dbWorker.selectPosts(supporter.readId("user")).forEach(System.out::println);
                case "9" -> dbWorker.selectPosts(supporter.readName()).forEach(System.out::println);
                case "10" -> System.out.println(dbWorker.getAddress(supporter.readId("user")));
                case "11" -> System.out.println(dbWorker.getAddress(supporter.readName()));
                case "12" -> dbWorker.selectComments(supporter.readId("post")).forEach(System.out::println);
                case "13" -> dbWorker.selectComments(supporter.readName()).forEach(System.out::println);
                case "0" -> {
                }
                default -> System.out.printf("'%s' is incorrect value, try again\n", read);
            }
        } catch (Exception e) {
            System.out.println("Exception in SelectMenu.selectMainMenu()");
        }
    }

}
