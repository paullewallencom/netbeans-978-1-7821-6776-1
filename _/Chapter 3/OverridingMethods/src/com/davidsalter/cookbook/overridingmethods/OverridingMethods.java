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

package com.davidsalter.cookbook.overridingmethods;

/**
 *
 * @author david@developinjava.com
 */
public class OverridingMethods {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Book book1 = new Book("NetBeans Cookbook", Book.format.Paperback);
        Book book2 = new Book("Seam 2 Development", Book.format.eBook);
        Book book3 = new Book("Seam 2 Development", Book.format.Paperback);
        
        System.out.println("Book1 == book2 " +  book1.equals(book2));
        System.out.println("Book2 == book3 " +  book2.equals(book3));
        System.out.println("Book3 == book1 " +  book3.equals(book1));
    }
    
}
