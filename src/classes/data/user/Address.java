package classes.data.user;

public class Address {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geo geo;

    public Address() {
    }

    public Address(String street, String suite, String city, String zipcode, Geo geo) {
        this.street = street;
        this.suite = suite;
        this.city = city;
        this.zipcode = zipcode;
        this.geo = geo;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public Geo getGeo() {
        return geo;
    }

    public void setGeo(Geo geo) {
        this.geo = geo;
    }

    @Override
    public String toString() {
        return String.format("Address { street = %s, suite = %s, city = %s, zip code = %s, \n%s }",
                street, suite, city, zipcode, geo);
    }

    public static class Builder {
        private String street;
        private String suite;
        private String city;
        private String zipcode;
        private Geo.Builder geoBuilder;

        public Builder() {
            geoBuilder = new Geo.Builder();
        }

        public Builder street(String street) {
            this.street = street;
            return this;
        }

        public Builder suite(String suite) {
            this.suite = suite;
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder zipcode(String zipcode) {
            this.zipcode = zipcode;
            return this;
        }

        public Builder lat(String lat) {
            geoBuilder = geoBuilder.lat(lat);
            return this;
        }

        public Builder lng(String lng) {
            geoBuilder = geoBuilder.lng(lng);
            return this;
        }

        public Address build() {
            return new Address(this);
        }

    }

    private Address (Builder builder) {
        street = builder.street;
        suite = builder.suite;
        city = builder.city;
        zipcode = builder.zipcode;
        geo = builder.geoBuilder.build();
    }

}
