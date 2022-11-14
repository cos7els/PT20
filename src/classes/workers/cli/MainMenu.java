package classes.workers.cli;

public class MainMenu {
    private final Supporter supporter;
    private final ImportMenu importMenu;
    private final ExportMenu exportMenu;
    private final SelectMenu selectMenu;
    private final ModifyMenu modifyMenu;

    MainMenu() {
        supporter = new Supporter();
        importMenu = new ImportMenu();
        exportMenu = new ExportMenu();
        selectMenu = new SelectMenu();
        modifyMenu = new ModifyMenu();
    }

    public static void start() {
        new MainMenu().mainMenu();
    }

    private void mainMenu() {
        String read;
        try {
            do {
                System.out.print("""
                        -------------------------------------------------
                        MAIN MENU
                        -------------------------------------------------
                        Enter 1 for import records to DB
                        Enter 2 for export records from DB
                        Enter 3 for select records from DB
                        Enter 4 for modify DB records
                        Enter 0 for exit
                        --------------------------------------------------
                        """);
                read = supporter.readChoice();
                switch (read) {
                    case "1" -> importMenu.importMainMenu();
                    case "2" -> exportMenu.exportMainMenu();
                    case "3" -> selectMenu.selectMainMenu();
                    case "4" -> modifyMenu.modifyMainMenu();
                    case "0" -> {
                        System.out.print("Are you sure want to exit? Y/N: ");
                        if (supporter.readChoice().equalsIgnoreCase("n")) {
                            read = "continue";
                        }
                    }
                    default -> System.out.printf("'%s' is incorrect value, try again\n", read);
                }
            } while (!read.equalsIgnoreCase("0"));
        } catch (Exception e) {
            System.out.println("Exception in MainMenu.mainMenu()");
        }
    }

}
