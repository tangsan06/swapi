package com.example.swapi.retrofitFactory;

public class ApiUrl {
    private static final String BASE_URL = "https://swapi.co/api/people/";

    public static String getBaseUrl() {
        return BASE_URL;
    }
}
