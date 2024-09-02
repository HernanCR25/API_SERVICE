package pe.edu.vallegrande;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.MediaType;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiDemoApplication {

	public static void main(String[] args) {
		OkHttpClient client = new OkHttpClient().newBuilder()
				.build();

		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, "[\r\n  {\r\n    \"content\": \"Hello! I'm an AI assistant bot based on ChatGPT 3. How may I help you?\",\r\n    \"role\": \"system\"\r\n  },\r\n  {\r\n    \"content\": \"nombre de una cancion relajante\",\r\n    \"role\": \"user\"\r\n  }\r\n]\r\n");

		Request request = new Request.Builder()
				.url("https://chatgpt-api8.p.rapidapi.com/")  // Asegúrate de que esta URL es correcta
				.method("POST", body)
				.addHeader("x-rapidapi-key", "3f280f1c04mshb6c8d6aa0360c31p177732jsn7c273b470a2a")
				.addHeader("x-rapidapi-host", "chatgpt-api8.p.rapidapi.com")
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
