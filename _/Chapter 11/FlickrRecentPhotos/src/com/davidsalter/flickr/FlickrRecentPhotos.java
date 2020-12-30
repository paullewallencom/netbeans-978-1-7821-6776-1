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
package flickrrecentphotos;

import flickr.photoservice.flickrresponse.Rsp;
import flickr.photoservice.flickrresponse.Rsp.Photo;
import org.netbeans.saas.RestResponse;
import org.netbeans.saas.flickr.FlickrPhotoService;

/**
 *
 * @author david@developinjava.com
 */
public class FlickrRecentPhotos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            String minDate = "1388534400";
            String extras = "Rain";
            String perPage = "10";
            String page = "1";

            RestResponse result = FlickrPhotoService.photosRecentlyUpdated(minDate, extras, perPage, page);
            if (result.getDataAsObject(flickr.photoservice.flickrresponse.Rsp.class) instanceof flickr.photoservice.flickrresponse.Rsp) {
                flickr.photoservice.flickrresponse.Rsp resultObj = result.getDataAsObject(flickr.photoservice.flickrresponse.Rsp.class);
                
                for (Rsp.Photos.Photo photo : resultObj.getPhotos().getPhoto()) {
                    System.out.println(photo.getTitle());
                }
            }
             //TODO - Uncomment the print Statement below to print result.
            //System.out.println("The SaasService returned: "+result.getDataAsString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
// TODO code application logic here
    }

}
