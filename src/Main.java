import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main {

    // private static HttpURLConnection connection;

    public static void main(String[] args) throws IOException {
        List<album> albums = new ArrayList<>();
        Gson gson = new Gson();

        BufferedReader reader;
        String line;
        String[] fields;

        StringBuffer responseContent = new StringBuffer();
        URL url = new URL("https://jsonplaceholder.typicode.com/albums");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try {
            // Request Setup
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);

            int status = connection.getResponseCode();
            // System.out.println(status);

            if(status > 299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                System.out.println(responseContent);
                album[] album = gson.fromJson(String.valueOf(responseContent),album[].class);
                for (album al: album) {
                    System.out.println(al.toString());
                }

                System.out.println(album[10].getUserId());
            }
            reader.close();
            connection.disconnect();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


