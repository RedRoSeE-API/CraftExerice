package org.example;

import org.example.craftexercise.service.GitHubAndFreshdeskServiceImpl;

import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        final String GITHUB_TOKEN = System.getenv("GITHUB_TOKEN");
        final String FRESHDESK_TOKEN = System.getenv("FRESHDESK_TOKEN");

        Scanner sc = new Scanner(System.in);

        System.out.print("GitHub Username: ");
        String gitHubUsername = sc.nextLine();
        System.out.print("Freshdesk Subdomain: ");
        String freshdeskSubDomain = sc.nextLine();
        //RedRoSeE-API
        //test7226.freshdesk.com

        //Initialize GitAndFreshdeskService
        GitHubAndFreshdeskServiceImpl GitAndFreshdeskService = new GitHubAndFreshdeskServiceImpl();

        //Get Information From GitHub
        HttpResponse<String> gitHubGetUserWithTokenResponse = GitAndFreshdeskService.gitHubGetUserWithToken(GITHUB_TOKEN, gitHubUsername);
        System.out.println(gitHubGetUserWithTokenResponse.body());

        //Add Contact To Freshdesk
        String jsonPayload = GitAndFreshdeskService.DataMapFromHTTPResponseToString(gitHubGetUserWithTokenResponse);
        HttpResponse<String> res = GitAndFreshdeskService.freshDeskAddUserWithToken(FRESHDESK_TOKEN, freshdeskSubDomain, jsonPayload);

        System.out.println(res);

        //Get Information From Freshdesk
        HttpResponse<String> freshDeskGetUserWithTokenResponse = GitAndFreshdeskService.freshDeskGetUserWithToken(FRESHDESK_TOKEN, freshdeskSubDomain);
        System.out.println(freshDeskGetUserWithTokenResponse.body());

    }
}