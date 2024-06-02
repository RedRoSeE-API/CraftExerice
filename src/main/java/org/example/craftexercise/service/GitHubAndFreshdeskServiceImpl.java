package org.example.craftexercise.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.craftexercise.model.FreshdeskModel;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.SQLOutput;
import java.util.Base64;

public class GitHubAndFreshdeskServiceImpl implements GitHubAndFreshdeskService {

    @Override
    public HttpResponse<String> gitHubGetUserWithToken(String GitHubToken, String username){
        // GitHub Request Builder
        HttpRequest gitHubGetUserWithTokenRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://api.github.com/users/" + username))
                .header("Accept", "application/vnd.github+json")
                .header("Authorization", "Bearer " + GitHubToken)
                .header("X-GitHub-Api-Version", "2022-11-28")
                .build();
        HttpResponse<String> gitHubGetUserWithTokenResponse = null;

        try {
            gitHubGetUserWithTokenResponse = HttpClient.newHttpClient().send(gitHubGetUserWithTokenRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            System.out.println("GitHubAndFreshdeskServiceImpl\\gitHubGetUserWithToken IOException:");
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("GitHubAndFreshdeskServiceImpl\\gitHubGetUserWithToken InterruptedException:");
            e.printStackTrace();
        }

        return gitHubGetUserWithTokenResponse;
    };

    @Override
    public HttpResponse<String> freshDeskGetUserWithToken(String FreshdeskToken, String freshdeskSubdomain){

        String auth = FreshdeskToken + ":X";
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());

        HttpResponse<String> freshDeskGetUserWithTokenResponse = null;

        try {
            // FreshDesk Request Builder
            HttpRequest freshDeskGetUserWithTokenRequest = HttpRequest.newBuilder()
                    .uri(URI.create("https://"+ freshdeskSubdomain +"/api/v2/contacts"))
                    .header("Authorization", "Basic " + encodedAuth)
                    .GET()
                    .build();
            freshDeskGetUserWithTokenResponse = HttpClient.newHttpClient().send(freshDeskGetUserWithTokenRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            System.out.println("\n!! GitHubAndFreshdeskServiceImpl\\freshDeskGetUserWithToken IOException !!");
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("\n!! GitHubAndFreshdeskServiceImpl\\freshDeskGetUserWithToken InterruptedException !!");
            e.printStackTrace();
        }

        return freshDeskGetUserWithTokenResponse;
    };

    @Override
    public HttpResponse<String> freshDeskAddUserWithToken(String FreshdeskToken, String freshdeskSubdomain, String jsonPayload){

        String auth = FreshdeskToken + ":X";
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());

        // FreshDesk Client And Request Builder
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://"+ freshdeskSubdomain +"/api/v2/contacts"))
                .header("Authorization", "Basic " + encodedAuth)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
                .build();
        HttpResponse<String> response = null;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());

        } catch (IOException e) {
            System.out.println("\n!! GitHubAndFreshdeskServiceImpl\\freshDeskAddUserWithToken IOException !!");
            e.printStackTrace();
        }catch (InterruptedException e){
            System.out.println("\n!! GitHubAndFreshdeskServiceImpl\\freshDeskAddUserWithToken InterruptedException !!");
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public FreshdeskModel DataMapFromHTTPResponseToFreshdeskModel(HttpResponse<String> jsonResponse){

        ObjectMapper objectMapper = new ObjectMapper();
        FreshdeskModel model = new FreshdeskModel();

        try {
            JsonNode rootNode = objectMapper.readTree(jsonResponse.body());
            model.setName(rootNode.path("name").asText());
            model.setEmail(rootNode.path("email").asText());
            model.setTwitter_id(rootNode.path("twitter_id").asText());
            model.setUnique_external_id(rootNode.path("id").asText());
        } catch (IOException e) {
            System.out.println("\n!! GitHubAndFreshdeskServiceImpl\\DataMapFromHTTPResponseToFreshdeskModel IOException !!");
            e.printStackTrace();
        }

        return model;
    }

    @Override
    public String DataMapFromFreshdeskModelToString(FreshdeskModel model){

        ObjectMapper objectMapper = new ObjectMapper();

        String jsonPayload = "";

        try {
            jsonPayload = objectMapper.writeValueAsString(model);
        } catch (IOException e) {
            System.out.println("\n!! GitHubAndFreshdeskServiceImpl\\DataMapFromFreshdeskModelToString IOException !!");
            e.printStackTrace();
        }

        return jsonPayload;
    }



}
