package com.sep.carsharingbusiness.graphQLServices.serviceImpl;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class GraphQLService {

    private static final String GRAPHQL_URI = "http://localhost:5004/graphql?=";
    private static final Gson gson = new Gson();

    public static HttpResponse<String> sendQuery(String query) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(GRAPHQL_URI))
                .timeout(Duration.ofMinutes(2))
                .header("Content-Type", "application/json")
                .method("POST", HttpRequest.BodyPublishers.ofString(
                        query
                ))
                .build();
        return HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
    }

    public static <T> ArrayList<T> createListQuery(String query, String naming) throws IOException, InterruptedException {
        HttpResponse<String> response = sendQuery(query);

        JsonObject obj = gson.fromJson(response.body(), JsonObject.class);
        JsonArray arr = obj.get("data").getAsJsonObject().get(naming).getAsJsonArray();
        return gson.fromJson(arr, new TypeToken<ArrayList<T>>() {}.getType());
    }

    public static <T> T createObjQuery(String query, String naming, Class<T> objType) throws IOException, InterruptedException {
        HttpResponse<String> response = sendQuery(query);

        JsonObject obj = gson.fromJson(response.body(), JsonObject.class);obj = obj.get("data").getAsJsonObject().get(naming).getAsJsonObject();
        return gson.fromJson(obj, objType);
    }

    public static String getQueryFromFile(String fileName, boolean isMutation) throws IOException {
        String path = "src/main/java/com/sep/carsharingbusiness/" + ( isMutation? "mutations/" : "queries/" );
        List<String> strings = ((Files.readAllLines(Path.of(path + fileName))));

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\"query\":\"");
        strings.forEach(stringBuilder::append);
        stringBuilder.append("\"}");

        return stringBuilder.toString();
    }

}
