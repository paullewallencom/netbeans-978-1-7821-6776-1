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
package com.davidsalter.cookbook.delicious;

import org.netbeans.saas.delicious.DeliciousBookmarkingService;
import org.netbeans.saas.RestResponse;

/**
 *
 * @author david@developinjava.com
 */
public class StoreBookMark {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            String url = "http://www.packtpub.com/netbeans-ide-8-cookbook/book";
            String description = "NetBeans 8 CookBook";
            String extended = null;
            String tags = "Java, NetBeans";
            String dt = null;
            String replace = null;
            String shared = null;

            RestResponse result = DeliciousBookmarkingService.addPosts(url, description, extended, tags, dt, replace, shared);
            if (result.getDataAsObject(delicious.bookmarkingservice.deliciousresponse.Result.class) instanceof delicious.bookmarkingservice.deliciousresponse.Result) {
                delicious.bookmarkingservice.deliciousresponse.Result resultObj = result.getDataAsObject(delicious.bookmarkingservice.deliciousresponse.Result.class);
            }
            
            System.out.println(result.getResponseCode());
            //TODO - Uncomment the print Statement below to print result.
            //System.out.println("The SaasService returned: "+result.getDataAsString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
// TODO code application logic here
    }

}
