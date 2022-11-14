package classes.data;

public class Photo {
    private int id;
    private int albumId;
    private String title;
    private String url;
    private String thumbnailUrl;

    public Photo() {
    }

    public Photo(int id, int albumId, String title, String url, String thumbnailUrl) {
        this.id = id;
        this.albumId = albumId;
        this.title = title;
        this.url = url;
        this.thumbnailUrl = thumbnailUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    @Override
    public String toString() {
        return String.format("Photo { id = %d, album Id = %d, \ntitle = %s, \nurl = %s, \nthumbnail url = %s}",
                id, albumId, title, url, thumbnailUrl);
    }

    public static class Builder {
        private int id;
        private int albumId;
        private String title;
        private String url;
        private String thumbnailUrl;

        public Builder() {
        }

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder albumId(int albumId) {
            this.albumId = albumId;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder thumbnailUrl(String thumbnailUrl) {
            this.thumbnailUrl = thumbnailUrl;
            return this;
        }

        public Photo build() {
            return new Photo(this);
        }

    }

    private Photo(Builder builder) {
        id = builder.id;
        albumId = builder.albumId;
        title = builder.title;
        url = builder.url;
        thumbnailUrl = builder.thumbnailUrl;
    }

}
