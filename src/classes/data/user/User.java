package classes.data.user;

import java.util.ArrayList;

public class User {
    private int id;
    private String name;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;

    public User() {
    }

    public User(int id, String name, String username, String email, Address address, String phone, String website, Company company) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.website = website;
        this.company = company;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return String.format("User { id = %d, name = %s, user name = %s, \n%s, \nphone = %s, website = %s, \n%s }\n",
                id, name, username, address, phone, website, company);
    }

    public static class Builder {
        private int id;
        private String name;
        private String username;
        private String email;
        private Address.Builder addressBuilder;
        private String phone;
        private String website;
        private Company.Builder companyBuilder;

        public Builder() {
            addressBuilder = new Address.Builder();
            companyBuilder = new Company.Builder();
        }

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder street(String street) {
            addressBuilder = addressBuilder.street(street);
            return this;
        }

        public Builder suite(String suite) {
            addressBuilder = addressBuilder.suite(suite);
            return this;
        }

        public Builder city(String city) {
            addressBuilder = addressBuilder.city(city);
            return this;
        }

        public Builder zipcode(String zipcode) {
            addressBuilder = addressBuilder.zipcode(zipcode);
            return this;
        }

        public Builder lat(String lat) {
            addressBuilder = addressBuilder.lat(lat);
            return this;
        }

        public Builder lng(String lng) {
            addressBuilder = addressBuilder.lng(lng);
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder website(String website) {
            this.website = website;
            return this;
        }

        public Builder companyName(String name) {
            companyBuilder = companyBuilder.name(name);
            return this;
        }

        public Builder catchPhrase(String catchPhrase) {
            companyBuilder = companyBuilder.catchPhrase(catchPhrase);
            return this;
        }

        public Builder bs(String bs) {
            companyBuilder = companyBuilder.bs(bs);
            return this;
        }

        public User build() {
            return new User(this);
        }

    }

    private User(Builder builder) {
        id = builder.id;
        name = builder.name;
        username = builder.username;
        email = builder.email;
        address = builder.addressBuilder.build();
        phone = builder.phone;
        website = builder.website;
        company = builder.companyBuilder.build();
    }
}
