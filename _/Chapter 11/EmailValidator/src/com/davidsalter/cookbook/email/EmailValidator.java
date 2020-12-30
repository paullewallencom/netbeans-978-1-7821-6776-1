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
package com.davidsalter.cookbook.email;

/**
 *
 * @author david@developinjava.com
 */
public class EmailValidator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            java.lang.String unregisteredUserEmail = "";
            java.lang.String userID = "<enter username here>";
            java.lang.String password = "<enter password here>";
            java.lang.String email = "<enter address to validate here>";
            int timeout = 15;
            com.strikeiron.EmailVerification service = new com.strikeiron.EmailVerification();
            com.strikeiron.EmailVerificationSoap port = service.getEmailVerificationSoap();
            // TODO initialize WS operation arguments here
            javax.xml.ws.Holder<com.strikeiron.SIWsOutputOfVerifyEmailRecord> verifyEmailResult = new javax.xml.ws.Holder<com.strikeiron.SIWsOutputOfVerifyEmailRecord>();
            javax.xml.ws.Holder<com.strikeiron.SISubscriptionInfo> siSubscriptionInfo = new javax.xml.ws.Holder<com.strikeiron.SISubscriptionInfo>();
            port.verifyEmail(unregisteredUserEmail, userID, password, email, timeout, verifyEmailResult, siSubscriptionInfo);
            System.out.println(verifyEmailResult.value.getServiceStatus().getStatusDescription());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
