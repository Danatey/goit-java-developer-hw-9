package org.example;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpStatusChecker {

    private static final String BASE_URL = "https://http.cat/";

    public String getStatusImage(int code) {

        String urlStr = BASE_URL + code + ".jpg";

        try {
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                return urlStr;

            } else if (responseCode == HttpURLConnection.HTTP_NOT_FOUND) {
                throw new RuntimeException("No image for HTTP status: " + code);

            } else {
                throw new RuntimeException("Unexpected response code: " + responseCode);
            }

        } catch (IOException e) {
            throw new RuntimeException("Error while connecting to http.cat", e);
        }
    }
}