package org.example;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.*;

import static org.mozilla.javascript.Context.exit;

public class Utils {

    public static String defaultRegistrationNumberSuffix = "9000";
    public static String newRegistrationNumberSuffix = defaultRegistrationNumberSuffix;


    public static void addDelay(int timeInMilli) {
        try {
            Thread.sleep(timeInMilli);
        } catch (Exception e){
            GSLogger.print(" Error-100001 Thread sleep Exception: "+ e.getLocalizedMessage());
        }
    }

    public static void getNewRegistrationPhoneNumber() {
        GSLogger.print(" calling newRegistrationPhoneNumber API ");
        try {
            String serverUrlPath = "https://gs-prod-magic-1.azurewebsites.net/api/v1/automation/new-test-registration-number";
//            String localUrlPath = "http://localhost:5001/api/v1/automation/new-test-registration-number";
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(serverUrlPath))
                    .build();

            HttpResponse<String> httpResponse = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
            GSLogger.print(" newRegistrationPhoneNumber API statusCode : " + httpResponse.statusCode());

            String responseStr = httpResponse.body();
            newRegistrationNumberSuffix = new JSONObject(responseStr).getString("newRegNumber");
            GSLogger.print("  Utils.newRegistrationNumberSuffix : " +  newRegistrationNumberSuffix);
        } catch (Exception e) {
            GSLogger.print(" Error-100013 get New Registration API Exception: "+ e.getLocalizedMessage());
            e.printStackTrace();
        } finally {
            if(newRegistrationNumberSuffix.equals(defaultRegistrationNumberSuffix) || newRegistrationNumberSuffix.length() != 4) {
                GSLogger.print("\n\n  ------ Error-100021 Failed to get New Registration Num from API: "+ newRegistrationNumberSuffix+ " . Terminating test suite ------ \n\n");
                System.exit(-1001);
            }
        }
    }
}
