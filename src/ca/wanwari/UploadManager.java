package ca.wanwari;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class UploadManager {

    private final HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
    private final String uploadUrl = "https://freeimage.host/api/1/upload";
    private final String apiKey = ""; //place private API key here

    UploadManager() {

    }

    String upload(String imgInBase64) throws IOException, InterruptedException {

        String uploadUrlResponse = "error";

        Map<Object, Object> formData = new HashMap<>();
        formData.put("key", apiKey);
        formData.put("source", imgInBase64);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .POST(buildFormDataFromMap(formData))
                .uri(URI.create(uploadUrl))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();

        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            uploadUrlResponse = response.body();
            uploadUrlResponse = uploadUrlResponse.substring(uploadUrlResponse.indexOf("\"image\":\"") + 9);
            uploadUrlResponse = uploadUrlResponse.substring(0, uploadUrlResponse.indexOf('"'));
        }

        return formatUrl(uploadUrlResponse);
    }

    private static String formatUrl(String originalUrl) {
        String fixedUrl = "";
        if (originalUrl.contains("\\")) {
            for (int i = 0; i < originalUrl.length(); i++) {
                if (originalUrl.charAt(i) != '\\')
                    fixedUrl += originalUrl.charAt(i);
            }
        } else {
            return originalUrl;
        }
        return fixedUrl;
    }

    private static HttpRequest.BodyPublisher buildFormDataFromMap(Map<Object, Object> data) {
        var builder = new StringBuilder();
        for (Map.Entry<Object, Object> entry : data.entrySet()) {
            if (builder.length() > 0) {
                builder.append("&");
            }
            builder.append(URLEncoder.encode(entry.getKey().toString(), StandardCharsets.UTF_8));
            builder.append("=");
            builder.append(URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8));
        }
        return HttpRequest.BodyPublishers.ofString(builder.toString());
    }
}
