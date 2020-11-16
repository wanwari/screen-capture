package ca.wanwari;

/*
 * UploadManager.java
 * Author: Wiesa Anwari
 * Manages the uploading of an image to an online online image host
 * Receives a base64 image in the form of a string and uploads it (freeimage.host)
 * If the upload was a success extract the URL from the response and format it into a proper ULR
 */

import java.io.*;
import java.net.*;

public class UploadManager {

    private String uploadResponse;

    UploadManager() {
        uploadResponse = "error";
    }

    String upload(String imgInBase64) {

        try {
            String uploadURL = "https://freeimage.host/api/1/upload";
            String key = "6d207e02198a847aa98d0a2a901485a5";

            URL url = new URL(uploadURL);
            String postString = "key=" + URLEncoder.encode(key, "UTF-8") + "&source=" + URLEncoder.encode(imgInBase64, "UTF-8");
            byte[] postDataBytes = postString.getBytes("UTF-8");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
            conn.setDoOutput(true);
            conn.getOutputStream().write(postDataBytes);

            Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            StringBuilder stringBuilder = new StringBuilder();
            int res;
            while ((res = in.read()) >= 0) {
                stringBuilder.append((char) res);
            }
            uploadResponse = extractURLFromResponse(stringBuilder.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return uploadResponse;
    }

    String extractURLFromResponse(String response) {
        String extractedURL = "";
        extractedURL = response.substring(response.indexOf("image\":\"") + 8);
        extractedURL = extractedURL.substring(0, extractedURL.indexOf("\""));
        return formatUrl((extractedURL));
    }

    private static String formatUrl(String originalUrl) {
        StringBuilder fixedUrl = new StringBuilder();
        if (originalUrl.contains("\\")) {
            for (int i = 0; i < originalUrl.length(); i++) {
                if (originalUrl.charAt(i) != '\\')
                    fixedUrl.append(originalUrl.charAt(i));
            }
        } else {
            return originalUrl;
        }
        return fixedUrl.toString();
    }
}
