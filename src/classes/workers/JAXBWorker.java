package classes.workers;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.nio.file.Path;

public class JAXBWorker {

    public JAXBWorker() {
    }

    public <T> T xmlToObj(T t, Path path) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(t.getClass());
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            t = (T) unmarshaller.unmarshal(path.toFile());
        } catch (JAXBException e) {
            System.out.println("JAXBException in JAXBWorker.xmlToObj()");
            e.printStackTrace();
        }
        return t;
    }

    public <T> void objToXml(T t, Path path) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(t.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.marshal(t, path.toFile());
        } catch (JAXBException e) {
            System.out.println("JAXBException in JAXBWorker.objToXml()");
        }
    }

}
