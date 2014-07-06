package net.catchpole.pimpmylight.twitter;

//   Copyright 2014 catchpole.net
//
//   Licensed under the Apache License, Version 2.0 (the "License");
//   you may not use this file except in compliance with the License.
//   You may obtain a copy of the License at
//
//       http://www.apache.org/licenses/LICENSE-2.0
//
//   Unless required by applicable law or agreed to in writing, software
//   distributed under the License is distributed on an "AS IS" BASIS,
//   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//   See the License for the specific language governing permissions and
//   limitations under the License.

import net.catchpole.pimpmylight.lang.PropertiesFile;
import net.catchpole.silicone.lang.Throw;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

import javax.swing.*;

public class TwitterClient {
    private final Twitter twitter;

    public TwitterClient() throws Exception {
        this.twitter = (new PropertiesFile("twitter4j.properties").isAvailable()) ? TwitterFactory.getSingleton() : null;
    }

    public void tweet(final String status) {
        if (twitter != null) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        twitter.updateStatus(status);
                    } catch (Exception e) {
                        throw Throw.unchecked(e);
                    }
                }
            }.start();
        } else {
            System.out.println("no twitter client > " + status);
        }
    }

    private void accessTokens(String consumerKey, String secretKey) throws Exception {
        twitter.setOAuthConsumer(consumerKey, secretKey);

        RequestToken requestToken = twitter.getOAuthRequestToken();
        System.out.println("Open the following URL...");
        System.out.println(requestToken.getAuthorizationURL());

        AccessToken accessToken = null;
        while (null == accessToken) {
            String pin = JOptionPane.showInputDialog("PIN?");
            try{
                if (pin.length() > 0){
                    accessToken = twitter.getOAuthAccessToken(requestToken, pin);
                }else{
                    accessToken = twitter.getOAuthAccessToken();
                }
            } catch (TwitterException te) {
                if (401 == te.getStatusCode()){
                    System.out.println("Unable to get the access token.");
                }else{
                    te.printStackTrace();
                }
            }
        }
        System.out.println("token:       " + accessToken.getToken());
        System.out.println("secretToken: " + accessToken.getTokenSecret());
        System.exit(0);
    }
}
