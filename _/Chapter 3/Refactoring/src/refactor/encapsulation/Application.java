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


package refactor.encapsulation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author david@developinjava.com
 */
public class Application {
    
    private String name;
    private String author;
    private List<String> reviews = new ArrayList<String>();
    private float cost;

    public Application(String name, String author, float cost) {
        this.name = name;
        this.author = author;
        this.cost = cost;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @return the reviews
     */
    public List<String> getReviews() {
        return reviews;
    }

    /**
     * @return the cost
     */
    public float getCost() {
        return cost;
    }
    
    public void addReview(String review) {
        this.reviews.add(review);
    }
}
