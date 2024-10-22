/*
 * Copyright 2014 David Salter
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.davidsalter.cookbook.geocoding;

import org.netbeans.saas.google.GoogleGeocodingService;
import org.netbeans.saas.RestResponse;

/**
 *
 * @author david@developinjava.com
 */
public class Geocoding {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            String q = "trafalgar square, london";
            String output = "json";

            RestResponse result = GoogleGeocodingService.geocode(q, output);
            if (result.getResponseCode()==200) {
                System.out.println("The SaasService returned: "+result.getDataAsString());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
