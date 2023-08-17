package org.example;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.*;

public class Utils {

    public static String  newRegistrationNumberSuffix = "9000";

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
            String stagingUrlPath = "https://gs-staging-magic-2.azurewebsites.net/api/v1/automation/new-test-registration-number";
//            String urlPathLocal = "http://localhost:5001/api/v1/automation/fetch-new-registration-number";
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(stagingUrlPath))
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
        }
    }
}
