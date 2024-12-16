package com.example.trial.controllers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlCheckController {

    private final String IS_SITE_UP = "Site is Up";
    private final String SITE_IS_DOWN = "Site is Down";
    private final String INCORRECT_URL = "URL is incorrect";

    @SuppressWarnings("deprecation")
    @GetMapping("/check")
    public String getStatusMessage(@RequestParam String url) {
        String returnMessage = "";

        try {
            // Create a URL object from the input string
            URL urlObj = new URL(url);

            // Open an HTTP connection to the URL
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            // Check if the response code indicates success (2xx)
            if (conn.getResponseCode() / 100 == 2) {
                returnMessage = IS_SITE_UP;
            } else {
                returnMessage = SITE_IS_DOWN;
            }

        } catch (MalformedURLException e) {
            returnMessage = INCORRECT_URL;
        } catch (IOException e) {
            returnMessage = SITE_IS_DOWN;
        }

        return returnMessage;
    }
}
