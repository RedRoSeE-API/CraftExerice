package org.example.craftexercise.model;

public class FreshdeskModel {

    private String name;
    private String email;
    private String twitter_id;
    private String unique_external_id;

    public FreshdeskModel() {
        this.name = "";
        this.email = "";
        this.twitter_id = "";
        this.unique_external_id = "";
    }



    public FreshdeskModel(String name, String email, String twitter_id, String unique_external_id) {
        this.name = name;
        this.email = email;
        this.twitter_id = twitter_id;
        this.unique_external_id = unique_external_id;
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

    public String getTwitter_id() {
        return twitter_id;
    }

    public void setTwitter_id(String twitter_id) {
        this.twitter_id = twitter_id;
    }

    public String getUnique_external_id() {
        return unique_external_id;
    }

    public void setUnique_external_id(String unique_external_id) {
        this.unique_external_id = unique_external_id;
    }

    @Override
    public String toString() {
        return "FreshdeskModel{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", twitter_id='" + twitter_id + '\'' +
                ", unique_external_id='" + unique_external_id + '\'' +
                '}';
    }
}
