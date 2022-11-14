package classes.workers.cli;

import classes.workers.DBWorker;
import classes.workers.GsonWorker;
import classes.workers.JAXBWorker;

abstract class SuperMenu {
    DBWorker dbWorker;
    GsonWorker gsonWorker;
    JAXBWorker jaxbWorker;
    Supporter supporter;

    SuperMenu() {
        dbWorker = new DBWorker();
        gsonWorker = new GsonWorker();
        jaxbWorker = new JAXBWorker();
        supporter = new Supporter();
    }

}
