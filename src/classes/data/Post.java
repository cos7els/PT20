package classes.data;

public class Post {
    private int id;
    private int userId;
    private String title;
    private String body;

    public Post() {
    }

    public Post(int id, int userId, String title, String body) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return String.format("Post { id = %d, user id = %d, \ntitle = %s, \nbody = %s }\n", id, userId, title, body);
    }

    public static class Builder {
        private int id;
        private int userId;
        private String title;
        private String body;

        public Builder() {}

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder userId(int userId) {
            this.userId = userId;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder body(String body) {
            this.body = body;
            return this;
        }

        public Post build() {
            return new Post(this);
        }

    }

    private Post(Builder builder) {
        id = builder.id;
        userId = builder.userId;
        title = builder.title;
        body = builder.body;
    }

}
