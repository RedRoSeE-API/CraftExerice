package org.example.craftexercise.model;

import java.sql.Timestamp;

public class DBUserInformationModel {

    private int id;
    private String name;
    private String email;
    private String twitter_id;
    private String unique_external_id;
    private Timestamp creation_time;

    public DBUserInformationModel() {
    }

    public DBUserInformationModel(int id, String name, String email, String twitter_id, String unique_external_id, Timestamp creation_time) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.twitter_id = twitter_id;
        this.unique_external_id = unique_external_id;
        this.creation_time = creation_time;
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

    public Timestamp getCreation_time() {
        return creation_time;
    }

    public void setCreation_time(Timestamp creation_time) {
        this.creation_time = creation_time;
    }

    @Override
    public String toString() {
        return "DBUserInformationModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", twitter_id='" + twitter_id + '\'' +
                ", unique_external_id='" + unique_external_id + '\'' +
                ", creation_time=" + creation_time +
                '}';
    }
}
