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

package com.davidsalter.cookbook.howtodebug;


/**
 * @author david@developinjava.com
 */
public class HowToDebug {

    public static void main(String[] args) {
        Person person1 = new Person();
        Person person2 = new Person();
        
        person1.setName("David");
        person1.setAge(21);
        person2.setName(null);
        person2.setAge(32);
    }
    
}
