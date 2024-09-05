package pe.edu.vallegrande.service;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import java.io.IOException;


public class GeminiService {

    public static void main(String[] args) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        MediaType mediaType = MediaType.parse("application/json");
        // Crear el JSON para la nueva API
        String jsonBody = "{\n" +
                "  \"contents\": [\n" +
                "    {\n" +
                "      \"role\": \"user\",\n" +
                "      \"parts\": [\n" +
                "        {\n" +
                "          \"text\": \"Capital de Peru\"\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        RequestBody body = RequestBody.create(mediaType, jsonBody);

        Request request = new Request.Builder()
                .url("https://gemini-pro-ai.p.rapidapi.com/")
                .method("POST", body)
                .addHeader("x-rapidapi-key", "11fbdeeb47mshee0af61654f99b9p1ab04djsnfcde7c1d0e48")
                .addHeader("x-rapidapi-host", "gemini-pro-ai.p.rapidapi.com")
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                // Imprime la respuesta formateada
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                Object json = gson.fromJson(responseBody, Object.class);
                System.out.println("Response Code: " + response.code());
                System.out.println("Response Body: ");
                System.out.println(gson.toJson(json));
            } else {
                System.out.println("Request failed with status code: " + response.code());
                System.out.println("Response Body: " + response.body().string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}