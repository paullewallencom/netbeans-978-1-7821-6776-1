/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.netbeans.saas.delicious;

import java.io.IOException;
import org.netbeans.saas.RestConnection;
import org.netbeans.saas.RestResponse;

/**
 * DeliciousBookmarkingService Service
 *
 * @author david
 */
public class DeliciousBookmarkingService {

    /**
     * Creates a new instance of DeliciousBookmarkingService
     */
    public DeliciousBookmarkingService() {
    }
    
    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (Throwable th) {
        }
    }

    /**
     *
     * @param tag
     * @param dt
     * @param url
     * @param hashes
     * @param meta
     * @return an instance of RestResponse
     */
    public static RestResponse getPosts(String tag, String dt, String url, String hashes, String meta) throws IOException {
        DeliciousBookmarkingServiceAuthenticator.login();
        String[][] pathParams = new String[][]{};
        String[][] queryParams = new String[][]{{"tag", tag}, {"dt", dt}, {"url", url}, {"hashes", hashes}, {"meta", meta}};
        RestConnection conn = new RestConnection("http://api.del.icio.us/v1/posts/get", pathParams, queryParams);
        sleep(1000);
        return conn.get(null);
    }

    /**
     *
     * @param url
     * @param description
     * @param extended
     * @param tags
     * @param dt
     * @param replace
     * @param shared
     * @return an instance of RestResponse
     */
    public static RestResponse addPosts(String url, String description, String extended, String tags, String dt, String replace, String shared) throws IOException {
        DeliciousBookmarkingServiceAuthenticator.login();
        String[][] pathParams = new String[][]{};
        String[][] queryParams = new String[][]{{"url", url}, {"description", description}, {"extended", extended}, {"tags", tags}, {"dt", dt}, {"replace", replace}, {"shared", shared}};
        RestConnection conn = new RestConnection("http://api.del.icio.us/v1/posts/add", pathParams, queryParams);
        sleep(1000);
        return conn.get(null);
    }
}
