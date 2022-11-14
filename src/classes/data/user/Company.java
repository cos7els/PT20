package classes.data.user;

public class Company {
    private String name;
    private String catchPhrase;
    private String bs;

    public Company() {
    }

    public Company(String name, String catchPhrase, String bs) {
        this.name = name;
        this.catchPhrase = catchPhrase;
        this.bs = bs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatchPhrase() {
        return catchPhrase;
    }

    public void setCatchPhrase(String catchPhrase) {
        this.catchPhrase = catchPhrase;
    }

    public String getBs() {
        return bs;
    }

    public void setBs(String bs) {
        this.bs = bs;
    }

    @Override
    public String toString() {
        return String.format("Company { name = %s, catch phrase = %s, bs = %s }", name, catchPhrase, bs);
    }

    public static class Builder {
        private String name;
        private String catchPhrase;
        private String bs;

        public Builder() {}

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder catchPhrase(String catchPhrase) {
            this.catchPhrase = catchPhrase;
            return this;
        }

        public Builder bs(String bs) {
            this.bs = bs;
            return this;
        }

        public Company build() {
            return new Company(this);
        }

    }

    private Company(Builder builder) {
        name = builder.name;
        catchPhrase = builder.catchPhrase;
        bs = builder.bs;
    }

}
