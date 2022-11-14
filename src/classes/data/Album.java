package classes.data;

public class Album {
    private int id;
    private int userId;
    private String title;

    public Album() {
    }

    public Album(int id, int userId, String title) {
        this.id = id;
        this.userId = userId;
        this.title = title;
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

    @Override
    public String toString() {
        return String.format("Album { id = %d, user Id = %d, \ntitle = %s }\n", id, userId, title);
    }

    public static class Builder {
        private int id;
        private int userId;
        private String title;

        public Builder() {
        }

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

        public Album build(){
            return new Album(this);
        }

    }

    private Album(Builder builder) {
        id = builder.id;
        userId = builder.userId;
        title = builder.title;
    }

}
