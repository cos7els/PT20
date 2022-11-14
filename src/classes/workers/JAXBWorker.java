package classes.workers;

import classes.data.*;
import classes.data.user.User;
import classes.data.user.Users;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.nio.file.Path;
import java.util.ArrayList;

public class JAXBWorker {

    public JAXBWorker() {
    }

    public ArrayList<User> xmlToUsers(Path path) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Users.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Users unmarshall = (Users) unmarshaller.unmarshal(path.toFile());
        return unmarshall.getUser();
    }

    public void usersToXml(Users users, Path path) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Users.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.marshal(users, path.toFile());
    }

    public ArrayList<Post> xmlToPosts(Path path) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Posts.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Posts unmarshall = (Posts) unmarshaller.unmarshal(path.toFile());
        return unmarshall.getPost();
    }

    public void postsToXml(Posts posts, Path path) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Posts.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.marshal(posts, path.toFile());
    }

    public ArrayList<Comment> xmlToComments(Path path) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Comments.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Comments unmarshall = (Comments) unmarshaller.unmarshal(path.toFile());
        return unmarshall.getComment();
    }

    public void commentsToXml(Comments comments, Path path) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Comments.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.marshal(comments, path.toFile());
    }

    public ArrayList<Album> xmlToAlbums(Path path) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Albums.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Albums unmarshall = (Albums) unmarshaller.unmarshal(path.toFile());
        return unmarshall.getAlbum();
    }

    public void albumsToXml(Albums albums, Path path) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Albums.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.marshal(albums, path.toFile());
    }

    public ArrayList<Photo> xmlToPhotos(Path path) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Photos.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Photos unmarshall = (Photos) unmarshaller.unmarshal(path.toFile());
        return unmarshall.getPhoto();
    }

    public void photosToXml(Photos photos, Path path) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Photos.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.marshal(photos, path.toFile());
    }

}
