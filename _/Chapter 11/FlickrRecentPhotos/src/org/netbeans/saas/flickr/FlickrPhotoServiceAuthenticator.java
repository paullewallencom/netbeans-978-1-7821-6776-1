/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.netbeans.saas.flickr;

import flickr.photoservice.flickrresponse.Rsp;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.netbeans.saas.RestConnection;
import org.netbeans.saas.RestResponse;

/**
 *
 * @author david
 */
public class FlickrPhotoServiceAuthenticator {
    
    private static String apiKey;
    private static String secret;
    private static String authToken;
    private static String authUser;
    private static boolean readOnly = false;
    private static final String PROP_FILE = FlickrPhotoServiceAuthenticator.class.getSimpleName().toLowerCase() + ".properties";
    
    static {
        try {
            Properties props = new Properties();
            props.load(FlickrPhotoServiceAuthenticator.class.getResourceAsStream(PROP_FILE));
            apiKey = props.getProperty("api_key");
            secret = props.getProperty("secret");
        } catch (IOException ex) {
            Logger.getLogger(FlickrPhotoServiceAuthenticator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static String getApiKey() throws IOException {
        if (apiKey == null || apiKey.length() == 0) {
            throw new IOException("Please specify your api key and secret in the " + PROP_FILE + " file.");
        }
        return apiKey;
    }
    
    private static String getSecret() throws IOException {
        if (secret == null || secret.length() == 0) {
            throw new IOException("Please specify your api key and secret in the " + PROP_FILE + " file.");
        }
        return secret;
    }
    
    public static String getAuthToken() throws IOException {
        if (authToken == null || authToken.length() == 0) {
            throw new IOException("Failed to get a valid authentication token.");
        }
        return authToken;
    }
    
    public static String getAuthUser() throws IOException {
        if (authUser == null || authUser.length() == 0) {
            throw new IOException("Failed to get a valid authentication token.");
        }
        return authUser;
    }
    
    public static void login() throws IOException {
        if (authToken == null) {
            String frob = getFrob();
            String apiKey = getApiKey();
            String method = "flickr.auth.getToken";
            String apiSig = sign(
                    new String[][]{
                        {"method", method},
                        {"frob", frob},
                        {"api_key", apiKey},});
            
            RestConnection conn = new RestConnection(
                    "https://api.flickr.com/services/rest/",
                    new String[][]{
                        {"method", method},
                        {"api_key", apiKey},
                        {"api_sig", apiSig},
                        {"frob", frob}
                    });
            RestResponse response = conn.get();
            
            try {
                Rsp rsp = response.getDataAsObject(Rsp.class);
                authToken = rsp.getAuth().getToken();
                authUser = rsp.getAuth().getUser().getNsid();
            } catch (Exception ex) {
                throw new IOException("Failed to get authentication token: " + response.getDataAsString() + ", cause: " + ex.getMessage());
            }
        }
    }
    
    private static void logout() {
    }
    
    private static String getFrob() throws IOException {
        String frob = null;
        String apiKey = getApiKey();
        String method = "flickr.auth.getFrob";
        
        String apiSig = sign(
                new String[][]{
                    {"method", method},
                    {"api_key", apiKey}
                });
        
        RestConnection conn = new RestConnection(
                "https://api.flickr.com/services/rest/",
                new String[][]{
                    {"method", method},
                    {"api_key", apiKey},
                    {"api_sig", apiSig}
                });
        String result = conn.get().getDataAsString();
        
        try {
            frob = result.substring(result.indexOf("<frob>") + 6, result.indexOf("</frob>"));
        } catch (Exception ex) {
            throw new IOException("Failed to get frob: " + result);
        }
        
        String perms = (readOnly) ? "read" : "delete";
        apiSig = sign(
                new String[][]{
                    {"frob", frob},
                    {"api_key", apiKey},
                    {"perms", perms}
                });
        
        String loginUrl = "https://www.flickr.com/services/auth/?api_key=" + apiKey + "&frob=" + frob + "&perms=" + perms + "&api_sig=" + apiSig;
        
        if (JOptionPane.showInputDialog(null,
                "Please log into your Flickr account using the following URL to authorize this application and click OK after you are done:",
                "Flickr Authorization Dialog",
                JOptionPane.INFORMATION_MESSAGE,
                null,
                null,
                loginUrl) == null) {
            throw new IOException("Authorizatoin declined");
        }
        
        return frob;
    }
    
    public static String sign(String[][] params) throws IOException {
        TreeMap<String, String> map = new TreeMap<String, String>();
        
        for (int i = 0; i < params.length; i++) {
            String key = params[i][0];
            String value = params[i][1];
            
            if (value != null) {
                map.put(key, value);                
            }
        }
        
        String signature = getSecret();
        Set<Map.Entry<String, String>> entrySet = map.entrySet();
        for (Map.Entry<String, String> entry : entrySet) {
            signature += entry.getKey() + entry.getValue();
        }
        
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] sum = md.digest(signature.getBytes("UTF-8"));
            BigInteger bigInt = new BigInteger(1, sum);
            
            return bigInt.toString(16);
        } catch (Exception ex) {
            throw new IOException(ex.getMessage());
        }
    }
}
