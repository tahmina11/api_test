import org.json.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Api_interview {
    public static void main(String[] args) {
        getCapital("col");
    }

    public static String getCapital(String code) {
        String capital=null;
        String line = null;
        String res=null;
        StringBuffer result = new StringBuffer();
        HttpClient client = HttpClientBuilder.create().build();
        String endPoint = "https://restcountries.com/v3.1/alpha/"+code;
        HttpGet get = new HttpGet(endPoint);

        try {
            HttpResponse response = client.execute(get);
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            res = result.toString();
            System.out.println(res);
            JSONArray array = new JSONArray(res);

            for(int i=0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);

                capital= String.valueOf(object.getJSONArray("capital"));

                System.out.println(capital);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return capital;

    }
}