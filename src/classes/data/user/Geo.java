package classes.data.user;

public class Geo {
    private String lat;
    private String lng;

    public Geo() {
    }

    public Geo(String lat, String lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return String.format("Geo { lat = %s, lng = %s }", lat, lng);
    }

    public static class Builder {
        private String lat;
        private String lng;

        public Builder() {}

        public Builder lat(String lat) {
            this.lat = lat;
            return this;
        }

        public Builder lng(String lng) {
            this.lng = lng;
            return this;
        }

        public Geo build() {
            return new Geo(this);
        }

    }

    private Geo(Builder builder) {
        lat = builder.lat;
        lng = builder.lng;
    }

}
