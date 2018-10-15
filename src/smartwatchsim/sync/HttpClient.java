/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartwatchsim.sync;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

public class HttpClient {

    /*
    fuentes:
    https://www.mkyong.com/webservices/jax-rs/restfull-java-client-with-java-net-url/
    https://stackoverflow.com/questions/15040504/how-to-easily-convert-a-bufferedreader-to-a-string
     */
    public static String getRequestJson(String connUrl) throws HttpException, IOException {
        URL url = new URL(connUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(2000);
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new HttpException(Integer.toString(conn.getResponseCode()));
        }

        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream()), "UTF-8"));
        String response = br.lines().collect(Collectors.joining());

        conn.disconnect();

        return response;

    }
    
    public static String postRequestJson(String connUrl, HttpCredentials credentials, String json) throws HttpException, IOException {
        URL url = new URL(connUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(2000);
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
	conn.setRequestProperty("Content-Type", "application/json");
        if (credentials != null) {
            conn.setRequestProperty("Authorization", credentials.getAuth());
        }
        
        OutputStream os = conn.getOutputStream();
        os.write(json.getBytes());
        os.flush();
        
        if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new HttpException(String.valueOf(conn.getResponseCode()));
        }
        
        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream()), "UTF-8"));
        String response = br.lines().collect(Collectors.joining());

        conn.disconnect();

        return response;
    }

}