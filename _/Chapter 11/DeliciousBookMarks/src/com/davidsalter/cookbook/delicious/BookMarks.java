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

import delicious.bookmarkingservice.deliciousresponse.Posts.Post;
import org.netbeans.saas.RestResponse;
import org.netbeans.saas.delicious.DeliciousBookmarkingService;

/**
 *
 * @author david@developinjava.com
 */
public class BookMarks {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            String tag = "Java";
            String dt = null;
            String url = "";
            String hashes = null;
            String meta = null;

            RestResponse result = DeliciousBookmarkingService.getPosts(tag, dt, url, hashes, meta);
            if (result.getDataAsObject(delicious.bookmarkingservice.deliciousresponse.Posts.class) instanceof delicious.bookmarkingservice.deliciousresponse.Posts) {
                delicious.bookmarkingservice.deliciousresponse.Posts resultObj = result.getDataAsObject(delicious.bookmarkingservice.deliciousresponse.Posts.class);
                
                for (Post post : resultObj.getPost()) {
                    System.out.println(post.getDescription());
                    System.out.println(post.getHref());
                }
            }
            
            System.out.println(result.getResponseCode());
             //TODO - Uncomment the print Statement below to print result.
            //System.out.println("The SaasService returned: "+result.getDataAsString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
