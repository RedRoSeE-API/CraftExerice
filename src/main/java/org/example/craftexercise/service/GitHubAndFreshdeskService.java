package org.example.craftexercise.service;

import org.example.craftexercise.model.FreshdeskModel;

import java.net.http.HttpResponse;

public interface GitHubAndFreshdeskService {

    public HttpResponse<String> gitHubGetUserWithToken(String GitHubToken, String username);
    public HttpResponse<String> freshDeskGetUserWithToken(String FreshdeskToken, String freshdeskSubdomain);
    public HttpResponse<String> freshDeskAddUserWithToken(String FreshdeskToken, String freshdeskSubdomain, String jsonPayload);
    public String DataMapFromFreshdeskModelToString(FreshdeskModel model);
    public FreshdeskModel DataMapFromHTTPResponseToFreshdeskModel(HttpResponse<String> jsonResponse);


}
