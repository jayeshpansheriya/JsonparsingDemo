package com.example.jayesh.jsondemo;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Jayesh on 20-Jun-17.
 */

public class Parser {

    String json;

    public String getDataJson(String s){
        try {
            URL url=new URL(s);
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            BufferedInputStream stream=new BufferedInputStream(connection.getInputStream());
            BufferedReader reader=new BufferedReader(new InputStreamReader(stream));


            String line;
            StringBuffer buffer=new StringBuffer();

            while ((line=reader.readLine())!=null){
                buffer.append(line);
            }
            json=buffer.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }
}
