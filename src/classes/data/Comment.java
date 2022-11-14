package classes.data;

public class Comment {
    private int id;
    private int postId;
    private String name;
    private String email;
    private String body;

    public Comment() {
    }

    public Comment(int id, int postId, String name, String email, String body) {
        this.id = id;
        this.postId = postId;
        this.name = name;
        this.email = email;
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return String.format("Comment { id = %d, post Id = %d, \nname = %s, email = %s, \nbody = %s }\n",
                id, postId, name, email, body);
    }

    public static class Builder {
        private int id;
        private int postId;
        private String name;
        private String email;
        private String body;

        public Builder() {
        }

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder postId(int postId) {
            this.postId = postId;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder body(String body) {
            this.body = body;
            return this;
        }

        public Comment build() {
            return new Comment(this);
        }

    }

    private Comment(Builder builder) {
        id = builder.id;
        postId = builder.postId;
        name = builder.name;
        email = builder.email;
        body = builder.body;
    }

}
