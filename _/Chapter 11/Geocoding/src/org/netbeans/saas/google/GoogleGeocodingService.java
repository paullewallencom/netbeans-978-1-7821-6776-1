/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.netbeans.saas.google;

import java.io.IOException;
import org.netbeans.saas.RestConnection;
import org.netbeans.saas.RestResponse;

/**
 * GoogleGeocodingService Service
 *
 * @author david
 */
public class GoogleGeocodingService {

    /**
     * Creates a new instance of GoogleGeocodingService
     */
    public GoogleGeocodingService() {
    }
    
    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (Throwable th) {
        }
    }

    /**
     *
     * @param q
     * @param output
     * @return an instance of RestResponse
     */
public static RestResponse geocode(String q, String output) throws IOException {
        String apiKey = GoogleGeocodingServiceAuthenticator.getApiKey();
        String[][] pathParams = new String[][]{};
        String[][] queryParams = new String[][]{{"address", q}, {"key", "" + apiKey + ""}, {"output", output}};
        RestConnection conn = new RestConnection("https://maps.googleapis.com/maps/api/geocode/json", pathParams, queryParams);
        sleep(1000);
        return conn.get(null);
    }
}
