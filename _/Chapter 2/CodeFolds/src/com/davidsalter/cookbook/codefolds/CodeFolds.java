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
package com.davidsalter.cookbook.codefolds;

/**
 * @author david@developinjava.com
 */
public class CodeFolds {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        int total = 0;
        int maximumCount = 10;

        // <editor-fold desc="Basic summing algorithm.">
        for (int i = 0; i < maximumCount; i++) {
            total += i;
        }
        // </editor-fold>

        System.out.println("Total: " + total);
    }

}
