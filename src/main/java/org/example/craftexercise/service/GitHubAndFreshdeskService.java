package org.example.craftexercise.service;

import java.net.http.HttpResponse;

public interface GitHubAndFreshdeskService {

    public HttpResponse<String> gitHubGetUserWithToken(String GitHubToken, String username);
    public HttpResponse<String> freshDeskGetUserWithToken(String FreshdeskToken, String freshdeskSubdomain);
    public HttpResponse<String> freshDeskAddUserWithToken(String FreshdeskToken, String freshdeskSubdomain, String jsonPayload);
    public String DataMapFromHTTPResponseToString(HttpResponse<String> jsonResponse);


}
