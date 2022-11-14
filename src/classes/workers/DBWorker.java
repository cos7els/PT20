package classes.workers;

import classes.data.*;
import classes.data.user.Address;
import classes.data.user.Company;
import classes.data.user.Geo;
import classes.data.user.User;
import classes.workers.cli.Supporter;

import java.sql.*;
import java.util.ArrayList;

public class DBWorker {
    private final String dbUrl;
    private final Supporter supporter;

    public DBWorker() {
        dbUrl = "jdbc:sqlite:project.db";
//        dbUrl = "jdbc:sqlite:D:\\DBs\\project.db";
        supporter = new Supporter();
    }

    public void addUser() {
        User user = createUserFromCLI();
        user.setId(getLastId("SELECT UserId FROM Users"));
        try (Connection connection = DriverManager.getConnection(dbUrl);
             PreparedStatement insert = connection.prepareStatement(
                     "INSERT INTO Users (UserId,Name,UserName,Email,Street,Suite,City,ZipCode,Lat,Lng,Phone," +
                             "Website,CompanyName,CatchPhrase,Bs) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)")) {
            stmtUserSetter(insert, user, 1, 2);
            insert.execute();
        } catch (SQLException e) {
            System.out.println("SQLException in DBWorker.addUser()");
            e.printStackTrace();
        }
    }

    public void addUsers(ArrayList<User> users) {
        try (Connection connection = DriverManager.getConnection(dbUrl);
             PreparedStatement insert = connection.prepareStatement(
                     "INSERT INTO Users (UserId,Name,UserName,Email,Street,Suite,City,ZipCode,Lat,Lng,Phone," +
                             "Website,CompanyName,CatchPhrase,Bs) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)")) {
            connection.setAutoCommit(false);
            for (User user : users) {
                stmtUserSetter(insert, user, 1, 2);
                insert.addBatch();
            }
            insert.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            System.out.println("SQLException in DBWorker.addUsers()");
        }
    }

    public void removeUser(int userId) {
        try (Connection connection = DriverManager.getConnection(dbUrl);
             PreparedStatement insert = connection.prepareStatement("DELETE FROM Users WHERE UserId = ?")) {
            insert.setInt(1, userId);
            insert.execute();
        } catch (SQLException e) {
            System.out.println("SQLException in DBWorker.removeUser()");
        }
    }

    public void updateUser(int userId) {
        String value;
        User user = getUser(userId);
        Address address = user.getAddress();
        Geo geo = address.getGeo();
        Company company = user.getCompany();
        String className = user.getClass().getSimpleName();
        User updUser = new User.Builder().id(userId)
                .name((value = supporter.readClassData("name", className)).equals("") ? user.getName() : value)
                .username((value = supporter.readClassData("user name", className)).equals("") ? user.getUsername() : value)
                .email((value = supporter.readClassData("email", className)).equals("") ? user.getEmail() : value)
                .street((value = supporter.readClassData("street", className)).equals("") ? address.getStreet() : value)
                .suite((value = supporter.readClassData("suite", className)).equals("") ? address.getSuite() : value)
                .city((value = supporter.readClassData("city", className)).equals("") ? address.getCity() : value)
                .zipcode((value = supporter.readClassData("zipcode", className)).equals("") ? address.getZipcode() : value)
                .lat((value = supporter.readClassData("lat", className)).equals("") ? geo.getLat() : value)
                .lng((value = supporter.readClassData("lng", className)).equals("") ? geo.getLng() : value)
                .phone((value = supporter.readClassData("phone", className)).equals("") ? user.getPhone() : value)
                .website((value = supporter.readClassData("website", className)).equals("") ? user.getWebsite() : value)
                .companyName((value = supporter.readClassData("company name", className)).equals("") ? company.getName() : value)
                .catchPhrase((value = supporter.readClassData("catch phrase", className)).equals("") ? company.getCatchPhrase() : value)
                .bs((value = supporter.readClassData("bs", className)).equals("") ? company.getBs() : value).build();
        try (Connection connection = DriverManager.getConnection(dbUrl);
             PreparedStatement update = connection.prepareStatement(
                     "UPDATE Users SET Name=?,UserName=?,Email=?,Street=?,Suite=?,City=?,ZipCode=?,Lat=?,Lng=?," +
                             "Phone=?,Website=?,CompanyName=?,CatchPhrase=?,Bs=? WHERE UserId=?")) {
            stmtUserSetter(update, updUser, 15, 1);
            update.execute();
        } catch (SQLException e) {
            System.out.println("SQLException in DBWorker.updateUser()");
            e.printStackTrace();
        }
    }

    private void stmtUserSetter(PreparedStatement stmt, User user, int idPos, int fieldsPos) {
        Address address = user.getAddress();
        Geo geo = address.getGeo();
        Company company = user.getCompany();
        try {
            stmt.setInt(idPos, user.getId());
            stmt.setString(fieldsPos++, user.getName());
            stmt.setString(fieldsPos++, user.getUsername());
            stmt.setString(fieldsPos++, user.getEmail());
            stmt.setString(fieldsPos++, address.getStreet());
            stmt.setString(fieldsPos++, address.getSuite());
            stmt.setString(fieldsPos++, address.getCity());
            stmt.setString(fieldsPos++, address.getZipcode());
            stmt.setString(fieldsPos++, geo.getLat());
            stmt.setString(fieldsPos++, geo.getLng());
            stmt.setString(fieldsPos++, user.getPhone());
            stmt.setString(fieldsPos++, user.getWebsite());
            stmt.setString(fieldsPos++, company.getName());
            stmt.setString(fieldsPos++, company.getCatchPhrase());
            stmt.setString(fieldsPos, company.getBs());
        } catch (SQLException e) {
            System.out.println("SQLException in DBWorker.stmtUserSetter()");
        }
    }

    private User createUserFromCLI() {
        String className = User.class.getSimpleName();
        return new User.Builder().name(supporter.readClassData("name", className))
                .username(supporter.readClassData("user name", className))
                .email(supporter.readClassData("email", className))
                .street(supporter.readClassData("street", className))
                .suite(supporter.readClassData("suite", className))
                .city(supporter.readClassData("city", className))
                .zipcode(supporter.readClassData("zipcode", className))
                .lat(supporter.readClassData("lat", className))
                .lng(supporter.readClassData("lng", className))
                .phone(supporter.readClassData("phone", className))
                .website(supporter.readClassData("website", className))
                .companyName(supporter.readClassData("company name", className))
                .catchPhrase(supporter.readClassData("catch phrase", className))
                .bs(supporter.readClassData("bs", className))
                .build();
    }

    public void addPost() {
        Post post = createPostFromCLI();
        post.setId(getLastId("SELECT PostId FROM Posts"));
        try (Connection connection = DriverManager.getConnection(dbUrl);
             PreparedStatement insert = connection.prepareStatement(
                     "INSERT INTO Posts (PostId,UserId,Title,Body) VALUES (?,?,?,?)")) {
            stmtPostSetter(insert, post, 1, 2);
            insert.execute();
        } catch (SQLException e) {
            System.out.println("SQLException in DBWorker.addPost()");
            e.printStackTrace();
        }
    }

    public void addPosts(ArrayList<Post> posts) {
        try (Connection connection = DriverManager.getConnection(dbUrl);
             PreparedStatement insert = connection.prepareStatement(
                     "INSERT INTO Posts (PostId,UserId,Title,Body) VALUES (?,?,?,?)")) {
            connection.setAutoCommit(false);
            for (Post post : posts) {
                stmtPostSetter(insert, post, 1, 2);
                insert.addBatch();
            }
            insert.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            System.out.println("SQLException in DBWorker.addPosts()");
        }
    }

    public void removePost(int postId) {
        try (Connection connection = DriverManager.getConnection(dbUrl);
             PreparedStatement insert = connection.prepareStatement("DELETE FROM Posts WHERE PostId = ?")) {
            insert.setInt(1, postId);
            insert.execute();
        } catch (SQLException e) {
            System.out.println("SQLException in DBWorker.removePost()");
        }
    }

    public void updatePost(int postId) {
        String value;
        Post post = getPost(postId);
        String className = post.getClass().getSimpleName();
        Post updPost = new Post.Builder().id(postId).userId(post.getUserId())
                .title((value = supporter.readClassData("title", className)).equals("") ? post.getTitle() : value)
                .body((value = supporter.readClassData("body", className)).equals("") ? post.getBody() : value)
                .build();
        try (Connection connection = DriverManager.getConnection(dbUrl);
             PreparedStatement update = connection.prepareStatement(
                     "UPDATE Posts SET UserId = ?, Title = ?, Body = ? WHERE PostId = ?")) {
            stmtPostSetter(update, updPost, 4, 1);
            update.execute();
        } catch (SQLException e) {
            System.out.println("SQLException in DBWorker.updatePost()");
            e.printStackTrace();
        }
    }

    private void stmtPostSetter(PreparedStatement stmt, Post post, int idPos, int fieldsPos) {
        try {
            stmt.setInt(idPos, post.getId());
            stmt.setInt(fieldsPos++, post.getUserId());
            stmt.setString(fieldsPos++, post.getTitle());
            stmt.setString(fieldsPos, post.getBody());
        } catch (SQLException e) {
            System.out.println("SQLException in DBWorker.stmtPostSetter()");
        }
    }

    private Post createPostFromCLI() {
        String className = Post.class.getSimpleName();
        return new Post.Builder().userId(Integer.parseInt(supporter.readClassData("user id", className)))
                .title(supporter.readClassData("title", className))
                .body(supporter.readClassData("body", className)).build();
    }

    public void addComment() {
        Comment comment = createCommentFromCLI();
        comment.setId(getLastId("SELECT CommentId FROM Comments"));
        try (Connection connection = DriverManager.getConnection(dbUrl);
             PreparedStatement insert = connection.prepareStatement(
                     "INSERT INTO Comments (CommentId, PostId, Name, Email, Body) VALUES (?, ?, ?, ?, ?)")) {
            stmtCommentSetter(insert, comment, 1, 2);
            insert.execute();
        } catch (SQLException e) {
            System.out.println("SQLException in DBWorker.addComment()");
        }
    }

    public void addComments(ArrayList<Comment> comments) {
        try (Connection connection = DriverManager.getConnection(dbUrl);
             PreparedStatement insert = connection.prepareStatement(
                     "INSERT INTO Comments (CommentId,PostId,Name,Email,Body) " +
                             "VALUES (?,?,?,?,?)")) {
            connection.setAutoCommit(false);
            for (Comment comment : comments) {
                stmtCommentSetter(insert, comment, 1, 2);
                insert.addBatch();
            }
            insert.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            System.out.println("SQLException in DBWorker.addComments()");
        }
    }

    public void removeComment(int commentId) {
        try (Connection connection = DriverManager.getConnection(dbUrl);
             PreparedStatement insert = connection.prepareStatement("DELETE FROM Comments WHERE CommentId = ?")) {
            insert.setInt(1, commentId);
            insert.execute();
        } catch (SQLException e) {
            System.out.println("SQLException in DBWorker.removeComment()");
        }
    }

    public void updateComment(int commentId) {
        String value;
        Comment comment = getComment(commentId);
        String className = comment.getClass().getSimpleName();
        Comment updComment = new Comment.Builder().id(commentId).postId(comment.getPostId())
                .name((value = supporter.readClassData("name", className)).equals("") ? comment.getName() : value)
                .email((value = supporter.readClassData("email", className)).equals("") ? comment.getEmail() : value)
                .body((value = supporter.readClassData("body", className)).equals("") ? comment.getBody() : value)
                .build();
        try (Connection connection = DriverManager.getConnection(dbUrl);
             PreparedStatement update = connection.prepareStatement(
                     "UPDATE Comments SET PostId = ?, Name = ?, Email = ?, Body = ? WHERE CommentId = ?")) {
            stmtCommentSetter(update, updComment, 5, 1 );
            update.execute();
        } catch (SQLException e) {
            System.out.println("SQLException in DBWorker.updateComment()");
        }
    }

    private void stmtCommentSetter(PreparedStatement stmt, Comment comment, int idPos, int fieldsPos) {
        try {
            stmt.setInt(idPos, comment.getId());
            stmt.setInt(fieldsPos++, comment.getPostId());
            stmt.setString(fieldsPos++, comment.getName());
            stmt.setString(fieldsPos++, comment.getEmail());
            stmt.setString(fieldsPos, comment.getBody());
        } catch (SQLException e) {
            System.out.println("SQLException in DBWorker.stmtCommentSetter()");
        }
    }

    private Comment createCommentFromCLI() {
        String className = Comment.class.getSimpleName();
        return new Comment.Builder().postId(Integer.parseInt(supporter.readClassData("post id", className)))
                .name(supporter.readClassData("name", className))
                .email(supporter.readClassData("email", className))
                .body(supporter.readClassData("body", className)).build();
    }

    public void addAlbum() {
        Album album = createAlbumFromCLI();
        album.setId(getLastId("SELECT AlbumId FROM Albums"));
        try (Connection connection = DriverManager.getConnection(dbUrl);
             PreparedStatement insert = connection.prepareStatement(
                     "INSERT INTO Albums (AlbumId, UserId, Title) VALUES (?, ?, ?)")) {
            stmtAlbumSetter(insert, album, 1, 2);
            insert.execute();
        } catch (SQLException e) {
            System.out.println("SQLException in DBWorker.addAlbum()");
        }
    }

    public void addAlbums(ArrayList<Album> albums) {
        try (Connection connection = DriverManager.getConnection(dbUrl);
             PreparedStatement insert = connection.prepareStatement(
                     "INSERT INTO Albums (AlbumId,UserId,Title) VALUES (?,?,?)")) {
            connection.setAutoCommit(false);
            for (Album album : albums) {
                stmtAlbumSetter(insert, album, 1, 2);
                insert.addBatch();
            }
            insert.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            System.out.println("SQLException in DBWorker.addAlbums()");
        }
    }

    public void removeAlbum(int albumId) {
        try (Connection connection = DriverManager.getConnection(dbUrl);
             PreparedStatement insert = connection.prepareStatement("DELETE FROM Albums WHERE AlbumId = ?")) {
            insert.setInt(1, albumId);
            insert.execute();
        } catch (SQLException e) {
            System.out.println("SQLException in DBWorker.removeAlbum()");
        }
    }

    public void updateAlbum(int albumId) {
        String value;
        Album album = getAlbum(albumId);
        String className = album.getClass().getSimpleName();
        Album updAlbum = new Album.Builder().id(albumId).userId(album.getUserId())
                .title((value = supporter.readClassData("title", className)).equals("") ? album.getTitle() : value)
                .build();
        try (Connection connection = DriverManager.getConnection(dbUrl);
             PreparedStatement update = connection.prepareStatement(
                     "UPDATE Albums SET UserId = ?, Title = ? WHERE AlbumId = ?")) {
            stmtAlbumSetter(update, updAlbum, 3, 1);
            update.execute();
        } catch (SQLException e) {
            System.out.println("SQLException in DBWorker.updateAlbum()");
        }
    }

    private void stmtAlbumSetter(PreparedStatement stmt, Album album, int idPos, int fieldsPos) {
        try {
            stmt.setInt(idPos, album.getId());
            stmt.setInt(fieldsPos++, album.getUserId());
            stmt.setString(fieldsPos, album.getTitle());
        } catch (SQLException e) {
            System.out.println("SQLException in DBWorker.stmtAlbumSetter()");
        }
    }

    private Album createAlbumFromCLI() {
        String className = Album.class.getSimpleName();
        return new Album.Builder().userId(Integer.parseInt(supporter.readClassData("user id", className)))
                .title(supporter.readClassData("title", className)).build();
    }

    public void addPhoto() {
        Photo photo = createPhotoFromCLI();
        photo.setId(getLastId("SELECT PhotoId FROM Photos"));
        try (Connection connection = DriverManager.getConnection(dbUrl);
             PreparedStatement insert = connection.prepareStatement(
                     "INSERT INTO Photos (PhotoId, AlbumId, Title, URL, ThumbnailURL) VALUES (?, ?, ?, ?, ?)")) {
            stmtPhotoSetter(insert, photo, 1, 2);
            insert.execute();
        } catch (SQLException e) {
            System.out.println("SQLException in DBWorker.addPhoto()");
        }
    }

    public void addPhotos(ArrayList<Photo> photos) {
        try (Connection connection = DriverManager.getConnection(dbUrl);
             PreparedStatement insert = connection.prepareStatement(
                     "INSERT INTO 'Photos' (PhotoId, AlbumId, Title, URL, ThumbnailURL) VALUES (?, ?, ?, ?, ?)")) {
            connection.setAutoCommit(false);
            for (Photo photo : photos) {
                stmtPhotoSetter(insert, photo, 1, 2);
                insert.addBatch();
            }
            insert.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            System.out.println("SQLException in DBWorker.addPhotos()");
        }
    }

    public void removePhoto(int photoId) {
        try (Connection connection = DriverManager.getConnection(dbUrl);
             PreparedStatement delete = connection.prepareStatement("DELETE FROM Photos WHERE PhotoId = ?")) {
            delete.setInt(1, photoId);
            delete.execute();
        } catch (SQLException e) {
            System.out.println("SQLException in DBWorker.removePhoto()");
        }
    }

    public void updatePhoto(int photoId) {
        String value;
        Photo photo = getPhoto(photoId);
        String className = photo.getClass().getSimpleName();
        Photo updPhoto = new Photo.Builder().id(photoId).albumId(photo.getAlbumId())
                .title((value = supporter.readClassData("title", className)).equals("") ? photo.getTitle() : value)
                .url((value = supporter.readClassData("url", className)).equals("") ? photo.getUrl() : value)
                .thumbnailUrl((value = supporter.readClassData("thumbnail url", className)).equals("") ? photo.getThumbnailUrl() : value)
                .build();
        try (Connection connection = DriverManager.getConnection(dbUrl);
             PreparedStatement update = connection.prepareStatement(
                     "UPDATE Photos SET AlbumId = ?, Title = ?, URL = ?, ThumbnailURL = ? WHERE PhotoId = ?")) {
            stmtPhotoSetter(update, updPhoto, 5, 1);
            update.execute();
        } catch (SQLException e) {
            System.out.println("SQLException in DBWorker.updateAlbum()");
        }
    }

    private void stmtPhotoSetter(PreparedStatement stmt, Photo photo, int idPos, int fieldsPos) {
        try {
            stmt.setInt(idPos, photo.getId());
            stmt.setInt(fieldsPos++, photo.getAlbumId());
            stmt.setString(fieldsPos++, photo.getTitle());
            stmt.setString(fieldsPos++, photo.getUrl());
            stmt.setString(fieldsPos, photo.getThumbnailUrl());
        } catch (SQLException e) {
            System.out.println("SQLException in DBWorker.stmtAlbumSetter()");
        }
    }

    private Photo createPhotoFromCLI() {
        String className = Photo.class.getSimpleName();
        return new Photo.Builder().albumId(Integer.parseInt(supporter.readClassData("album id", className)))
                .title(supporter.readClassData("title", className))
                .url(supporter.readClassData("url", className))
                .thumbnailUrl(supporter.readClassData("thumbnail url", className)).build();
    }

    public User getUser(String userName) {
        return getUser(getUserId(userName));
    }

    public User getUser(int userId) {
        User user = null;
        try (Connection connection = DriverManager.getConnection(dbUrl);
             PreparedStatement select = connection.prepareStatement("SELECT * FROM Users WHERE UserId = ?")) {
            select.setInt(1, userId);
            ResultSet set = select.executeQuery();
            while (set.next()) {
                user = createUser(set);
            }
        } catch (SQLException e) {
            System.out.println("SQLException in DBWorker.getUser()");
        }
        return user;
    }

    public Address getAddress(String name) {
        int userId = getUserId(name);
        return getAddress(userId);
    }

    public Address getAddress(int userId) {
        return getUser(userId).getAddress();

    }

    public Post getPost(int postId) {
        Post post = null;
        try (Connection connection = DriverManager.getConnection(dbUrl);
             PreparedStatement select = connection.prepareStatement("SELECT * FROM Posts WHERE PostId = ?")) {
            select.setInt(1, postId);
            ResultSet set = select.executeQuery();
            while (set.next()) {
                post = createPost(set);
            }
        } catch (SQLException e) {
            System.out.println("SQLException in DBWorker.getPost()");
        }
        return post;
    }

    public Comment getComment(int commentId) {
        Comment comment = null;
        try (Connection connection = DriverManager.getConnection(dbUrl);
             PreparedStatement select = connection.prepareStatement("SELECT * FROM Comments WHERE CommentId = ?")) {
            select.setInt(1, commentId);
            ResultSet set = select.executeQuery();
            while (set.next()) {
                comment = createComment(set);
            }
        } catch (SQLException e) {
            System.out.println("SQLException in DBWorker.getComment()");
        }
        return comment;
    }

    public Album getAlbum(int albumId) {
        Album album = null;
        try (Connection connection = DriverManager.getConnection(dbUrl);
             PreparedStatement select = connection.prepareStatement("SELECT * FROM Albums WHERE AlbumId = ?")) {
            select.setInt(1, albumId);
            ResultSet set = select.executeQuery();
            while (set.next()) {
                album = createAlbum(set);
            }
        } catch (SQLException e) {
            System.out.println("SQLException in DBWorker.getAlbum()");
        }
        return album;
    }

    public Photo getPhoto(int photoId) {
        Photo photo = null;
        try (Connection connection = DriverManager.getConnection(dbUrl);
             PreparedStatement select = connection.prepareStatement("SELECT * FROM Photos WHERE PhotoId = ?")) {
            select.setInt(1, photoId);
            ResultSet set = select.executeQuery();
            while (set.next()) {
                photo = createPhoto(set);
            }
        } catch (SQLException e) {
            System.out.println("SQLException in DBWorker.getPhoto()");
        }
        return photo;
    }

    public ArrayList<Comment> selectComments(String userName) {
        ArrayList<Comment> comments = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(dbUrl);
             PreparedStatement select = connection.prepareStatement("SELECT * FROM Comments WHERE Name = ?")) {
            select.setString(1, userName);
            ResultSet set = select.executeQuery();
            while (set.next()) {
                comments.add(createComment(set));
            }
        } catch (SQLException e) {
            System.out.println("SQLException in DBWorker.selectComments()");
        }
        return comments;
    }

    public ArrayList<Comment> selectComments(int postId) {
        ArrayList<Comment> comments = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(dbUrl);
             PreparedStatement select = connection.prepareStatement("SELECT * FROM Comments WHERE PostId = ?")) {
            select.setInt(1, postId);
            ResultSet set = select.executeQuery();
            while (set.next()) {
                comments.add(createComment(set));
            }
        } catch (SQLException e) {
            System.out.println("SQLException in DBWorker.selectComments()");
        }
        return comments;
    }

    public ArrayList<Post> selectPosts(String userName) {
        return selectPosts(getUserId(userName));
    }

    public ArrayList<Post> selectPosts(int userId) {
        ArrayList<Post> posts = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(dbUrl);
             PreparedStatement select = connection.prepareStatement("SELECT * FROM Posts WHERE UserId = ?")) {
            select.setInt(1, userId);
            ResultSet set = select.executeQuery();
            while (set.next()) {
                posts.add(createPost(set));
            }
        } catch (SQLException e) {
            System.out.println("SQLException in DBWorker.selectPosts()");
        }
        return posts;
    }

    public ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(dbUrl);
             PreparedStatement select = connection.prepareStatement("SELECT * FROM Users")) {
            ResultSet set = select.executeQuery();
            while (set.next()) {
                users.add(createUser(set));
            }
        } catch (SQLException e) {
            System.out.println("SQLException in DBWorker.getUsers()");
        }
        return users;
    }

    public ArrayList<Post> getPosts() {
        ArrayList<Post> posts = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(dbUrl);
             PreparedStatement select = connection.prepareStatement("SELECT * FROM Posts")) {
            ResultSet set = select.executeQuery();
            while (set.next()) {
                posts.add(createPost(set));
            }
        } catch (SQLException e) {
            System.out.println("SQLException in DBWorker.getPosts()");
        }
        return posts;
    }

    public ArrayList<Comment> getComments() {
        ArrayList<Comment> comments = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(dbUrl);
             PreparedStatement select = connection.prepareStatement("SELECT * FROM Comments")) {
            ResultSet set = select.executeQuery();
            while (set.next()) {
                Comment comment = new Comment.Builder().id(set.getInt(1)).postId(set.getInt(2))
                        .name(set.getString(3)).email(set.getString(4))
                        .body(set.getString(5)).build();
                comments.add(comment);
            }
        } catch (SQLException e) {
            System.out.println("SQLException in DBWorker.getComments()");
        }
        return comments;
    }

    public ArrayList<Album> getAlbums() {
        ArrayList<Album> albums = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(dbUrl);
             PreparedStatement select = connection.prepareStatement("SELECT * FROM Albums")) {
            ResultSet set = select.executeQuery();
            while (set.next()) {
                Album album = new Album.Builder().id(set.getInt(1)).userId(set.getInt(2))
                        .title(set.getString(3)).build();
                albums.add(album);
            }
        } catch (SQLException e) {
            System.out.println("SQLException in DBWorker.getAlbums()");
        }
        return albums;
    }

    public ArrayList<Photo> getPhotos() {
        ArrayList<Photo> photos = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(dbUrl);
             PreparedStatement select = connection.prepareStatement("SELECT * FROM Photos")) {
            ResultSet set = select.executeQuery();
            while (set.next()) {
                Photo photo = new Photo.Builder().id(set.getInt(1)).albumId(set.getInt(2))
                        .title(set.getString(3)).url(set.getString(4))
                        .thumbnailUrl(set.getString(5)).build();
                photos.add(photo);
            }
        } catch (SQLException e) {
            System.out.println("SQLException in DBWorker.getPhotos()");
        }
        return photos;
    }

    private int getUserId(String name) {
        int userId = -1;
        try (Connection connection = DriverManager.getConnection(dbUrl);
             PreparedStatement select = connection.prepareStatement("SELECT UserId, Name FROM Users WHERE Name = ?")) {
            select.setString(1, name);
            ResultSet set = select.executeQuery();
            if (set.next()) {
                userId = set.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException in DBWorker.getUserId()");
        }
        return userId;
    }

    private User createUser(ResultSet set) {
        User user = null;
        try {
            user = new User.Builder().id(set.getInt(1)).name(set.getString(2))
                    .username(set.getString(3)).email(set.getString(4))
                    .street(set.getString(5)).suite(set.getString(6))
                    .city(set.getString(7)).zipcode(set.getString(8))
                    .lat(set.getString(9)).lng(set.getString(10))
                    .phone(set.getString(11)).website(set.getString(12))
                    .companyName(set.getString(13)).catchPhrase(set.getString(14))
                    .bs(set.getString(15)).build();
        } catch (SQLException e) {
            System.out.println("SQLException in DBWorker.createUser");
        }
        return user;
    }

    private Post createPost(ResultSet set) {
        Post post = null;
        try {
            post = new Post.Builder().id(set.getInt(1)).userId(set.getInt(2))
                    .title(set.getString(3)).body(set.getString(4)).build();
        } catch (SQLException e) {
            System.out.println("SQLException in DBWorker.createPost()");
        }
        return post;
    }

    private Comment createComment(ResultSet set) {
        Comment comment = null;
        try {
            comment = new Comment.Builder().id(set.getInt(1)).postId(set.getInt(2))
                    .name(set.getString(3)).email(set.getString(4))
                    .body(set.getString(5)).build();
        } catch (SQLException e) {
            System.out.println("SQLException in DBWorker.createComment()");
        }
        return comment;
    }

    private Album createAlbum(ResultSet set) {
        Album album = null;
        try {
            album = new Album.Builder().id(set.getInt(1)).userId(set.getInt(2))
                    .title(set.getString(3)).build();
        } catch (SQLException e) {
            System.out.println("SQLException in DBWorker.createAlbum()");
        }
        return album;
    }

    private Photo createPhoto(ResultSet set) {
        Photo photo = null;
        try {
            photo = new Photo.Builder().id(set.getInt(1)).albumId(set.getInt(2))
                    .title(set.getString(3)).url(set.getString(4))
                    .thumbnailUrl(set.getString(5)).build();
        } catch (SQLException e) {
            System.out.println("SQLException in DBWorker.createPhoto()");
        }
        return photo;
    }

    private int getLastId(String sql) {
        int id = 0;
        try (Connection connection = DriverManager.getConnection(dbUrl);
             PreparedStatement select = connection.prepareStatement(sql)) {
            ResultSet set = select.executeQuery();
            while (set.next()) {
                id = set.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException in DBWorker.getLastId()");
        }
        return ++id;
    }

}
