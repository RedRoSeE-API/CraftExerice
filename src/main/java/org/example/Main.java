package org.example;

import org.example.craftexercise.model.DBUserInformationModel;
import org.example.craftexercise.model.FreshdeskModel;
import org.example.craftexercise.service.DBService;
import org.example.craftexercise.service.GitHubAndFreshdeskServiceImpl;

import java.net.http.HttpResponse;
import java.util.List;
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
        DBService dbService = new DBService();

        //Get Information From GitHub
        HttpResponse<String> gitHubGetUserWithTokenResponse = GitAndFreshdeskService.gitHubGetUserWithToken(GITHUB_TOKEN, gitHubUsername);

        //Add Contact To Freshdesk
        FreshdeskModel newModel = GitAndFreshdeskService.DataMapFromHTTPResponseToFreshdeskModel(gitHubGetUserWithTokenResponse);
        String jsonPayload = GitAndFreshdeskService.DataMapFromFreshdeskModelToString(newModel);
        HttpResponse<String> freshDeskAddUserWithTokenResponse = GitAndFreshdeskService.freshDeskAddUserWithToken(FRESHDESK_TOKEN, freshdeskSubDomain, jsonPayload);


        if(freshDeskAddUserWithTokenResponse.statusCode() == 201){
            System.out.println("\nDo you want to save a record in the database for the newly created user?");
            System.out.print("Y / N = ");
            String DbSaveStringChoice = sc.nextLine();

            if(DbSaveStringChoice.equals("Y")){
                System.out.println("\n--Saving Data Into Database--");
                //Add Information To Database
                int rows = dbService.addNewUserInformationInDB(newModel);

                //Get Information From Db
                List<DBUserInformationModel> informationList = dbService.getUserInformationFromDB();

                System.out.println("\n--All Saved Information In Database--");
                for (DBUserInformationModel userInfModel: informationList) {
                    System.out.println(userInfModel);
                }
            }else{
                System.out.println("\n--Proceed Without Saving Data Into Database--");
            }
        }else{
            if(freshDeskAddUserWithTokenResponse.statusCode() == 409){
                System.out.println("\n--There is already existing contact with this email address--");
            }else{
                System.out.println("\n--Freshdesk Add Contact Response--");
                System.out.println(freshDeskAddUserWithTokenResponse.body());
            }
        }

        //Get Information From Db
        List<DBUserInformationModel> informationList = dbService.getUserInformationFromDB();

        System.out.println("\n--All Saved Information In Database--");
        for (DBUserInformationModel userInfModel: informationList) {
            System.out.println(userInfModel);
        }



        //Get Information From Freshdesk
        HttpResponse<String> freshDeskGetUserWithTokenResponse = GitAndFreshdeskService.freshDeskGetUserWithToken(FRESHDESK_TOKEN, freshdeskSubDomain);
        System.out.println("\n--All Contacts from FreshDesk--");
        System.out.println(freshDeskGetUserWithTokenResponse.body());

    }
}