/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.netbeans.saas.flickr;

import java.io.IOException;
import org.netbeans.saas.RestConnection;
import org.netbeans.saas.RestResponse;

/**
 * FlickrPhotoService Service
 *
 * @author david
 */
public class FlickrPhotoService {

    /**
     * Creates a new instance of FlickrPhotoService
     */
    public FlickrPhotoService() {
    }
    
    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (Throwable th) {
        }
    }

    /**
     *
     * @param minDate
     * @param extras
     * @param perPage
     * @param page
     * @return an instance of RestResponse
     */
    public static RestResponse photosRecentlyUpdated(String minDate, String extras, String perPage, String page) throws IOException {
        String method = "flickr.photos.recentlyUpdated";
        FlickrPhotoServiceAuthenticator.login();
        String apiKey = FlickrPhotoServiceAuthenticator.getApiKey();
        String authToken = FlickrPhotoServiceAuthenticator.getAuthToken();
        String apiSig = FlickrPhotoServiceAuthenticator.sign(new String[][]{{"api_key", apiKey}, {"min_date", minDate}, {"extras", extras}, {"per_page", perPage}, {"page", page}, {"method", method}, {"auth_token", authToken}});
        String[][] pathParams = new String[][]{};
        String[][] queryParams = new String[][]{{"api_key", "" + apiKey + ""}, {"min_date", minDate}, {"extras", extras}, {"per_page", perPage}, {"page", page}, {"method", method}, {"auth_token", authToken}, {"api_sig", apiSig}};
        RestConnection conn = new RestConnection("https://api.flickr.com/services/rest", pathParams, queryParams);
        sleep(1000);
        return conn.get(null);
    }
}
