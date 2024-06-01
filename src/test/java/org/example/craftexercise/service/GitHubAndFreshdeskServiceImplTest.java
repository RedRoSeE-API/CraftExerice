package org.example.craftexercise.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.craftexercise.model.FreshdeskModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class GitHubAndFreshdeskServiceImplTest {

    @InjectMocks
    private GitHubAndFreshdeskServiceImpl service;

    @Mock
    private HttpResponse<String> httpResponse;

    private static final String GITHUB_TOKEN = System.getenv("GITHUB_TOKEN");
    private static final String FRESHDESK_TOKEN = System.getenv("FRESHDESK_TOKEN");
    private static final String GITHUB_USERNAME = System.getenv("GITHUB_USERNAME");
    private static final String FRESHDESK_SUBDOMAIN = System.getenv("FRESHDESK_SUBDOMAIN");
    Random random = new Random();

    MockitoAnnotations MockitoAnnotations;


    @BeforeEach
    public void setUp() {

        MockitoAnnotations.openMocks(this);
    }

    @Test
    public  void gitHubGetUserWithToken() throws Exception{
        // Mock the response
        when(httpResponse.statusCode()).thenReturn(200);

        // Call the method to test
        HttpResponse<String> response = service.gitHubGetUserWithToken(GITHUB_TOKEN, GITHUB_USERNAME);

        // Verify and assert
        assertEquals(200, response.statusCode());
    }

    @Test
    public void freshDeskGetUserWithToken() throws Exception{
        // Mock the response
        when(httpResponse.statusCode()).thenReturn(200);

        // Call the method to test
        HttpResponse<String> response = service.freshDeskGetUserWithToken(FRESHDESK_TOKEN, FRESHDESK_SUBDOMAIN);

        // Verify and assert
        assertEquals(200, response.statusCode());
    }


    @Test
    public void freshDeskAddUserWithToken() throws Exception {
        // Mock the response
        when(httpResponse.statusCode()).thenReturn(201);

        int randomId = random.nextInt(900000) + 100000;
        String jsonPayload = "{\"name\":\"Super Man\",\"email\":\"superman" + randomId + "@freshdesk.com\",\"twitter_id\":\""+ randomId +"\",\"unique_external_id\":\"" + randomId + "\"}";
        // Call the method to test
        HttpResponse<String> response = service.freshDeskAddUserWithToken(FRESHDESK_TOKEN, FRESHDESK_SUBDOMAIN, jsonPayload);

        // Verify and assert
        assertEquals(201, response.statusCode());

    }

    @Test
    public void DataMapFromHTTPResponseToString() throws Exception {

//        HttpResponse<String> response = service.gitHubGetUserWithToken(GITHUB_TOKEN);

        // Sample JSON response
        String jsonResponseString = "{\"name\":\"Super Man\",\"email\":\"superman@freshdesk.com\",\"twitter_id\":\"@superman\",\"id\":\"12345\"}";

        // Mock the response
        when(httpResponse.body()).thenReturn(jsonResponseString);

        // Call the method to test
        String jsonPayload = service.DataMapFromHTTPResponseToString(httpResponse);

        // Parse the result to verify the fields
        ObjectMapper objectMapper = new ObjectMapper();
        FreshdeskModel model = objectMapper.readValue(jsonPayload, FreshdeskModel.class);

        // Verify and assert
        assertEquals("Super Man", model.getName());
        assertEquals("superman@freshdesk.com", model.getEmail());
        assertEquals("@superman", model.getTwitter_id());
        assertEquals("12345", model.getUnique_external_id());
    }


}